package com.sacavix.users.persistence.projections;


//projection class based, just a simple pojo

import lombok.Value;

@Value
public class PersonLocationDTO {
    private String name;
    private String phone_number;
    private String street;
}
