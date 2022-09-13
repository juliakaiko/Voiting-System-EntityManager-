package com.step.springmvcapp.entity;

import com.step.springmvcapp.entity.annotations.ValidPassportNumber;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.core.style.ToStringCreator;

@Entity (name = "Elector")
public class Elector extends User implements Serializable { 
    @NotNull
    @Size (min=3, max=50) 
    private String firstName;

    @NotNull
    @Size (min=3, max=50)
    private String lastName;
    
    @Digits (integer=3, fraction =0)
    @Min(18)
    @Max(100)
    @NotNull
    private Integer age;
    
    private boolean voted;
    
    @Size (min=2, max=2)
    @NotNull
    private String passportSeries;
    
    @NotNull
    @ValidPassportNumber
    @Size (min=7, max=7)
    private String passportNum;
    
    //много избирателей to одному кандидату
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;
   
    public Elector() {}
    
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    @Column(name = "PassportSeries")
    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    @Column(name = "PassportNumber", unique = true)
    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }
    
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    
    @Column(name = "Voted")
    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.age);
        hash = 53 * hash + Objects.hashCode(this.passportSeries);
        hash = 53 * hash + Objects.hashCode(this.passportNum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Elector other = (Elector) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.passportSeries, other.passportSeries)) {
            return false;
        }
        if (!Objects.equals(this.passportNum, other.passportNum)) {
            return false;
        }
        return Objects.equals(this.age, other.age);
    }

    @Override
    public String toString() {
         return new ToStringCreator(this)
            .append("id", this.getId())
            .append("firstName", this.getFirstName())
            .append("lastName", this.getLastName())
            .append("age", this.getAge())
            .append("passportSeries", this.getPassportSeries())
            .append("passportNum", this.getPassportNum())
            .append("Login", this.getLogin())
            .append("Password", this.getPassword())
            .append("Voted", this.isVoted())    
            .toString();
    }  

}
