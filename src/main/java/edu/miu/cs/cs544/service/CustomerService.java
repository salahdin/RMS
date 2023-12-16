package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.CustomerDTO;
import org.springframework.stereotype.Service;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO customerDTO);
}
