package com.gmail.pashasimonpure.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.gmail.pashasimonpure.repository.ItemRepository;
import com.gmail.pashasimonpure.repository.ShopRepository;
import com.gmail.pashasimonpure.repository.model.Item;
import com.gmail.pashasimonpure.repository.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryImpl extends GenericRepositoryImpl<Long, Shop> implements ShopRepository {

    private final ItemRepository itemRepository;

    public ShopRepositoryImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Shop> findShopsWithItemId(Long id) {
        Item item = itemRepository.findById(id);
        List<Shop> shops = findAll()
                .stream()
                .filter(shop -> shop.getItems().contains(item))
                .collect(Collectors.toList());

        return shops;
    }

}