package com.gmail.pashasimonpure.web.controller;

import java.util.List;
import javax.validation.Valid;

import com.gmail.pashasimonpure.service.ShopService;
import com.gmail.pashasimonpure.service.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public String showShops(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/create")
    public String createShop(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "create_shop";
    }

    @PostMapping("/create")
    public String createShop(@Valid @ModelAttribute(name = "shop") ShopDTO shop, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_shop";
        }
        shopService.add(shop);
        return "redirect:/shops";
    }

    @GetMapping("/{id}/delete")
    public String removeShopById(@PathVariable(name = "id") Long id) {
        shopService.removeById(id);
        return "redirect:/shops";
    }

}