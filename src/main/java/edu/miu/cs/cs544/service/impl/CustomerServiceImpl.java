package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.adapter.CustomerAdapter;
import edu.miu.cs.cs544.adapter.UserAdapter;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerAdapter customerAdapter;
    @Autowired
    UserAdapter userAdapter;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {

        try {

            var customer = customerAdapter.DtoToEntity(customerDTO);
            customerRepository.save(customer);
            var customerDTOResponse = customerAdapter.entityToDTO(customer);
            return customerDTOResponse;

        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to add the customer: " + e.getMessage());
        }
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        try
        {
            var customer = customerRepository.findCustomerByEmail(email);
            if(customer.isPresent()){
                return customerAdapter.entityToDTO(customer.get());
            }
            return null;
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to get the customer by email: " + e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> getCustomerByFirstName(String firstName) {
        try
        {
            var customers = customerRepository.findCustomerByFirstName(firstName);
            if(customers.size() > 0){
                return customerAdapter.entityToDTOAll(customers);
            }
            return null;
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to get the customer by firstName: " + e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> getCustomerByLastName(String lastName) {
        try
        {
            var customers = customerRepository.findCustomerByLastName(lastName);
            if(customers.size() > 0){
                return customerAdapter.entityToDTOAll(customers);
            }
            return null;
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to get the customer by lastName: " + e.getMessage());
        }
    }

    @Override
    public CustomerDTO updateCustomerByEmail(CustomerDTO customerDTO) {
        //
        try
        {

            var customerOpt = customerRepository.findCustomerByEmail(customerDTO.getEmail());
            if(customerOpt.isPresent())
            {
                // update process
                var customer = customerAdapter.DtoToEntity(customerDTO);
                customerRepository.save(customer);
                //
                return customerAdapter.entityToDTO(customerOpt.get()) ;
            }
            else
                return null;
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to update customer: " + e.getMessage());
        }
    }

    @Override
    public String deleteCustomerByEmail(String email) {
        //
        try
        {

            var customerOpt = customerRepository.findCustomerByEmail(email);
            if(customerOpt.isPresent())
            {
                var user = customerOpt.get().getUser();
                user.setActive(false);
                userRepository.save(user);
                return "User account for customer [" + email + " ] was deactivated successfully";
            }
            else
                return "";
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to deactivate customer: " + e.getMessage());
        }
    }


}
