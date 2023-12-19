package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByEmail(String email);
    List<Customer> findCustomerByFirstName(String firstName);
    List<Customer> findCustomerByLastName(String lastName);
}
