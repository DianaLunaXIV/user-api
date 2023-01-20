package com.luna.userapi.models;


import java.sql.Date;
import lombok.Data;

@Data
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String profession;
    private Date dateCreated;
    private String country;
    private String city;
}
