package com.gmail.pashasimonpure.repository.impl;

import com.gmail.pashasimonpure.repository.model.Item;
import com.gmail.pashasimonpure.repository.ItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

}