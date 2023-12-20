package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.adapter.CustomerAdapter;
import edu.miu.cs.cs544.adapter.UserAdapter;
import edu.miu.cs.cs544.config.HibernateUtil;
import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.enums.UserType;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.dto.LoggedInUserDTO;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.CustomerService;
import edu.miu.cs.cs544.service.util.SecurityUtils;
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
            //
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
    public CustomerDTO updateCustomerNamesByEmail(CustomerDTO customerDTO) {
        //
        try
        {
            checkAuthorization(customerDTO.getEmail());
            //
            var customerOpt = customerRepository.findByEmail(customerDTO.getEmail());
            if(customerOpt!=null)
            {
                var customer = customerAdapter.DtoToEntity(customerDTO);
                customerOpt.setLastName(customerDTO.getLastName());
                customerOpt.setLastName(customerDTO.getFirstName());
                //
                customerRepository.save(customerOpt);
                return customerAdapter.entityToDTO(customerOpt) ;
            }
            else
                throw new IllegalArgumentException("Customer does not exist");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to update customer: " + e.getMessage());
        }
        catch (Exception e)
        {
            throw e;
        }
    }


    @Override
    public CustomerDTO deactivateCustomerByEmail(String email) {
        //
        try
        {
            checkAdminAuthorization(email);
            //
            var customerOpt = customerRepository.findByEmail(email);
            if(customerOpt != null)
            {
                var user = customerOpt.getUser();
                user.setActive(false);

                userRepository.save(user);
                return customerAdapter.entityToDTO(customerRepository.findByEmail(email));
            }
            else
                throw new IllegalArgumentException("Customer does not exist");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to deactivate customer: " + e.getMessage());
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerAdapter.entityToDTOAll( customerRepository.findAll());
    }

    @Override
    public CustomerDTO updateCustomerBillingAddressByEmail(CustomerDTO customerDTO) {
        try
        {
            checkAuthorization(customerDTO.getEmail());
            //
            var customerOpt = customerRepository.findByEmail(customerDTO.getEmail());
            if(customerOpt != null)
            {
                return customerAdapter.entityToDTO(customerOpt);
            }
            else
                throw new IllegalArgumentException("Customer does not exist");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to update [updateCustomerBillingAddressByEmail] : " + e.getMessage());
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    private void checkAuthorization(String email) {
        LoggedInUserDTO loggedInUserDTO = SecurityUtils.getLoggedInUser();
        if (loggedInUserDTO == null) {
            throw new IllegalArgumentException("User is not logged in");
        }
        if (loggedInUserDTO.getRole() == UserType.CLIENT && !loggedInUserDTO.getName().equals(email)) {
            throw new IllegalArgumentException("User is not authorized to update the customer");
        }
    }

    private void checkAdminAuthorization(String email) {
        LoggedInUserDTO loggedInUserDTO = SecurityUtils.getLoggedInUser();
        if (loggedInUserDTO == null) {
            throw new IllegalArgumentException("User is not logged in");
        }
        if (loggedInUserDTO.getRole() != UserType.ADMIN) {
            throw new IllegalArgumentException("User is not authorized to execute the function");
        }
    }

    @Override
    public CustomerDTO updateCustomerPhysicalAddressByEmail(CustomerDTO customerDTO) {
        try
        {
            checkAuthorization(customerDTO.getEmail());
            //
            var customerOpt = customerRepository.findByEmail(customerDTO.getEmail());
            if(customerOpt != null)
            {
                customerOpt.setPhysicalAddress(  customerDTO.getPhysicalAddress() );
                return customerAdapter.entityToDTO(customerOpt);
            }
            else
                throw new IllegalArgumentException("Customer does not exist");
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Failed to update [updateCustomerPhysicalAddressByEmail] : " + e.getMessage());
        }
    }


}
