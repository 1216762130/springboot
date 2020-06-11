package com.ljx.springboot.service;

import com.ljx.springboot.model.Users;
import org.springframework.stereotype.Service;


public interface UsersService {
    int deleteByPrimaryKey(Long uid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}