package com.gmail.pashasimonpure.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gmail.pashasimonpure.repository.ItemRepository;
import com.gmail.pashasimonpure.repository.ShopRepository;
import com.gmail.pashasimonpure.repository.model.Item;
import com.gmail.pashasimonpure.repository.model.Shop;
import com.gmail.pashasimonpure.service.ShopService;
import com.gmail.pashasimonpure.service.model.ShopDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;

    public ShopServiceImpl(ShopRepository shopRepository, ItemRepository itemRepository) {
        this.shopRepository = shopRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public ShopDTO add(ShopDTO shopDTO) {
        Shop shop = convert(shopDTO);
        shopRepository.persist(shop);
        return convert(shop);
    }

    @Override
    @Transactional
    public ShopDTO findById(Long id) {
        return convert(shopRepository.findById(id));
    }

    @Override
    @Transactional
    public List<ShopDTO> findShopsWithItemId(Long id) {
        List<Shop> shops = shopRepository.findShopsWithItemId(id);
        List<ShopDTO> shopsDTO = new ArrayList<>();

        for (Shop shop : shops) {
            shopsDTO.add(convert(shop));
        }
        return shopsDTO;
    }

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopsDTO = new ArrayList<>();
        for (Shop shop : shops) {
            shopsDTO.add(convert(shop));
        }
        return shopsDTO;
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        Shop shop = shopRepository.findById(id);
        shop.getItems().clear();
        shopRepository.remove(shop);
    }

    @Override
    @Transactional
    public void addItemToShop(Long itemId, Long shopId) {
        Shop shop = shopRepository.findById(shopId);
        Item item = itemRepository.findById(itemId);
        shop.getItems().add(item);
    }

    @Override
    @Transactional
    public void addItemToShops(List<Long> shopsId, Long itemId) {
        for (Long shopId : shopsId) {
            addItemToShop(itemId, shopId);
        }
    }

    private static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

    private static Shop convert(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setName(shopDTO.getName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }

}