package org.exzmple.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Data
public class CPEntity{
    private int id;
    private String name;
    private String parlour;
    private float price;
    private String unit;
    private int stock;
    private String picture;
    private int parlour_id;


    public CPEntity(Commodity commodity, Parlour parlour){
        this.id = commodity.getId();
        this.name = commodity.getName();
        this.parlour = parlour.getName();
        this.price = commodity.getPrice();
        this.unit = commodity.getUnit();
        this.stock = commodity.getStock();
        this.picture =  commodity.getPicture();
        this.parlour_id = parlour.getId();
    }



}
