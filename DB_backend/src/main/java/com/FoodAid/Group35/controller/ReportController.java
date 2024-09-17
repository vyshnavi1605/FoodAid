package com.FoodAid.Group35.controller;

import com.FoodAid.Group35.model.FoodBean;
import com.FoodAid.Group35.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("auth/report/{username}")
    public List<FoodBean> getReport(@PathVariable("username") String userName){
         return reportService.getClientReport(userName);
    }
}
