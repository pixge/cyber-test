package it.andreamugellini.manager;

import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(Test.class)
public class Test {
	
	
	String URL = "http://localhost:1234/calculations";
    @Autowired
    private MockMvc mockMvc;
    
    @org.junit.Test
    public void testGetEndpoint() throws Exception {
        mockMvc.perform(get(URL+"/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Hello, World!"));
    }

    @org.junit.Test
    public void testPostEndpoint() throws Exception {
        String requestBody = "{'operand1': '1', 'operand2': 30, 'operation':'sum'}";

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                
    }

}
