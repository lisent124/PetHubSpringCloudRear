package org.exzmple.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("petHub_user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String phone;
    private String password;
    private String name;
    private String head_picture;
}
