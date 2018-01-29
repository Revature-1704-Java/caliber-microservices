package com.revature.caliber.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CALIBER_ASSESSMENT")
@Cacheable
public class Assessment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ASSESSMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSESSMENT_ID_SEQUENCE")
	@SequenceGenerator(name = "ASSESSMENT_ID_SEQUENCE", sequenceName = "ASSESSMENT_ID_SEQUENCE")
	private long assessmentId;

	@Min(value = 1)
	@Column(name = "RAW_SCORE", nullable = false)
	private int rawScore;

	@Column(name = "ASSESSMENT_TITLE")
	private String title;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "ASSESSMENT_TYPE", nullable = false)
	private AssessmentType type;

	@Min(value = 1)
	@Column(name = "WEEK_NUMBER", nullable = false)
	private short week;

	@NotNull
	@Column(name = "BATCH_ID")
	private int batchId;

	@NotNull
	@Column(name = "ASSESSMENT_CATEGORY", nullable = false)
	private int category;

	public Assessment() {
		super();
	}

	public Assessment(long assessmentId, int rawScore, String title, AssessmentType type, short week, int batchId,
			int category) {
		super();
		this.assessmentId = assessmentId;
		this.rawScore = rawScore;
		this.title = title;
		this.type = type;
		this.week = week;
		this.batchId = batchId;
		this.category = category;
	}

	public long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getRawScore() {
		return rawScore;
	}

	public void setRawScore(int rawScore) {
		this.rawScore = rawScore;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AssessmentType getType() {
		return type;
	}

	public void setType(AssessmentType type) {
		this.type = type;
	}

	public short getWeek() {
		return week;
	}

	public void setWeek(short week) {
		this.week = week;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + category;
		result = prime * result + rawScore;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + week;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assessment other = (Assessment) obj;
		if (batchId != other.batchId)
			return false;
		if (category != other.category)
			return false;
		if (rawScore != other.rawScore)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (week != other.week)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", rawScore=" + rawScore + ", title=" + title + ", type="
				+ type + ", week=" + week + ", batchId=" + batchId + ", category=" + category + "]";
	}
}
