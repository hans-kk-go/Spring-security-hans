package com.sangeng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
