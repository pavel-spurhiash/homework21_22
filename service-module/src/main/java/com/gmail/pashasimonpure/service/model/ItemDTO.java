package com.gmail.pashasimonpure.service.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO {

    private Long id;

    @Size(min = 3, max = 255, message = "Invalid Item Name")
    private String name;

    @Size(min = 3, max = 255, message = "Invalid Item Description")
    private String description;

    @NotNull
    @DecimalMin(value = "0.5", message = "Invalid Item Price (0.5 min)")
    private BigDecimal price;

    @NotEmpty
    private List<Long> shopsId = new ArrayList<>();

    public List<Long> getShopsId() {
        return shopsId;
    }

    public void setShopsId(List<Long> shopsId) {
        this.shopsId = shopsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", shopsId=" + shopsId +
                '}';
    }

}