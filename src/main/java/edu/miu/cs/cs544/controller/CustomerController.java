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

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping
    public ResponseEntity<?> getCustomers(){
        return new ResponseEntity<List<CustomerDTO>>(customerService.getAllCustomers(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.addCustomer(customerDTO), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getCustomersByLastname(@PathVariable("lastname") String lastname){
        return new ResponseEntity<List<CustomerDTO>>(
                customerService.getCustomerByLastName(lastname)
                , HttpStatus.OK);
    }

    // more to implement: update name(s) / addresses
    @PutMapping("/email/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    public ResponseEntity<?> updateCustomerByEmail(@RequestBody CustomerDTO customerDTO){
        //return new ResponseEntity<String>("result", HttpStatus.OK );
       try {
           var result = customerService.updateCustomerNamesByEmail(customerDTO);
           return new ResponseEntity<ResponseDto>(
                   new ResponseDto(
                           true,
                           "Update for user [" + customerDTO.getEmail() + "] successfully",
                           result
                   )
                   , HttpStatus.OK);
       }
       catch (Exception ex)
       {
           return new ResponseEntity<ResponseDto>(
                   new ResponseDto(
                           false,
                           "Update for user[" + customerDTO.getEmail() + "] has error: " + ex.getMessage(),
                           null
                   )
                   , HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/deactivate/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deactivateCustomerByEmail(@PathVariable("email") String email){
        try {
            var customer = customerService.getCustomerByEmail(email);
            if (customer == null) {
                return new ResponseEntity<ResponseDto>(new ResponseDto(false, "Customer with email = " + email + " is not available", null), HttpStatus.NOT_FOUND);
            }
            var result = customerService.deactivateCustomerByEmail(email);
            return new ResponseEntity<>(new ResponseDto(
                    true,
                    "Deactivating user [" + email + "] successfully",
                    result
            ), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<ResponseDto>(
                    new ResponseDto(
                            false,
                            "Deactivating user[" + email + "] has error: " + ex.getMessage(),
                            null
                    )
                    , HttpStatus.BAD_REQUEST);
        }
    }
}
