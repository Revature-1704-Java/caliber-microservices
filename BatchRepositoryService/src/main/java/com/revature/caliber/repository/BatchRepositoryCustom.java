package com.revature.caliber.repository;

import java.util.List;

import com.revature.caliber.model.SimpleBatch;

public interface BatchRepositoryCustom {
	public List<SimpleBatch>findAllCurrent();
	public List<SimpleBatch>findAllCurrent(int trainerId);
}
