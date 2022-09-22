package com.cortes.p2p.data.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long userId;
    @NotEmpty(message = "name cannot be empty or null !")
    private String name;
    @NotEmpty(message = "username cannot be empty or null !")
    private String username;
    private List<String> interestList = new ArrayList<>();

    public Author(Long userId, String name, String username) {
        this.userId = userId;
        this.name = name;
        this.username = username;
    }
}
