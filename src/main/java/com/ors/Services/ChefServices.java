package com.ors.Services;

import com.ors.Models.Chef;
import com.ors.Repositories.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChefServices {
    @Autowired private ChefRepository chefRepository;

    public Chef getCurrentChef(int i) {
        Chef chef=chefRepository.findById(i).get();
        return chef;
    }

    public Chef getLoggedChef(String emailChef) {
        Chef chef=chefRepository.findByEmail(emailChef);
        return chef;
    }
}
