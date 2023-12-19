package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.adapter.ItemAdaptor;
import edu.miu.cs.cs544.adapter.ReservationAdapter;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.domain.enums.ReservationState;
import edu.miu.cs.cs544.dto.*;
import edu.miu.cs.cs544.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationAdapter reservationAdapter;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemAdaptor itemAdaptor;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseDto updateReservation(ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationDTO.getId());
        if (reservationOptional.isEmpty()) {
            throw new IllegalArgumentException("Reservation does not exist");
        }

        Reservation reservation = reservationAdapter.DtoToEntity(reservationDTO);
        reservationRepository.save(reservation);
        return ResponseDto.builder()
                .success(true)
                .message("Reservation updated successfully")
                .data(reservationDTO)
                .build();
    }

    @Transactional
    public ResponseDto createReservation(ReservationDTO reservationDTO) {
        Customer customer = validateCustomer(reservationDTO.getCustomerEmail());

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setReservationState(ReservationState.NEW);

        for (ItemDTO itemDTO : reservationDTO.getItems()) {
            Product product = validateProduct(itemDTO.getProduct().getId());
            validateProductAvailability(product.getId(), itemDTO.getCheckinDate(), itemDTO.getCheckoutDate());

            if (product.getMax_occupancy() < itemDTO.getOccupants()) {
                throw new IllegalArgumentException("Product quantity is not enough");
            }

            Item item = itemAdaptor.DtoToEntity(itemDTO);
            item.setProduct(product);
            item.setReservation(reservation);
            itemRepository.save(item);
            reservation.addItem(item);
        }

        reservationRepository.save(reservation);

        return ResponseDto.builder()
                .success(true)
                .message("Reservation created successfully")
                .data(reservationDTO)
                .build();
    }

    public void addItemToReservation(Integer reservationId, Item item) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("Invalid reservation id"));
        reservation.getItems().add(item);
    }


    public ResponseDto getAllReservations() {
        List<ReservationDTO> reservations = reservationAdapter.entityToDTOAll(reservationRepository.findAll());
        return ResponseDto.builder()
                .success(true)
                .message("Reservation fetched successfully")
                .data(reservations)
                .build();
    }

    public boolean isProductReservedDuringStay(Integer productId, String checkinDate, String checkoutDate) {
        LocalDate checkin = LocalDate.parse(checkinDate);
        LocalDate checkout = LocalDate.parse(checkoutDate);
        List<Reservation> reservations = reservationRepository.findReservationsByProductAndDates(productId, checkin, checkout);
        return !reservations.isEmpty();
    }

    private Customer validateCustomer(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));
    }

    private Product validateProduct(Integer productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product does not exist");
        }
        return product;
    }

    private void validateProductAvailability(Integer productId, String checkinDate, String checkoutDate) {
        if (isProductReservedDuringStay(productId, checkinDate, checkoutDate)) {
            throw new IllegalArgumentException("Product is already reserved on this date");
        }
    }

}