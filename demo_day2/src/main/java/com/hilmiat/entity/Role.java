package com.hilmiat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
public class Role {
    @Getter
    int id;
    @Getter @Setter
    String role_name;
}
