package com.project.user_story.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Person implements Serializable {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date dateOfBirth;
    private int height;
    private double weight;
    private boolean hasDocuments;
}
