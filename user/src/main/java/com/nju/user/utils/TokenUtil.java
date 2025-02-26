package com.nju.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.nju.user.enums.UserConstants;
import com.nju.user.model.User;
import com.nju.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    @Autowired
    UserRepository userRepository;

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    public String getToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withAudience(String.valueOf(user.getId()))
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

    public boolean verifyToken(String token) {
        try {
            Integer userId=Integer.parseInt(JWT.decode(token).getAudience().get(0));
            User user= userRepository.findById(userId).get();
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public User getUser(String token){
        Integer userId=Integer.parseInt(JWT.decode(token).getAudience().get(0));
        if(redisTemplate.opsForValue().get(UserConstants.USER_PREFIX +userId) != null){
            return (User) redisTemplate.opsForValue().get("user");
        }
        User user = userRepository.findById(userId).get();
        redisTemplate.opsForValue().set(UserConstants.USER_PREFIX+userId,user);
        return user;
    }
}