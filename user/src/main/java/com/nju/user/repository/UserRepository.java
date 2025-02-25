package com.nju.user.repository;

import com.nju.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByPhone(String phone);
    User findByPhoneAndPassword(String phone, String password);

}
