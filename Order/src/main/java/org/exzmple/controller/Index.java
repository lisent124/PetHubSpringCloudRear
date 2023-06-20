package org.exzmple.controller;


//import io.seata.spring.annotation.GlobalTransactional;
import org.exzmple.entity.Commodity;
import org.exzmple.entity.Parlour;
import org.exzmple.entity.TPEntity;
import org.exzmple.entity.Transactions;
import org.exzmple.feign.ParlourFeign;
import org.exzmple.pojo.Result;
import org.exzmple.pojo.ResultCode;
import org.exzmple.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class Index {

    @Autowired
    TransactionsService service;

    @Autowired
    ParlourFeign feign;

    @GetMapping("/")
    public Result getOrders(@RequestHeader("id")int userId){
        List<Transactions> many = service.getMany(userId);

        List<TPEntity> dataList = new ArrayList<>();
        for (Transactions t:many){
            Commodity commodity = feign.getCommodityById(t.getCommodity_id());
            Parlour parlour = feign.getParlourById(commodity.getParlour_id());

            TPEntity order = new TPEntity(t, commodity, parlour);

            dataList.add(order);

        }
        return Result.success("返回订单成功",dataList);
    }

    @PostMapping("/")
//    @GlobalTransactional
    public Result createOrder(@RequestHeader("id")int userId,
                              @RequestParam("commodity_id")int commodityId,
                              @RequestParam("count")int count){
        Transactions t = new Transactions();

        t.setAmount(count);
        t.setCommodity_id(commodityId);
        t.setState(false);
        t.setUser_id(userId);
        t.setAccountant_id(1);
        t.setStart_time(new Timestamp(new Date().getTime()));

        service.save(t);
        try {
            feign.toDoneOrder(t);
        }catch (RuntimeException e){
            return Result.failure(ResultCode.SERVICE_ERROR,e.getMessage());
        }

        t.setState(true);
        service.updateById(t);

        return Result.success("完成订单");
    }

}
