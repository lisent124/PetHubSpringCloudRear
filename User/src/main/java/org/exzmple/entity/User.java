package org.exzmple.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("petHub_user")
public class User {
    private int id;
    private String name;
    private String gender;
//    private String phone;
    private String location;
    private String head_picture;

}
