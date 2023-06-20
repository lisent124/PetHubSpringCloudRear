package org.example.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("petHub_blog")
public class Blog {
    private int id;
    private int userId;
    private String content;
    private Timestamp releaseTime;
    private String picture;
    private Boolean visible;
}
