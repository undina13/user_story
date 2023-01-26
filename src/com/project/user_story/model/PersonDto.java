package com.project.user_story.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Класс для красивого вывода данных в json
 */
@Getter
@Setter
public class PersonDto {
    private int id;
    private DatePerson data;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.data = new DatePerson(person.getFirstName(), person.getLastName(), person.getMiddleName(),
                person.getDateOfBirth(), person.getHeight(), person.getWeight(), person.isHasDocuments());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class DatePerson {
        private String firstName;
        private String lastName;
        private String middleName;
        @JsonFormat(pattern = "dd.MM.yyyy")
        private Date dateOfBirth;
        private int height;
        private double weight;
        private boolean hasDocuments;
    }
}
