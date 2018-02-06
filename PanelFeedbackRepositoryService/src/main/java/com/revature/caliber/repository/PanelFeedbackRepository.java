package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.beans.SimplePanelFeedback;

@Repository
public interface PanelFeedbackRepository extends JpaRepository<SimplePanelFeedback, Long> {

	List<SimplePanelFeedback> findByPanelId(int id);

	List<SimplePanelFeedback> findByPanelIdAndStatus(int id, PanelStatus status);
}
