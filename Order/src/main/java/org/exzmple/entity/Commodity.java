package org.exzmple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
public class Commodity {
    private int id;
    private String name;
    private float price;
    private String unit;
    private int stock;
    private String picture;
    private int parlour_id;
}
