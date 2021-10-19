package com.cortes.p2p.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

}
