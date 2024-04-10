package com.example.demo.controller;

import com.example.demo.response.FlowChart;
import com.example.demo.service.FlowChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CodeFlowController {

    @Autowired
    private FlowChartService flowChartService;

    @PostMapping("/analyze")
    public FlowChart analyzeCode(@RequestBody String code) {
        // Implement code analysis logic here
        // You can parse the code, extract relevant information, and return the analysis result

        return flowChartService.getFlowChart(code);
    }
}
