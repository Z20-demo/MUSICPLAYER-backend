package com.nju.user.enums;


public enum Role {
    USER,ADMIN;
    public static Role getRole(String role){
        if(role.equals("USER")){
            return USER;
        }else if(role.equals("ADMIN")){
            return ADMIN;
        }
        return null;
    }
}
