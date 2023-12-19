package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationAdapter {

    @Autowired
    private CustomerAdapter customerAdapter;

    @Autowired
    private ItemAdaptor itemAdapter;

    public ReservationDTO entityToDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        Customer customer = reservation.getCustomer();
        reservationDTO.setCustomer(customerAdapter.entityToDTO(customer));
        reservationDTO.setItems(itemAdapter.entityToDTOAll(reservation.getItems()));
        return reservationDTO;
    }

    public Reservation DtoToEntity(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        Customer customer = customerAdapter.DtoToEntity(reservationDTO.getCustomer());
        reservation.setCustomer(customer);
        reservation.setItems(itemAdapter.DtoToEntityAll(reservationDTO.getItems()));
        return reservation;
    }

    public List<ReservationDTO> entityToDTOAll(List<Reservation> reservations){
        return reservations.stream().map(this::entityToDTO).toList();
    }
}
