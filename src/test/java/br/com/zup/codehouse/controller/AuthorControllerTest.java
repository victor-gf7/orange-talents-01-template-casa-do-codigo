package br.com.zup.codehouse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void mustRegisterTheAuthor() throws Exception {
        URI uri = new URI("/author");

        String json = "{\n" +
                "    \"email\": \"teste@email.com.br\",\n" +
                "    \"name\": \"João Victor Ferreira\",\n" +
                "    \"description\": \"Teste de requisição\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }
    @Test
    void mustRegisterTheAuthorWithSameEmail() throws Exception {
        URI uri = new URI("/author");

        String json = "{\n" +
                "    \"email\": \"teste2@email.com.br\",\n" +
                "    \"name\": \"João Victor Ferreira\",\n" +
                "    \"description\": \"Teste de requisição\"\n" +
                "}";

        String json2 = "{\n" +
                "    \"email\": \"teste2@email.com.br\",\n" +
                "    \"name\": \"João Victor Ferreira\",\n" +
                "    \"description\": \"Teste de requisição\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    void mustNotRegisterTheAuthorWithInvalidEmail() throws Exception {
        URI uri = new URI("/author");

        String json = "{\n" +
                "    \"email\": \"testeemail.com.br\",\n" +
                "    \"name\": \"João Victor Ferreira\",\n" +
                "    \"description\": \"Teste de requisição\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    void mustNotRegisterTheAuthorWithFieldBlank() throws Exception {
        URI uri = new URI("/author");

        String json = "{\n" +
                "    \"email\": \"teste@email.com.br\",\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"Teste de requisição\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    void mustNotRegisterTheAuthorWithDescriptionGreaterThan400Characters() throws Exception {
        URI uri = new URI("/author");

        String json = "{\n" +
                "    \"email\": \"teste@email.com.br\",\n" +
                "    \"name\": \"João Victor Ferreira\",\n" +
                "    \"description\": \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }
}