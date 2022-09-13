package com.step.springmvcapp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.core.style.ToStringCreator;

@Entity (name = "CandidateDetails")
public class CandidateDetails extends BaseEntity implements Serializable { 
    private String education;
    private Integer annualIncome;
    private String property;
    private String electionPromises;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Candidate candidate;
    
    public CandidateDetails() {}

    @Column (name="Education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column (name="Annual_Income")
    public Integer getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Integer annualIncome) {
        this.annualIncome = annualIncome;
    }

    @Column (name="Property")
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Column (name="Election_Promises")
    public String getElectionPromises() {
        return electionPromises;
    }

    public void setElectionPromises(String electionPromises) {
        this.electionPromises = electionPromises;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    
    @Override
    public String toString() {
         return new ToStringCreator(this)
            .append("education", education)
            .append("annualIncome", annualIncome)
            .append("property", property) 
            .append("electionPromises", electionPromises)      
            .toString();
    } 
    
    
}
