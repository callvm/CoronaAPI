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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author callum
 */
@RestController
public class WebController {

    @Autowired
    private DailyReportServiceImpl reportService;

    @Autowired
    private DailyReportRepository reportRepository;

    @Autowired
    private ProvinceReportRepository provinceReportRepository;

    @Autowired
    private ProvinceReportServiceImpl provinceReportService;

    @GetMapping("/latest")
    public DailyReport getprov() {

        return reportRepository.findById(Long.parseLong("1")).get();
    }

    @GetMapping("/update")
    public void update() throws FileNotFoundException, IOException {
        File f = new File("data.txt");
        Set<ProvinceReport> provinceReports = new HashSet();
        FileReader fileReader = new FileReader(f);
        BufferedReader br = new BufferedReader(fileReader);

        Date d = new Date(System.currentTimeMillis());
        DailyReport dr = new DailyReport();
        dr.setCurrentDay(d);

        String line;
        while ((line = br.readLine()) != null) {
            String[] out = line.split("-");
            ProvinceReport pr = new ProvinceReport();
            pr.setProvinceName(out[0]);
           
            pr.setCasesTotal(Integer.parseInt(out[1]));

            pr.setDailyReport(dr);
            provinceReports.add(pr);
            provinceReportService.save(pr);
        }

        if (reportRepository.findByCurrentDay(d) == null) {
            dr.setProvinceReports(provinceReports);
            reportService.save(dr);
        }

    }

}
