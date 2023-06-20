package org.exzmple.entity;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class CUEntity {

    private int blog_id;
    private boolean like;
    private String comment;
    private Timestamp create_time;
    private User user;

    public CUEntity(Interactives interactives,User user){
        this.blog_id = interactives.getBlog_id();
        this.like = interactives.isLike();
        this.comment = interactives.getComment();
        this.create_time = interactives.getCreate_time();
        this.user = new User();
        this.user.setName(user.getName());
        this.user.setHead_picture(user.getHead_picture());
    }

}
