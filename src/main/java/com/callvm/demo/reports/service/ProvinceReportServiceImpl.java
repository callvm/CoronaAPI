/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.service;

import com.callvm.demo.reports.model.ProvinceReport;
import com.callvm.demo.reports.repository.ProvinceReportRepository;
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
    
}
