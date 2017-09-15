package com.theironyard.invoicify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theironyard.invoicify.models.BillingRecord;

public interface BillingRecordRepository extends JpaRepository<BillingRecord, Long> {

	//client is where we will be finding the ID, spring does magic here to make it a method we can use 
	List<BillingRecord> findByClientIdAndLineItemIsNull(long clientId); 

	List<BillingRecord> findByIdIn(long[] recordIds); 
	
	
}
