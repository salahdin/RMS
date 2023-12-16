package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid  @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.addCustomer(customerDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCustomers(){
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

}
