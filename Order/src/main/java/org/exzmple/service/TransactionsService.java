package org.exzmple.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.exzmple.entity.Transactions;
import org.exzmple.mapper.TransactionsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService extends ServiceImpl<TransactionsMapper, Transactions> {

    public List<Transactions> getMany(int userId){
        QueryWrapper<Transactions> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId).
                orderByAsc("start_time").
                last("limit 10");

        return super.list(wrapper);
    }

}
