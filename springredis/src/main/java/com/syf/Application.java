package com.syf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author 锁雁飞
 * @Date 2020/3/17
 * @Version V1.0
 * @Describe
 */


@SpringBootApplication
@MapperScan(basePackages = {"com.syf.mapper"})
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {
    @Autowired
    private RedisTemplate redisTemplate;



   // @PostConstruct
    public void init(){
        initRedisTemplate();
    }
    /**
     * @Author 锁雁飞
     * @Date 2020/3/17
     * @Version V1.0
     * @return
     * @Describe  设置key序列化
     */
    public void initRedisTemplate(){
        RedisSerializer serializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
    }
    /**
     * @Author 锁雁飞
     * @Date 2020/3/19
     * @Version V1.0
     * @return
     * @Describe 不使用内嵌tomcat运行
     */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false); //关闭异常页面拦截
        return builder.sources(Application.class);
    }
     /**
      * @Author 锁雁飞
      * @Date 2020/3/19
      * @Version V1.0
      * @return
      * @Describe  使用内嵌taomcat 运行
      */

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
