package com.fdmgroup.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.entities.Shareholder;

public interface ShareholderRep extends JpaRepository<Shareholder, Integer> {

}
