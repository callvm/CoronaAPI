/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callvm.demo.reports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author callum
 */
@Entity
public class DailyReport implements Serializable{
    
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date currentDay;
    
    private int casesToday, casesTotal;
    private double percentChange;
    
    @OneToMany(mappedBy = "dailyReport")
    private Set<ProvinceReport> provinceReports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(Date currentDay) {
        this.currentDay = currentDay;
    }

    public Set<ProvinceReport> getProvinceReports() {
        return provinceReports;
    }

    public void setProvinceReports(Set<ProvinceReport> provinceReports) {
        this.provinceReports = provinceReports;
    }

    public int getCasesToday() {
        return casesToday;
    }

    public void setCasesToday(int casesToday) {
        this.casesToday = casesToday;
    }

    public int getCasesTotal() {
        return casesTotal;
    }

    public void setCasesTotal(int casesTotal) {
        this.casesTotal = casesTotal;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }
    
    
    
    
    
    
}
