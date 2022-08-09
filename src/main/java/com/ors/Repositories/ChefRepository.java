package com.ors.Repositories;

import com.ors.Models.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends JpaRepository<Chef,Integer> {
    Chef findByEmail(String email);
}
