package com.gmail.pashasimonpure.service;

import java.util.List;

import com.gmail.pashasimonpure.service.model.ShopDTO;

public interface ShopService {

    ShopDTO add(ShopDTO shop);

    ShopDTO findById(Long id);

    List<ShopDTO> findAll();

    void removeById(Long id);

    void addItemToShop(Long itemId, Long shopId);

    List<ShopDTO> findShopsWithItemId(Long id);

    void addItemToShops(List<Long> shopsId, Long itemId);

}