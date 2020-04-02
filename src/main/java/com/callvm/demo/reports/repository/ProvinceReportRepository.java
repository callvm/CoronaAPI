/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.repository;

import com.callvm.demo.reports.model.ProvinceReport;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author callum
 */
public interface ProvinceReportRepository extends JpaRepository<ProvinceReport, Long> {
    ProvinceReport[] findAllById(Long id);
    ProvinceReport[] findAllByDailyReportId(Long id);
}
