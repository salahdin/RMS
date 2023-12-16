package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.adapter.CustomerAdapter;
import edu.miu.cs.cs544.adapter.UserAdapter;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {

        try {

            var customer = customerAdapter.DtoToEntity(customerDTO);
            customerRepository.save(customer);
            var customerDTOResponse = customerAdapter.entityToDTO(customer);
            return customerDTOResponse;


//            //reterive the roles from the database
//            Set<Role> roles = new HashSet<>();
//            roleTypes.forEach(role -> {
//                var roleMember = roleRepository.findByName(role.getName());
//                roles.add(roleMember);
//            });
//            member.setRoleTypes(roles);
//            member.setAudit(new Audit(LocalDateTime.now()));
//            memberRepository.save(member);
//
//            var rolesDTO = roleAdapter.entityToDTOAll(roles);
//            var memberDTOResponse = memberAdapter.entityToDTO(member);
//            memberDTOResponse.setRoleTypes(rolesDTO);

//
//            return memberDTOResponse;
//

        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to add the customer");
        }
    }
}
