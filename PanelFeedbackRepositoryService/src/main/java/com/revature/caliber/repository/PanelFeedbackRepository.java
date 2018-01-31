package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.PanelFeedback;

@Repository
public interface PanelFeedbackRepository extends JpaRepository<PanelFeedback, Long> {

	@Query("SELECT DISTINCT pf FROM PanelFeedback pf")
	List<PanelFeedback> findAll();
	
	@Query("SELECT DISTINCT pf FROM PanelFeedback pf WHERE pf.panelId= ?1")
	List<PanelFeedback> findAllForPanel(int panelId);
	
	@Query("SELECT DISTINCT pf FROM PanelFeedback pf WHERE pf.panelId= ?1 and pf.status='Repanel'")
	List<PanelFeedback> findFailedFeedbackByPanel(int panelId);
}
