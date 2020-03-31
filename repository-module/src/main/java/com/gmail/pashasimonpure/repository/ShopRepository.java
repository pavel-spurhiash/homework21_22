package com.gmail.pashasimonpure.repository;

import java.util.List;

import com.gmail.pashasimonpure.repository.model.Shop;

public interface ShopRepository extends GenericRepository<Long, Shop> {

    List<Shop> findShopsWithItemId(Long id);

}