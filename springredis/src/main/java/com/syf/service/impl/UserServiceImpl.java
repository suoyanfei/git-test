package com.syf.service.impl;

import com.syf.mapper.UserMapper;
import com.syf.model.BsUser;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author 锁雁飞
 * @Date 2020/3/17
 * @Version V1.0
 * @Describe
 */
@Service
public class UserServiceImpl implements UserService {


    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public  BsUser selectByPrimaryKey(String usrId) {
        //读写锁  解决redis缓存穿透
        BsUser user = null;

        /*redisTemplate.opsForValue().increment("aa",-1);
        try {
            lock.readLock().lock();//加 读锁
            user = (BsUser)redisTemplate.opsForValue().get("User");
        } finally {

           lock.readLock().unlock(); //释放锁
        }

        if (user ==null ){
            try {
                lock.writeLock().lock();
                //设置到redis中
                user = userMapper.selectByPrimaryKey(usrId);
                redisTemplate.opsForValue().set("User",user);
            } finally {
               lock.writeLock().unlock();
            }
            System.out.println("从数据库查询....");

        }else{
            System.out.println("从缓存中获取....");
        }*/


        //使用双重检测锁 解决redis缓存穿透

      /*  //先从redis中查询
        BsUser user = (BsUser)redisTemplate.opsForValue().get("User");



        if(user == null){ //不存在就从数据库查询

            synchronized (this){  //同步代码块

                 user = (BsUser)redisTemplate.opsForValue().get("User"); //在查询一遍

                if(user == null){
                    //设置到redis中
                    user = userMapper.selectByPrimaryKey(usrId);
                    redisTemplate.opsForValue().set("User",user);

                }
            }
            System.out.println("从数据库查.....");
        }else{
            System.out.println("从缓存中拿。。。。");
        }*/

        user = userMapper.selectByPrimaryKey(usrId);

        return user;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void A(){
        //Object savepoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        BsUser user = new BsUser();
        user.setUsrId("88888");
        user.setCompanyId("1000");
        user.setCompanyName("北京A");
        user.setUsername("测试A");
        user.setPassword("123");
        user.setMobile("11111111");
        userMapper.insert(user);
        try {
            B();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void B() throws Exception {
        BsUser user = new BsUser();
        user.setUsrId("999999");
        user.setCompanyId("1000");
        user.setCompanyName("北京B");
        user.setUsername("测试B");
        user.setPassword("123");
        user.setMobile("2222222");
        //checked 异常，发生异常不会回滚，需要指定事务回滚触发的条件 rollBackFor=Exception.class
        userMapper.insert(user);
        InputStream in = new FileInputStream(new File("xxxx"));
        //会回滚
       // int i = 1/0;

    }

    @Override
    public BsUser selectLike(String name) {

        return userMapper.selectLike(name);
    }
}
