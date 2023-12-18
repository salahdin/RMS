package edu.miu.cs.cs544.cs544.repository;

import edu.miu.cs.cs544.domain.enums.UserType;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.dto.UserDTO;
import edu.miu.cs.cs544.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByCustomerEmail_thenReturnCustomer() {

        //given
        UserDTO user = new UserDTO("anv", "123", true, UserType.CLIENT);
        CustomerDTO customerDTO = new CustomerDTO(
                "An",
                "Vu",
                "anvu.sg@gmail.com",
                user
        );
        entityManager.persist(customerDTO);
        entityManager.flush();
        //
        var found = customerRepository.findCustomerByEmail( customerDTO.getEmail() );
                assertThat(customerDTO.getEmail())
                .isEqualTo(found.get().getEmail());

        // given
//        Account account = new Account("958",10.0,"VAN AN");
//        entityManager.persist(account);
//        entityManager.flush();
//
//        // when
//        Account found = accountRepository.findByAccountHolder("VAN AN");
//
//        // then
//        assertThat(account.getAccountNumber())
//                .isEqualTo(found.getAccountNumber());
//        // and
//        assertThat(account.getBalance())
//                .isEqualTo(found.getBalance());
//        // and
//        assertThat(account.getAccountHolder())
//                .isEqualTo(found.getAccountHolder());
    }
}
