package com.sacavix.users.persistence.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonLocationDTO2 {
    private String name;
    private String phone_number;
    private String street;
}
