package com.syf.controller;

import com.syf.mapper.UserMapper;
import com.syf.model.BsUser;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 锁雁飞
 * @Date 2020/3/17
 * @Version V1.0
 * @Describe
 */
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserController userController;

    @RequestMapping(value = "/findUserById")
    public BsUser findUserById(){

        /*ExecutorService pool = Executors.newFixedThreadPool(8);


        for (int i =0 ;i <10000 ;i++){

             pool.submit(new Runnable() {
                @Override
                public void run() {
                    BsUser bsUser = userService.selectByPrimaryKey("2");
                }
            });
        }*/
        BsUser bsUser = userService.selectByPrimaryKey("2");
        return bsUser;
    }

    @RequestMapping(value = "/toJsp")
    public ModelAndView toJsp(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("msg","fff");
        return mv;
    }


    /**

     * @Author 锁雁飞
     * @Date 2020/4/12
     * @Version V1.0
     * @Describe
     */


    @RequestMapping(value = "/A")
    public void A(){
        //try {
            userService.A();
    }

    @RequestMapping(value = "/like/{name}")
    public Object like(@PathVariable("name") String name){
        BsUser bsUser = userService.selectLike(name);

        return bsUser;

    }


}

