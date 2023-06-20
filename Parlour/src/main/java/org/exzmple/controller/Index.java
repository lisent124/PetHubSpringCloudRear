package org.exzmple.controller;

import org.exzmple.entity.CPEntity;
import org.exzmple.entity.Commodity;
import org.exzmple.entity.Parlour;
import org.exzmple.entity.Transactions;
import org.exzmple.pojo.Result;
import org.exzmple.pojo.ResultCode;
import org.exzmple.service.CommodityService;
import org.exzmple.service.ParlourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Index {

    @Autowired
    CommodityService commodityService;

    @Autowired
    ParlourService parlourService;

    @Value("${BaseUrl}")
    String baseUrl;

    private List getCPEntities(List<Commodity> commodities){
        List<CPEntity> cpEntities = new ArrayList<>();
        for(Commodity commodity : commodities){
            Parlour parlour = parlourService.getById(commodity.getParlour_id());
            commodity.setPicture(baseUrl+commodity.getPicture());
            CPEntity cpEntity = new CPEntity(commodity,parlour);
            cpEntities.add(cpEntity);
        }
        return cpEntities;
    }

    @GetMapping("/commodities")
    public Result getCommodities(){
        List<Commodity> commodities = commodityService.getCommodities(10);

        List<CPEntity> cpEntities = getCPEntities(commodities);

        return Result.success(cpEntities);
    }

    @GetMapping("/commoditiesByParlour")
    public Result getCommoditiesByParlourId(@RequestParam("parlour_id") int parlourId){
        List<Commodity> commodities = commodityService.getCommoditiesByParlourId(parlourId);

        List<CPEntity> cpEntities = getCPEntities(commodities);

        return Result.success(cpEntities);
    }

    @GetMapping("/")
    public Result getParlourInfo(@RequestParam String parlour_id){
        Parlour parlour = parlourService.getById(parlour_id);
        if(parlour == null)
            return Result.failure(ResultCode.NO_REQUEST_DATA);

        parlour.setHead_picture(baseUrl+parlour.getHead_picture());
        return Result.success(parlour);
    }

    @PostMapping("/commodities/order/done")
    public Boolean toDoneOrder(@RequestBody Transactions t){

        Commodity commodity = commodityService.getById(t.getCommodity_id());

        int stock = commodity.getStock();
        if(stock<t.getAmount()) throw new RuntimeException("存货不足");

        commodity.setStock(stock-t.getAmount());
        commodityService.updateById(commodity);

        return true;
    }




    @GetMapping("/commodity")
    public Commodity getCommodityById(@RequestParam int id){
        return commodityService.getById(id);
    }

    @GetMapping("/parlour")
    public Parlour getParlourById(@RequestParam int id){
        return parlourService.getById(id);
    }
}
