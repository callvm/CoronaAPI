/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.service;

import com.callvm.demo.reports.model.DailyReport;
import com.callvm.demo.reports.model.ProvinceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.callvm.demo.reports.repository.DailyReportRepository;
import com.callvm.demo.reports.repository.ProvinceReportRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author callum
 */
@Service
public class DailyReportServiceImpl implements DailyReportService{
    
    @Autowired
    private DailyReportRepository dailyReportRepository;
    
     @Autowired
    private ProvinceReportRepository provinceReportRepository;
    
    
    @Override
    public void save(DailyReport r) {
        dailyReportRepository.save(r);
    }
    
    
    
    public void calculateDailyTotals(DailyReport dr){
        
        System.out.println("DRS calc totals");
        
        int casesTotal = 0;
        int casesToday = 0;
        
        Long id = dr.getId();
        
        System.out.println("trying to enter pr loop");
        
        for (ProvinceReport pr : provinceReportRepository.findAllByDailyReportId(id)){
            casesToday += pr.getCasesToday();
            casesTotal += pr.getCasesTotal();
        }
        
        System.out.println("done pr loop");
        
        dr.setCasesToday(casesToday);
        System.out.println("cases today: " + casesToday);
        dr.setCasesTotal(casesTotal);
        
        Long yId = dr.getId() - 1;
        
        DailyReport yesterday = dailyReportRepository.findById(yId).get();
        
        System.out.println(dr);
        System.out.println(yesterday);
        double percChange = (double) dr.getCasesToday() / yesterday.getCasesTotal() * 100;
        
        System.out.println(dr.getCasesToday());
        System.out.println(yesterday.getCasesTotal());
        
        dr.setPercentChange(percChange);
        System.out.println("finished tryna");
        dailyReportRepository.save(dr);
        
    }
    
}
