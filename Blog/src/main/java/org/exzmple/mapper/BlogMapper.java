package org.exzmple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.exzmple.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}
