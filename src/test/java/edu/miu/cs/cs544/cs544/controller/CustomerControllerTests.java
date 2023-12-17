package edu.miu.cs.cs544.cs544.controller;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import org.junit.Test;

public class CustomerControllerTests {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetByEmail(){



    }


}
