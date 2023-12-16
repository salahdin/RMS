package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {

        return null;
    }
}
