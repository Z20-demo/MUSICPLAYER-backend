package com.nju.user.service;

import com.nju.user.model.UserVO;

public  interface UserService {
    Boolean register(UserVO userVO);

    String login(String phone,String password);

    UserVO getInformation();

    Boolean updateInformation(UserVO userVO);

    String getUsername (Integer userId);

    String verify();
}
