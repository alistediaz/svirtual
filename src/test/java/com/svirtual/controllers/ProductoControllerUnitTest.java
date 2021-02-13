package com.svirtual.controllers;

import java.nio.charset.Charset;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.svirtual.api.controllers.ProductoController;

@RunWith(SpringRunner.class) 
@WebMvcTest
@AutoConfigureMockMvc
public class ProductoControllerUnitTest {
	
    @MockBean
    private ProductoRepository productoRepository;
    
    @Autowired
    ProductoController productoController;

    @Autowired
    private MockMvc mockMvc;
	
    @Test
    public void whenPostRequestToProductosAndValidProducto_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String prod = "{\"sku\": 10000000, \"name\" : \"testProd\",\"brand\": \"brand\", \"size\" : \"M\", \"price\" : 24990, \"urlImg\": \"http://asd.com/aer.jpg\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/productos")
          .content(prod)
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content()
            .contentType(textPlainUtf8));
    }

    @Test
    public void whenPostRequestToProductosAndInValidProducto_thenCorrectResponse() throws Exception {
    	String prod = "{\"sku\": 1, \"name\" : \"\",\"brand\": \"brand\", \"size\" : \"M\", \"price\" : 24990, \"urlImg\": \"http://asd.com/aer.jpg\"}";
	    mockMvc.perform(MockMvcRequestBuilders.post("/productos")
	      .content(prod)
	      .contentType(MediaType.APPLICATION_JSON_UTF8))
	      .andExpect(MockMvcResultMatchers.status().isBadRequest())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Name is mandatory")))
	      .andExpect(MockMvcResultMatchers.content()
	        .contentType(MediaType.APPLICATION_JSON_UTF8));
    }
	
}
