package com.gmail.pashasimonpure.web.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.validation.Valid;

import com.gmail.pashasimonpure.service.ItemService;
import com.gmail.pashasimonpure.service.ShopService;
import com.gmail.pashasimonpure.service.model.ItemDTO;
import com.gmail.pashasimonpure.service.model.ShopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final ItemService itemService;
    private final ShopService shopService;

    public ItemController(ItemService itemService, ShopService shopService) {
        this.itemService = itemService;
        this.shopService = shopService;
    }

    @GetMapping
    public String showItems(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/{id}")
    public String showItem(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("shops", shopService.findShopsWithItemId(id));
        return "item";
    }

    @GetMapping("/{id}/delete")
    public String removeItemById(@PathVariable(name = "id") Long id) {
        itemService.removeById(id);
        return "redirect:/items";
    }

    @GetMapping("/create")
    public String createItem(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("item", new ItemDTO());
        model.addAttribute("shops", shops);
        return "create_item";
    }

    @PostMapping("/create")
    public String createItem(@Valid @ModelAttribute(name = "item") ItemDTO item, BindingResult bindingResult, Model model) {
        logger.error("create item input DATA: " + item.toString());
        if (bindingResult.hasErrors()) {
            List<ShopDTO> shops = shopService.findAll();
            model.addAttribute("item", item);
            model.addAttribute("shops", shops);
            return "create_item";
        }
        List<Long> shopsId = item.getShopsId();
        //generate item id from database:
        item = itemService.add(item);
        shopService.addItemToShops(shopsId, item.getId());
        return "redirect:/items";
    }

}