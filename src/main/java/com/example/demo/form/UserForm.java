package com.example.demo.form;

import lombok.Data;

@Data
public class UserForm {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userIcon;
}
