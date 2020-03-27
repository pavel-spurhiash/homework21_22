package com.gmail.pashasimonpure.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gmail.pashasimonpure.repository.ItemRepository;
import com.gmail.pashasimonpure.repository.ShopRepository;
import com.gmail.pashasimonpure.repository.model.Item;
import com.gmail.pashasimonpure.repository.model.ItemDetails;
import com.gmail.pashasimonpure.repository.model.Shop;
import com.gmail.pashasimonpure.service.ItemService;
import com.gmail.pashasimonpure.service.model.ItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ShopRepository shopRepository) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public ItemDTO add(ItemDTO itemDTO) {
        Item item = convert(itemDTO);
        itemRepository.persist(item);
        return convert(item);
    }

    @Override
    @Transactional
    public ItemDTO findById(Long id) {
        return convert(itemRepository.findById(id));
    }

    @Override
    @Transactional
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (Item item : items) {
            itemsDTO.add(convert(item));
        }
        return itemsDTO;
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        List<Shop> shops = shopRepository.findShopsWithItemId(id);
        for(Shop shop: shops){
            shop.getItems().removeIf(item -> item.getId().equals(id));
        }
        Item item = itemRepository.findById(id);
        itemRepository.remove(item);
    }

    private static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getItemDetails().getPrice());
        return itemDTO;
    }

    private static Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(itemDTO.getPrice());
        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);
        return item;
    }

}