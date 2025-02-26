package com.nju.user;

import com.nju.user.enums.Role;
import com.nju.user.model.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class UserApplicationTests {

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }


    @Test
    public void testRedis(){
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setPhone("123456789");
        userVO.setPassword("123456");
        userVO.setUsername("test");
        userVO.setRole(Role.USER);
        redisTemplate.opsForValue().set("user",userVO);
        System.out.println("redis set user success");
        UserVO user = (UserVO) redisTemplate.opsForValue().get("user");
        System.out.println("user id = " + user.getId());
        System.out.println("user phone = " + user.getPhone());
        System.out.println("user password = " + user.getPassword());
    }


}
