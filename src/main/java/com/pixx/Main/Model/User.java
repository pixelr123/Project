package com.pixx.Main.Model;

import lombok.*;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String resetToken;
}
