package com.cortes.p2p.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
    @NotEmpty(message = "can not be empty")
    @Size(max = 80, min = 3)
    private String fullName;
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, max = 80)
    private String password;


}
