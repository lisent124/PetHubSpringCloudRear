package org.exzmple.feign;

import org.exzmple.entity.Commodity;
import org.exzmple.entity.Parlour;
import org.exzmple.entity.Transactions;
import org.exzmple.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ParlourService")
public interface ParlourFeign {

    @GetMapping("/parlour")
    public Parlour getParlourById(@RequestParam int id);

    @GetMapping("/commodity")
    public Commodity getCommodityById(@RequestParam int id);

    @PostMapping("/commodities/order/done")
    public Boolean toDoneOrder(@RequestBody Transactions t);
}
