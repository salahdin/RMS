package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerByEmail(String email);

    List<CustomerDTO> getCustomerByFirstName(String firstName);

    List<CustomerDTO> getCustomerByLastName(String lastName);

    CustomerDTO updateCustomerNamesByEmail(CustomerDTO customerDTO);

    CustomerDTO deactivateCustomerByEmail(String email);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomerBillingAddressByEmail(CustomerDTO customerDTO);
    CustomerDTO updateCustomerPhysicalAddressByEmail(CustomerDTO customerDTO);

}
