package org.exzmple.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.exzmple.entity.Commodity;
import org.exzmple.mapper.CommodityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {

    public List getCommodities(int count){
        int maxId =  super.getOne(new QueryWrapper<Commodity>().orderByDesc("id").last("limit 1")).getId();
        Random random = new Random();
        List<Commodity> commodities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int rand = random.nextInt(maxId+1);
            Commodity commodity = super.getById(rand);
            if(commodity == null){
                i--;
            }
            else {
                commodities.add(commodity);
            }

        }
        return commodities;
    }

    public List getCommoditiesByParlourId(int parlourId){
        return super.list(new QueryWrapper<Commodity>()
                .eq("parlour_id",parlourId)
                .orderByDesc("id").last("limit 10"));

    }

}
