package com.cortes.p2p.DTO;

import lombok.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String title;

    private String description;

    private String imageLink;

    private String projectLink;

    private String deadLine;

    List<String> ftags = new ArrayList<>();
}
