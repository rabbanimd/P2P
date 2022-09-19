package com.cortes.p2p.data.DTO;

import com.cortes.p2p.data.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long postId;
    @NotEmpty(message = "description cannot be empty or null !")
    private String description;
    private String imageLink;
    private List<String> interestList = new ArrayList<>();
    private User author;
}
