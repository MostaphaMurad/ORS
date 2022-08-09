package com.ors.Security;

import com.ors.Models.Chef;
import com.ors.Models.Customer;
import com.ors.Models.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.*;

public class MyUserDetails implements UserDetails {
    private MyUserDetails userDetails;
    public MyUserDetails (){}
    public MyUserDetails(MyUserDetails userDetails){
        this.userDetails=userDetails;
    }
    private Customer customer=new Customer();
    private Chef chef=new Chef();
    public MyUserDetails(Customer customer){
        this.customer=customer;
    }
    public MyUserDetails(Chef chef){
        this.chef=chef;
    }
    public MyUserDetails(Chef chef,Customer customer){
        this.chef=chef;
        this.customer=customer;
    }
    public Map<String,Roles> getLoggedEmailAndRole(){
        Map<String,Roles>roleAndEmail=new TreeMap<>();
        if(chef.getEmail()!=null){
            roleAndEmail.put(chef.getEmail(),chef.getRole());
        }
        else if(customer.getEmail()!=null){
            roleAndEmail.put(customer.getEmail(),customer.getRole());
        }
        return roleAndEmail;
    }
    public String logedFname(){
        if(customer.getFullName().getFname()!=null){
            return customer.getFullName().getFname();
        }
        else if(chef.getUserName()!=null) {
            return chef.getUserName();
        }
        else return "Admin";
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Map<String,Roles>role=new TreeMap<>();
        role=getLoggedEmailAndRole();
        List<SimpleGrantedAuthority>authorities=new ArrayList<>();
        for(Map.Entry<String,Roles>entry:role.entrySet()){
            authorities.add(new SimpleGrantedAuthority(entry.getValue().getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        if(chef.getPassword()!=null)return chef.getPassword();
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        if(chef.getEmail()!=null)return chef.getEmail();
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
