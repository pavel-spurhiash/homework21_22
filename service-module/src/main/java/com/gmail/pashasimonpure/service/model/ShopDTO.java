package com.gmail.pashasimonpure.service.model;

import javax.validation.constraints.Size;

public class ShopDTO {

    private Long id;

    @Size(min = 3, max = 255, message = "Invalid Shop Name")
    private String name;

    @Size(min = 3, max = 255, message = "Invalid Shop Location")
    private String location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ShopDTO{" + "id=" + id + ", name='" + name + '\'' + ", location='" + location + '\'' + '}';
    }

}