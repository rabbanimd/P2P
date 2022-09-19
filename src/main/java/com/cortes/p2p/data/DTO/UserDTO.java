package com.cortes.p2p.data.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    @NotEmpty(message = "name cannot be empty or null !")
    private String name;

    @NotEmpty(message = "username cannot be empty or null !")
    private String username;

    private List<String> interestList = new ArrayList<>();
}
