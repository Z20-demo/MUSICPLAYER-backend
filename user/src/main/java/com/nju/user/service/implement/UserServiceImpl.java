package com.nju.user.service.implement;

import com.nju.user.model.User;
import com.nju.user.repository.UserRepository;
import  com.nju.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nju.user.model.UserVO;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
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
        return null;
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

        return null;
    }

    @Override
    public String getUsername(Integer userId) {
        return null;
    }

}
