package com.mfarial.practicebaseapp;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ContractVerifierTests {


    @BeforeEach
    public void setup() {
        RestAssured.basePath = "/";
        RestAssured.port = Integer.parseInt("8080");
    }
}
