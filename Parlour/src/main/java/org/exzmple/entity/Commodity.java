package org.exzmple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Data;

@TableName("petHub_commodity")
@Data
public class Commodity {
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private float price;
    private String unit;
    private int stock;
    private String picture;
    private int parlour_id;
}
