package com.example.randomshipgeneratorcontroller;

import com.example.randomshipgeneratormodel.Ship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/")
public class ShipController {

    @GetMapping("ship")
    public Ship getShip(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET");
        Ship ship = new Ship();
        System.out.println(ship.toString());
        return ship;
    }
}
