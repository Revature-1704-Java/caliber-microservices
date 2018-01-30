package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.model.Trainer;
import com.revature.caliber.model.TrainerRole;

@Repository
public interface TrainerDAO extends JpaRepository<Trainer, Integer> {

	@Query("select distinct t from Trainer t where t.tier<>com.revature.caliber.model.TrainerRole.ROLE_INACTIVE")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<Trainer> findAll();

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	Trainer findByTrainerId(int id);
	
	@Query("select distinct t.title from Trainer t")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<String> findAllTrainerTitles();
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	Trainer findByEmail(String email);
	
	@Modifying
	@Query("update Trainer t set t.name = ?1, t.title = ?2, t.tier = ?3 where t.trainerId = ?4")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTrainerInfoById(String name, String title, TrainerRole tier, Integer userId);
}