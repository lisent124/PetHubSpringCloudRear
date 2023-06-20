package org.exzmple.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@TableName("petHub_interactives")
public class Interactives {
    @TableId(type = IdType.AUTO)
    private int id;
    private boolean like;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp create_time;
    private String comment;
    private int blog_id;
    private int user_id;
}
