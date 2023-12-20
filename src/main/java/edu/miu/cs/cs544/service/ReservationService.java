package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.adapter.ItemAdapter;
import edu.miu.cs.cs544.adapter.ReservationAdapter;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.enums.ReservationState;
import edu.miu.cs.cs544.domain.enums.UserType;
import edu.miu.cs.cs544.dto.ItemDTO;
import edu.miu.cs.cs544.dto.LoggedInUserDTO;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.ItemRepository;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.repository.ReservationRepository;
import edu.miu.cs.cs544.service.util.SecurityUtils;
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
    private ItemAdapter itemAdapter;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseDto updateReservation(ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationDTO.getId());
        Reservation reservation = reservationOptional.orElseThrow(() -> new IllegalArgumentException("Reservation does not exist"));

        reservationAdapter.dtoToEntity(reservationDTO);
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
        LoggedInUserDTO loggedInUserDTO = SecurityUtils.getLoggedInUser();
        checkAuthorization(loggedInUserDTO, customer);

        Reservation reservation = createNewReservation(customer);

        for (ItemDTO itemDTO : reservationDTO.getItems()) {
            createAndAddItemToReservation(itemDTO, reservation);
        }

        reservationRepository.save(reservation);

        return ResponseDto.builder()
                .success(true)
                .message("Reservation created successfully")
                .data(reservationDTO)
                .build();
    }

    private void checkAuthorization(LoggedInUserDTO loggedInUserDTO, Customer customer) {
        if (loggedInUserDTO == null) {
            throw new IllegalArgumentException("User is not logged in");
        }

        if (loggedInUserDTO.getRole() == UserType.CLIENT && !loggedInUserDTO.getName().equals(customer.getEmail())) {
            throw new IllegalArgumentException("User is not authorized to create a reservation for this customer");
        }
    }

    private Reservation createNewReservation(Customer customer) {
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setReservationState(ReservationState.NEW);
        return reservation;
    }

    private void createAndAddItemToReservation(ItemDTO itemDTO, Reservation reservation) {
        Product product = validateProduct(itemDTO.getProduct().getId());
        validateProductAvailability(product.getId(), itemDTO.getCheckinDate(), itemDTO.getCheckoutDate());

        if (product.getMaxOccupancy() < itemDTO.getOccupants()) {
            throw new IllegalArgumentException("Requested occupancy exceeds max occupancy");
        }

        Item item = itemAdapter.dtoToEntity(itemDTO);
        item.setProduct(product);
        item.setReservation(reservation);
        itemRepository.save(item);
        reservation.addItem(item);
    }

    public ResponseDto getAllReservations() {
        List<ReservationDTO> reservations = reservationAdapter.entityToDTOAll(reservationRepository.findAll());
        return ResponseDto.builder()
                .success(true)
                .message("Reservations fetched successfully")
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
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product does not exist"));
    }

    private void validateProductAvailability(Integer productId, String checkinDate, String checkoutDate) {
        if (isProductReservedDuringStay(productId, checkinDate, checkoutDate)) {
            throw new IllegalArgumentException("Product is already reserved on this date");
        }
    }
}
