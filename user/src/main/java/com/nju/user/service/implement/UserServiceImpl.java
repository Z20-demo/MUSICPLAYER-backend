package com.nju.user.service.implement;

import com.nju.user.enums.Role;
import com.nju.user.enums.UserConstants;
import com.nju.user.exception.UserException;
import com.nju.user.model.User;
import com.nju.user.repository.UserRepository;
import  com.nju.user.service.UserService;
import com.nju.user.utils.SecurityUtil;
import com.nju.user.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.nju.user.model.UserVO;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    SecurityUtil securityUtil;

    @Override
    public Boolean register(UserVO userVO) {
        User user = userRepository.findByPhone(userVO.getPhone());
        if (user != null) {
            return false;
        }
        user = userVO.toPO();
        user.setCreateTime(new Date());
        userRepository.save(user);
        return true;
    }
    @Override
    public String login(String phone, String password) {
        if(redisTemplate.opsForValue().get(UserConstants.USER_PREFIX+phone)!=null){
            User user = (User) redisTemplate.opsForValue().get(UserConstants.USER_PREFIX+phone);
            if(user.getPassword().equals(password) && user.getPhone().equals(phone)) {
                return tokenUtil.getToken(user);
            }
        }
        User user = userRepository.findByPhoneAndPassword(phone,password);
        if (user == null) {
            throw new UserException("登录失败，用户不存在或密码错误");
        }
        redisTemplate.opsForValue().set(UserConstants.USER_PREFIX+phone,user);
        return tokenUtil.getToken(user);
    }
    @Override
    public UserVO getInformation() {
        return null;
    }
    @Override
    public Boolean updateInformation(UserVO userVO) {
        User user = userRepository.findById(userVO.getId()).get();
        if (user == null) {
            return false;
        }
        user.setId(userVO.getId());
        user.setPhone(userVO.getPhone());
        user.setPassword(userVO.getPassword());
        user.setUsername(userVO.getUsername());
        user.setRole(userVO.getRole());
        user.setCreateTime(userVO.getCreateTime());
        userRepository.save(user);
        redisTemplate.opsForValue().set(UserConstants.USER_PREFIX+user.getId(),user);
        return true;
    }
    @Override
    public String getUsername(Integer userId) {
        if(redisTemplate.opsForValue().get(UserConstants.USER_PREFIX+userId)!=null){
            User user = (User) redisTemplate.opsForValue().get(UserConstants.USER_PREFIX+userId);
            return user.getUsername();
        }
        User user = userRepository.findById(userId).get();
        redisTemplate.opsForValue().set(UserConstants.USER_PREFIX+userId,user.getUsername());
        return user.getUsername();
    }

    @Override
    public String verify() {
        User user = securityUtil.getCurrentUser();
        if(user == null){
            throw new UserException("用户未登录");
        }
        return Role.getRole(user.getRole());
    }
}
