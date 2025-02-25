package com.nju.user.model;

import com.nju.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic // 表示该字段不是主键
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "create_time")
    private Date createTime;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setId(this.id);
        userVO.setUsername(this.username);
        userVO.setPassword(this.password);
        userVO.setPhone(this.phone);
        userVO.setCreateTime(this.createTime);
        userVO.setRole(this.role);
        return userVO;
    }
}
