package org.exzmple.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@TableName("petHub_transactions")
public class Transactions {
    @TableId(type = IdType.AUTO)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp start_time;
    private int amount;
    private boolean state;
    private int user_id;
    private int commodity_id;
    private int accountant_id;
}
