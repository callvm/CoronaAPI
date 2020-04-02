/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.service;

import com.callvm.demo.reports.model.DailyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.callvm.demo.reports.repository.DailyReportRepository;

/**
 *
 * @author callum
 */
@Service
public class DailyReportServiceImpl implements DailyReportService{
    
    @Autowired
    private DailyReportRepository reportRepository;
    
    @Override
    public void save(DailyReport r) {
        reportRepository.save(r);
    }
    
}
