package com.firstspring.firstspring;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChechHTTPresponce {
@LocalServerPort
    private int port;
@Autowired
    private TestRestTemplate testRestTemplate;

@Test
public void ShouldPassifStringMatches(){
    asserEquals("Hello World from Spring Boost",testRestTemplate.getForObject("http://localhost:" + port +"/" + String.class));

}

}
