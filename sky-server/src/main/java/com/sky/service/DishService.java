package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {
    //add new dish and the flavor of it
    public void saveWithFlavor(DishDTO dishDTO);
}
