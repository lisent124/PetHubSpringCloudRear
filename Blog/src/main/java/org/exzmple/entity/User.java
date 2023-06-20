package org.exzmple.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
//    private String gender;
//    private String phone;
//    private String location;
    private String head_picture;
    private Boolean like;
    private String comment;

}
