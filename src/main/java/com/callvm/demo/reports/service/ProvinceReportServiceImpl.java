/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.service;

import com.callvm.demo.reports.model.DailyReport;
import com.callvm.demo.reports.model.ProvinceReport;
import com.callvm.demo.reports.repository.ProvinceReportRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author callum
 */
@Service
public class ProvinceReportServiceImpl implements ProvinceReportService {
    
    @Autowired
    private ProvinceReportRepository provinceReportRepository;

    @Override
    public void save(ProvinceReport pr) {
        provinceReportRepository.save(pr);
    }
    
    
    public void calculateDailyTotals(DailyReport dr){
        // Using a daily report's ID, get today's and yesterday's arrays of provinceReports
        Long id = dr.getId(); 
        ProvinceReport[] provinceReportsToday = provinceReportRepository.findAllByDailyReportId(id);
        ProvinceReport[] provinceReportsYesterday = provinceReportRepository.findAllByDailyReportId(id-1);
        // Traverse both arrays to find the difference between today and yesterday's totals - this gives us today's cases
        for (int i = 0; i < provinceReportsToday.length; i++){
            ProvinceReport today = provinceReportsToday[i];
            ProvinceReport yesterday = provinceReportsYesterday[i];
            
            int change = today.getCasesTotal() - yesterday.getCasesTotal();
            today.setCasesToday(change);
            // we can calculate the % change by dividing today's change over yesterday's total * 100
            double percChange = (double) today.getCasesToday() / yesterday.getCasesTotal() * 100;
            
            today.setPercentChange(percChange);
            
            save(today);
            
        }
        
    }
    
}
