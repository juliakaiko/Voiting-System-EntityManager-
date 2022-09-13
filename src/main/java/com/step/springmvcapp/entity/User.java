package com.step.springmvcapp.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass // поля и аннотации этого класса наследуются наследниками
public class User extends BaseEntity implements Serializable { // 
    
    @NotEmpty
    @Size (min=3, max=30)
    private String login;
    
    @NotEmpty
    @Size (min=7, max=100)
    private String password;

    public User() {}
   
    @Column (name="Login", unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column (name="Password", unique = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.login);
        hash = 79 * hash + Objects.hashCode(this.password);
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
        final User other = (User) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + '}';
    }
    
}
