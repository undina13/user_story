package com.project.user_story.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Класс для красивого вывода данных в json
 */

public class PersonDto {
    private int id;
    private DatePerson data;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.data = new DatePerson(person.getFirstName(), person.getLastName(), person.getMiddleName(),
                person.getDateOfBirth(), person.getHeight(), person.getWeight(), person.isHasDocuments());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DatePerson getData() {
        return data;
    }

    public void setData(DatePerson data) {
        this.data = data;
    }

    class DatePerson {

        private String firstName;
        private String lastName;
        private String middleName;
        @JsonFormat(pattern = "dd.MM.yyyy")
        private Date dateOfBirth;
        private int height;
        private double weight;
        private boolean hasDocuments;

        public DatePerson(String firstName, String lastName, String middleName, Date dateOfBirth,
                          int height, double weight, boolean hasDocuments) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.dateOfBirth = dateOfBirth;
            this.height = height;
            this.weight = weight;
            this.hasDocuments = hasDocuments;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
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
    }
}
