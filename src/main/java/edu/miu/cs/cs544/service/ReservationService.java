package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.adapter.ItemAdaptor;
import edu.miu.cs.cs544.adapter.ReservationAdapter;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.enums.ReservationState;
import edu.miu.cs.cs544.dto.ItemDTO;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationAdapter reservationAdapter;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemAdaptor itemAdaptor;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Reservation updateReservation(Reservation reservation) {
        //TODO: Add logic to update reservation
        return reservationRepository.save(reservation);
    }

    @Transactional
    public ResponseDto createReservation(ReservationDTO reservationDTO){
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(reservationDTO.getCustomer().getEmail());
        if (!customerOptional.isPresent()) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        Reservation reservation = new Reservation();
        Customer customer = customerOptional.get();
        reservation.setCustomer(customer);
        reservation.setReservationState(ReservationState.NEW);

        for (ItemDTO itemDTO : reservationDTO.getItems()) {
            // Check if product exists
            Optional<Product> productOptional = productRepository.findById(itemDTO.getProduct().getId());
            if (!productOptional.isPresent()) {
                throw new IllegalArgumentException("Product does not exist");
            }

            Item item = itemAdaptor.DtoToEntity(itemDTO);
            item.setProduct(productOptional.get());
            itemRepository.save(item);
            reservation.addItem(item);
        }

        reservationRepository.save(reservation);

        return ResponseDto.builder()
                .success(true)
                .message("Reservation created successfully")
                .data(reservation)
                .build();
    }

    public void addItemToReservation(Integer reservationId, Item item) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("Invalid reservation id"));
        reservation.getItems().add(item);
    }
}