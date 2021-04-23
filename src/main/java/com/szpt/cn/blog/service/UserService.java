package com.szpt.cn.blog.service;

import com.szpt.cn.blog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
