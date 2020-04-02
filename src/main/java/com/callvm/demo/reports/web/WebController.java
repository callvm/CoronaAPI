/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.web;

import com.callvm.demo.reports.model.DailyReport;
import com.callvm.demo.reports.model.ProvinceReport;
import com.callvm.demo.reports.service.DailyReportServiceImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.callvm.demo.reports.repository.DailyReportRepository;
import com.callvm.demo.reports.repository.ProvinceReportRepository;
import com.callvm.demo.reports.service.ProvinceReportServiceImpl;
import com.callvm.demo.reports.service.UpdateService;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author callum
 */
@RestController
public class WebController {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DailyReportRepository dailyReportRepository;

    @Autowired
    private ProvinceReportRepository provinceReportRepository;

    @Autowired
    private ProvinceReportServiceImpl provinceReportService;

    @GetMapping("/latest")
    public DailyReport getprov() {

        return dailyReportRepository.findById(Long.parseLong("1")).get();
    }

    @GetMapping("/update")
    public void update() throws FileNotFoundException, IOException {
        updateService.updateFromTextFile();

    }

}
