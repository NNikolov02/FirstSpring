package com.firstspring.firstspring.Json;

import com.firstspring.firstspring.Web.dto.PersonResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@JsonTest
public class PersonSerializationTest {

    @Autowired
    private JacksonTester<PersonResponse> personResponseJacksontester;


    @Test
    void serializeResponse() throws Exception {

        PersonResponse response = PersonResponse.builder()
                .name("Ivan")
                .age(15)
                .build();

        JsonContent<PersonResponse> parsedJson = personResponseJacksontester.write(response);

        assertThat(parsedJson).extractingJsonPathStringValue("$.name").isEqualTo("Ivan");
    }

    @Test
    void deserializeResponse() throws Exception {
        String responseObject = "{\"id\": \"2fd5b2d6-1d44-483f-bcc9-71036c060351\",\n" +
                "                \"name\": \"Ivan\",\n" +
                "                \"age\": 15}";

        PersonResponse response = personResponseJacksontester.parse(responseObject).getObject();

        assertEquals(response.getAge(), 15);
    }


}