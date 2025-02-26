package com.nju.user.controller;

import com.nju.user.model.ResultVO;
import com.nju.user.model.UserVO;
import com.nju.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public ResultVO<String> login(String phone, String password) {
        return ResultVO.buildSuccess(userService.login(phone, password));
    }
    @PostMapping("/update")
    public ResultVO<Boolean> update(UserVO userVO) {
        return ResultVO.buildSuccess(userService.updateInformation(userVO));
    }

    @GetMapping("/verify")
    public ResultVO<String> verify() { //返回当前的用户的身份,用于权限检查
        return ResultVO.buildSuccess(userService.verify());
    }

}
