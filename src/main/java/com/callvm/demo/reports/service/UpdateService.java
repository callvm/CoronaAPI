/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.service;

import com.callvm.demo.reports.model.DailyReport;
import com.callvm.demo.reports.model.ProvinceReport;
import com.callvm.demo.reports.repository.DailyReportRepository;
import com.callvm.demo.reports.repository.ProvinceReportRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author callum
 */
@Service
public class UpdateService {

    @Autowired
    private DailyReportServiceImpl dailyReportService;

    @Autowired
    private DailyReportRepository dailyReportRepository;

    @Autowired
    private ProvinceReportServiceImpl provinceReportService;

    @Autowired
    private ProvinceReportRepository provinceReportRepository;

    public void updateFromTextFile() throws FileNotFoundException, IOException {
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

        dr.setProvinceReports(provinceReports);
        dailyReportService.save(dr);


        provinceReportService.calculateDailyTotals(dr);
        dailyReportService.calculateDailyTotals(dr);
    }

}
