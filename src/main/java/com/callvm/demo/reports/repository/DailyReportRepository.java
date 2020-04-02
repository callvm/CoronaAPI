/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.repository;

import com.callvm.demo.reports.model.DailyReport;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author callum
 */
public interface DailyReportRepository extends JpaRepository<DailyReport, Long>{
    DailyReport findByCurrentDay(Date d);
}
