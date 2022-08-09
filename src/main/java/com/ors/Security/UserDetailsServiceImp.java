package com.ors.Security;

import com.ors.Models.Chef;
import com.ors.Models.Customer;
import com.ors.Repositories.ChefRepository;
import com.ors.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired private CustomerRepository customerRepository;
    @Autowired private ChefRepository chefRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer=customerRepository.findByEmail(email);
        Chef chef=chefRepository.findByEmail(email);
        if(customer!=null)return new MyUserDetails(customer);
        else if(chef!=null)return new MyUserDetails(chef);
        else   throw new UsernameNotFoundException("Invalid Email");
    }
}
