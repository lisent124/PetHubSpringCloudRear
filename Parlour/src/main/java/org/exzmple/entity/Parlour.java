package org.exzmple.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@TableName("petHub_parlour")
public class Parlour {
    private int id;
    private String name;
    private String phone;
    private String location;
    private String head_picture;
}
