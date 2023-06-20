package org.exzmple.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.exzmple.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
