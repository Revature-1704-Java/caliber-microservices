package com.revature.caliber.repository;

import java.util.List;

import com.revature.caliber.model.SimpleBatch;

public interface BatchRepositoryCustom {
	/**
	 * define current as a batch that started within the past 30 days and yet to end;
	 * returns a list of simple batches
	 * @return
	 */
	public List<SimpleBatch>findAllCurrent();
	/**
	 * 
	 * current batches by trainer
	 * 
	 * @param trainerId
	 * @return
	 */
	public List<SimpleBatch>findAllCurrent(int trainerId);
}
