package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private String firstName;

    private String lastName;

    private String email;
    //
    List<Reservation> reservations = new ArrayList<>();
    private User user;
    //
    private Address billingAddress;
    private Address physicalAddress;
}
