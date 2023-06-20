package org.exzmple.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TPEntity {
    private String name;
    private String parlour;
    private float price;
    private boolean state;
    private String count;
    private Timestamp start_time;

    public TPEntity(Transactions transactions,
                    Commodity commodity,
                    Parlour parlour){
        this.name = commodity.getName();
        this.parlour = parlour.getName();
        this.price = commodity.getPrice();
        this.state = transactions.isState();
        this.count = ""+transactions.getAmount()+commodity.getUnit();
        this.start_time = transactions.getStart_time();
    }
}
