package com.microdevs.userservice.entity;

import com.microdevs.userservice.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
