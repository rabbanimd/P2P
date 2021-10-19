package com.cortes.p2p.DTO;

import com.cortes.p2p.utils.Name;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignupDTO {
    private Name name;
    private String username;
    private String hash;
    private String roll;
    private String studentId;
    private String bio;
    private String profilePic;
    private String link;
    private List<String> ftags;
}
