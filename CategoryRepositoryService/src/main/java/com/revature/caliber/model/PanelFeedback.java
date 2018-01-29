package com.revature.caliber.model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CALIBER_PANEL_FEEDBACK")
@Cacheable
public class PanelFeedback {

	@Id
	@Column(name = "PANEL_FEEDBACK_ID")
	@SequenceGenerator(name = "PANEL_FEEDBACK_ID_SEQUENCE", sequenceName = "PANEL_FEEDBACK_ID_SEQUENCE", initialValue = 1, allocationSize = 600)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PANEL_FEEDBACK_ID_SEQUENCE")
	private long id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="CATEGORY_ID", nullable=false)
	private Category technology;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "PANEL_STATUS", nullable = false)
	private PanelStatus status;
	
	@Min(0)
	@Max(10)
	@NotNull
	@Column(name = "PANEL_RESULT")
	private int result;
	
	@Column(name = "PANELIST_COMMENTS")
	private String comment;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PANEL_ID", nullable=false)
	@JsonBackReference(value = "feedback")
	private int panelId;

	public PanelFeedback() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getTechnology() {
		return technology;
	}

	public void setTechnology(Category technology) {
		this.technology = technology;
	}

	public PanelStatus getStatus() {
		return status;
	}

	public void setStatus(PanelStatus status) {
		this.status = status;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPanel() {
		return panelId;
	}

	public void setPanel(int panelId) {
		this.panelId = panelId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hashCodeResult = 1;
		hashCodeResult = prime * hashCodeResult + ((comment == null) ? 0 : comment.hashCode());
		hashCodeResult = prime * hashCodeResult + panelId;
		hashCodeResult = prime * hashCodeResult + this.result;
		hashCodeResult = prime * hashCodeResult + ((status == null) ? 0 : status.hashCode());
		hashCodeResult = prime * hashCodeResult + ((technology == null) ? 0 : technology.hashCode());
		return hashCodeResult;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelFeedback other = (PanelFeedback) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (panelId == 0) {
			if (other.panelId != 0)
				return false;
		} 
		if (result != other.result)
			return false;
		if (status != other.status)
			return false;
		if (technology == null) {
			if (other.technology != null)
				return false;
		} else if (!technology.equals(other.technology))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PanelFeedback [id=" + id + ", technology=" + technology + ", status=" + status + ", result=" + result
				+ ", comment=" + comment + "]";
	}
	
}
