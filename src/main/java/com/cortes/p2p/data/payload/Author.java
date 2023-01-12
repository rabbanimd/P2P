package com.cortes.p2p.data.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long userId;
    private String name;
    private String username;
    private String email;
    private boolean isAuthorized;
    private List<String> interestList = new ArrayList<>();

    public Author(Long userId, String name, String username,String email, boolean isAuthorized) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.isAuthorized = isAuthorized;
    }
}
