package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.dto.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerAdapter {
    public CustomerDTO entityToDTO(Customer customer){

        return new CustomerDTO();

        //return new CustomerDTO(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

}
