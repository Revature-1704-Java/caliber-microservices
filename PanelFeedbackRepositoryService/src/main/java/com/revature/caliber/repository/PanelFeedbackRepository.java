package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimplePanelFeedback;

@RepositoryRestResource(collectionResourceRel = "panelfeedback", path = "panelfeedback")
public interface PanelFeedbackRepository extends JpaRepository<SimplePanelFeedback, Long> {

	@Query("SELECT DISTINCT pf FROM SimplePanelFeedback pf")
	List<SimplePanelFeedback> findAll();
	
	@Query("SELECT DISTINCT pf FROM SimplePanelFeedback pf WHERE pf.panelId= ?1")
	List<SimplePanelFeedback> findAllForPanel(@Param("panelId") Integer panelId);
	
	@Query("SELECT DISTINCT pf FROM SimplePanelFeedback pf WHERE pf.panelId= ?1 and pf.status='Repanel'")
	List<SimplePanelFeedback> findFailedFeedbackByPanel(@Param("panelId") Integer panelId);
}
