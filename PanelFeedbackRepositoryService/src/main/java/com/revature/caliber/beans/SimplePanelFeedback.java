package com.revature.caliber.beans;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CALIBER_PANEL_FEEDBACK")
@Cacheable
public class SimplePanelFeedback implements Serializable {

	private static final long serialVersionUID = 8110514896738077383L;

	@Id
	@Column(name = "PANEL_FEEDBACK_ID")
	@SequenceGenerator(name = "PANEL_FEEDBACK_ID_SEQUENCE", sequenceName = "PANEL_FEEDBACK_ID_SEQUENCE", initialValue = 1, allocationSize = 600)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PANEL_FEEDBACK_ID_SEQUENCE")
	private long id;

	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private int categoryId;

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

	@JoinColumn(name = "PANEL_ID", nullable = false)
	private int panelId;

	public SimplePanelFeedback() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setTechnology(int categoryId) {
		this.categoryId = categoryId;
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

	public int getPanelId() {
		return panelId;
	}

	public void setPanelId(int panelId) {
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
		hashCodeResult = prime * hashCodeResult + categoryId;
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
		SimplePanelFeedback other = (SimplePanelFeedback) obj;
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
		if (categoryId == 0) {
			if (other.categoryId != 0)
				return false;
		} else if (categoryId != other.categoryId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PanelFeedback [id=" + id + ", categoryId=" + categoryId + ", status=" + status + ", result=" + result
				+ ", comment=" + comment + "]";
	}

}