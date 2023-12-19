package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerAdapter {

    @Autowired
    UserAdapter userAdapter;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerDTO entityToDTO(Customer customer){
        UserDTO userDTO = userAdapter.entityToDTO(customer.getUser());
        return new CustomerDTO( customer.getFirstName(), customer.getLastName(), customer.getEmail(), userDTO );
    }

    public List<CustomerDTO> entityToDTOAll(List<Customer> customers){
        return customers.stream().map(customer -> entityToDTO(customer)).collect(Collectors.toList());
    }

    public Customer DtoToEntity(CustomerDTO customerDTO){
        //
        User user =  userAdapter.DtoToEntity(customerDTO.getUserDTO());
        //
        return new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                user,
                customerDTO.getBillingAddress(),
                customerDTO.getPhysicalAddress()
        );
    }

    public List<Customer> DtoToEntityAll(List<CustomerDTO> customerDTOs){
        return customerDTOs.stream().map(customerDTO -> DtoToEntity(customerDTO)).collect(Collectors.toList());
    }


}
