package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.adapter.ItemAdaptor;
import edu.miu.cs.cs544.adapter.ReservationAdapter;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ItemRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("test")
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private ReservationAdapter reservationAdapter;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ItemAdaptor itemAdaptor;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void updateReservation() {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setEmail("test@gmail.com");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(1);
        //reservationDTO.setCustomer(customer);
        reservationDTO.setItems(null);


        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setCustomer(customer);
        reservation.setItems(null);

        when(reservationRepository.findById(reservationDTO.getId())).thenReturn(Optional.of(reservation));
        when(reservationAdapter.DtoToEntity(reservationDTO)).thenReturn(reservation);
        when(customerRepository.findCustomerByEmail(reservationDTO.getCustomer().getEmail())).thenReturn(Optional.of(customer));

        ResponseDto responseDto = reservationService.updateReservation(reservationDTO);
        assertTrue(responseDto.isSuccess());
    }

    @Test
    void createReservation() {
    }

    @Test
    void addItemToReservation() {
    }

    @Test
    void getAllReservations() {
    }
}