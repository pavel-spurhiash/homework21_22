package com.gmail.pashasimonpure.service;

import java.util.List;

import com.gmail.pashasimonpure.service.model.ItemDTO;

public interface ItemService {

    ItemDTO add(ItemDTO item);

    ItemDTO findById(Long id);

    List<ItemDTO> findAll();

    void removeById(Long id);

}