package com.nju.user.model;

import com.nju.user.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private Date createTime;
    private Role role;

    public User toPO(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setCreateTime(createTime);
        user.setRole(role);
        return user;
    }
}
