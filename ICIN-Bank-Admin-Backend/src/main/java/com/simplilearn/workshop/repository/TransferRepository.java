package com.simplilearn.workshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.workshop.model.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Integer> {
	
	public List<Transfer> findBySaccount(long saccount);
	public List<Transfer> findByRaccount(long racoount);
}
