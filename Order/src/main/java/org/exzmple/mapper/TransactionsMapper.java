package org.exzmple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.exzmple.entity.Transactions;

@Mapper
public interface TransactionsMapper extends BaseMapper<Transactions> {
}
