package edu.miu.cs.cs544.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Integer id;
    private CustomerDTO customer;
    private List<ItemDTO> items;
}