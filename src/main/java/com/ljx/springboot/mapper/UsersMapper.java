package com.ljx.springboot.mapper;

import com.ljx.springboot.model.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}