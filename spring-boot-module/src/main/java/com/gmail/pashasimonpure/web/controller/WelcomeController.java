package com.gmail.pashasimonpure.web.controller;

import java.util.List;

import com.gmail.pashasimonpure.service.ItemService;
import com.gmail.pashasimonpure.service.model.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private final ItemService itemService;

    public WelcomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String welcomePage(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

}