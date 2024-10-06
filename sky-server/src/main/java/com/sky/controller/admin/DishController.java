package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

/**
 * 菜品管理
 */

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api("Dishes controller")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * add new dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("add new dish")
    public Result save(@RequestBody DishDTO dishDTO, ServletRequest servletRequest) {
        log.info("新增菜品: {}", dishDTO);

        dishService.saveWithFlavor(dishDTO);

        return Result.success();
    }
}
