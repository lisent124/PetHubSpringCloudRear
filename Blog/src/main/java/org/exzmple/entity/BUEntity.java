package org.exzmple.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BUEntity {
    private User user;
    private Blog blog;
}
