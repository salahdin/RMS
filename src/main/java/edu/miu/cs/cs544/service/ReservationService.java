package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation makeReservation(Reservation reservation) {
        //TODO: Add logic to make reservation
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation) {
        //TODO: Add logic to update reservation
        return reservationRepository.save(reservation);
    }

    public Reservation createReservation(Reservation reservation) {
        // TODO: Add logic to create reservation
        return reservationRepository.save(reservation);
    }

    public  void addItemToReservation(Integer reservationId, Item item){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new IllegalArgumentException("Invalid reservation id"));
        reservation.getItems().add(item);
    }
}