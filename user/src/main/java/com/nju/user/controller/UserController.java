package com.nju.user.controller;

import com.nju.user.model.ResultVO;
import com.nju.user.model.UserVO;
import com.nju.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "here is user service";
    }
    @PostMapping("/register")
    public ResultVO<Boolean> regiser(UserVO userVO) {
        return ResultVO.buildSuccess(userService.register(userVO));
    }
}
