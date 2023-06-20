package org.exzmple.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Parlour {
    private int id;
    private String name;
    private String phone;
    private String location;
    private String head_picture;
}
