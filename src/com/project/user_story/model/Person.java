package com.project.user_story.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date dateOfBirth;
    private int height;
    private double weight;
    private boolean hasDocuments;

    public Person(int id, String lastName, String name, String middleName, Date dateOfBirth, int height,
                  double weight, boolean hasDocuments) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = name;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.hasDocuments = hasDocuments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isHasDocuments() {
        return hasDocuments;
    }

    public void setHasDocuments(boolean hasDocuments) {
        this.hasDocuments = hasDocuments;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", height=" + height +
                ", weight=" + weight +
                ", hasDocuments=" + hasDocuments +
                '}';
    }
}
