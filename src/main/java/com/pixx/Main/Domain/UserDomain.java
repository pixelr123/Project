package com.pixx.Main.Domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Setter @Getter
public class UserDomain {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
