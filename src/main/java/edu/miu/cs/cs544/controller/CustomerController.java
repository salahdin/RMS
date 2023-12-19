package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid  @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.addCustomer(customerDTO), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerByEmail(email)
                ,HttpStatus.OK);
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

    ///@PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @PutMapping("/email/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateCustomerByEmail(@Valid  @RequestBody CustomerDTO customerDTO){
        var result = customerService.updateCustomerByEmail(customerDTO);
        return new ResponseEntity<CustomerDTO>(result, HttpStatus.OK );
        //
    }

    @DeleteMapping("/email/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deactivateCustomerByEmail(@PathVariable("email") String email){
        var customer = customerService.getCustomerByEmail(email);
        if(customer == null)
        {
            return new ResponseEntity<ResponseDto>(new ResponseDto(false, "Customer with email = " + email + " is not available", null), HttpStatus.NOT_FOUND);
        }
        var result = customerService.deleteCustomerByEmail(email);
        return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
    }
}
