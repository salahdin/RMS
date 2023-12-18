package edu.miu.cs.cs544.cs544.controller;

import edu.miu.cs.cs544.Application;
import edu.miu.cs.cs544.controller.CustomerController;
import edu.miu.cs.cs544.domain.enums.UserType;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.dto.UserDTO;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTests {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetCustomerByEmail(){
        UserDTO user = new UserDTO("anvu", "123", true, UserType.CLIENT);
        CustomerDTO customer = new CustomerDTO("An", "Vu", "anvu.sg@gmail.com", user);

        given().contentType("application/json")
                .body(customer)
                .when().post("/customers").then()
                .statusCode(200);

        given()
                .when()
                .get("customers/anvu.sg@gmail.com")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("firstName",equalTo("An"))
                .body("lastName",equalTo("Vu"))
                .body("email",equalTo("anvu.sg@gmail.com"));

        given()
                .when()
                .delete("customers/anvu.sg@gmail.com");
    }

    @Test
    public void deleteCustomerByEmail() {
        // add the book to be fetched
        UserDTO user = new UserDTO("anvu", "123", true, UserType.CLIENT);
        CustomerDTO customer = new CustomerDTO("An", "Vu", "anvu.sg@gmail.com", user);
        //
        given()
                .contentType("application/json")
                .body(customer)
                .when().post("/customers").then()
                .statusCode(200);

        //cleanup
        given()
                .when()
                .delete("books/anvu.sg@gmail.com")
                .then()
                .statusCode(204);
    }

    @Test
    public void addCustomer(){

    }

    @Test
    public void deactivateCustomerByEmail(){

    }




}
