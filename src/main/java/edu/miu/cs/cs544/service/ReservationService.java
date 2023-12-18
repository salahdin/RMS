package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.adapter.ItemAdaptor;
import edu.miu.cs.cs544.adapter.ReservationAdapter;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ItemDTO;
import edu.miu.cs.cs544.dto.ReservationDTO;
import edu.miu.cs.cs544.dto.ResponseDto;
import edu.miu.cs.cs544.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ResponseDto createReservation(ReservationDTO reservationDTO) {
        // TODO: Add logic to create reservation
        // get confirmation
        Reservation reservation = reservationAdapter.DtoToEntity(reservationDTO);
        for(ItemDTO itemDTO : reservationDTO.getItems()){
            if(productRepository.findById(itemDTO.getProduct().getId()).isPresent()){
                Item item = itemAdaptor.DtoToEntity(itemDTO);
                itemRepository.save(item);
                reservation.addItem(item);
            }
        }

        //TODO: check if product available in specified date

        reservationRepository.save(reservation);

        return ResponseDto.builder()
                .success(true)
                .message("Reservation created successfully")
                .data(reservation)
                .build();
    }

    public  void addItemToReservation(Integer reservationId, Item item){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new IllegalArgumentException("Invalid reservation id"));
        reservation.getItems().add(item);
    }
}