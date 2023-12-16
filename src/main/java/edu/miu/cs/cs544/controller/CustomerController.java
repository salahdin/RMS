package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid  @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.addCustomer(customerDTO), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerByEmail(email)
                , HttpStatus.OK);
    }

    @GetMapping("/firstname/{firstname}")
    public ResponseEntity<?> getCustomersByFirstname(@PathVariable("firstname") String firstname){
        return new ResponseEntity<List<CustomerDTO>>(
                customerService.getCustomerByFirstName(firstname)
                , HttpStatus.OK);
    }


    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<?> getCustomersByLastname(@PathVariable("lastname") String lastname){
        return new ResponseEntity<List<CustomerDTO>>(
                customerService.getCustomerByLastName(lastname)
                , HttpStatus.OK);
    }

    // more to implement: update name(s) / addresses

}
