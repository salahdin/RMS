package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.enums.AddressType;
import edu.miu.cs.cs544.dto.AddressDTO;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.dto.StateDTO;
import edu.miu.cs.cs544.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressAdapter {

    @Autowired
    StateAdapter stateAdapter;
    public AddressDTO entityToDTO(Address address){
        return new AddressDTO(address.getLine1(), address.getLine2(), address.getCity(), address.getPostalCode(), address.getAddressType(), stateAdapter.entityToDTO(address.getState()));
    }


}
