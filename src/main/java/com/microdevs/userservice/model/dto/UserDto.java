package com.microdevs.userservice.model.dto;

import com.microdevs.baseservice.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private StatusType status;
}
