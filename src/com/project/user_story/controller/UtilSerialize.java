package com.project.user_story.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.user_story.model.Person;
import com.project.user_story.model.PersonDto;
import com.project.user_story.view.ProgressBar;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class UtilSerialize {

    public static void Serialize(List<Person> people) throws JsonProcessingException {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person person : people) {
            personDtoList.add(new PersonDto(person));
        }
        writeInFile(personDtoList);
    }

    private static void writeInFile(List<PersonDto> personDtoList) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setDefaultPrettyPrinter(new JsonPrettyPrinter());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("json", "json");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);

        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getPath();

            if (!filename.endsWith(".json")) {
                filename = filename + ".json";
                fc.setSelectedFile(new File(filename));
            }
            ProgressBar progressBar = new ProgressBar();
            try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {

                fw.write(mapper.writeValueAsString(personDtoList));
            } catch (IOException e) {
                System.out.println("Не могу записать файл");
            } finally {
                progressBar.close();
            }
        }
    }
}
