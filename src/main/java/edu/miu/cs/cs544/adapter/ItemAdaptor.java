package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.dto.ItemDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemAdaptor {

    public ItemDTO entityToDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCheckinDate(item.getCheckinDate().toString());
        itemDTO.setCheckoutDate(item.getCheckoutDate().toString());
        itemDTO.setOccupants(item.getOccupants());
       //TODO: add products
        itemDTO.setProduct(null);

        return itemDTO;
    }

    public Item DtoToEntity(ItemDTO itemDTO){
        Item item = new Item();
        //TODO: add products

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        item.setCheckinDate(LocalDate.parse(itemDTO.getCheckinDate(), formatter));
        item.setCheckoutDate(LocalDate.parse(itemDTO.getCheckoutDate(), formatter));

        return item;
    }

    public List<ItemDTO> entityToDTOAll(List<Item> items){
        return items.stream().map(item -> entityToDTO(item)).collect(Collectors.toList());
    }

    public List<Item> DtoToEntityAll(List<ItemDTO> itemDTOs){
        return itemDTOs.stream().map(itemDTO -> DtoToEntity(itemDTO)).collect(Collectors.toList());
    }
}