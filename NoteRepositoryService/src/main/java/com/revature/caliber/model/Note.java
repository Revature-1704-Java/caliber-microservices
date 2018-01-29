package com.revature.caliber.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Note implements Serializable {
	private static final long serialVersionUID = 7785756076682011103L;

	@Id
	@Column(name = "NOTE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE_ID_SEQUENCE")
	@SequenceGenerator(name = "NOTE_ID_SEQUENCE", sequenceName = "NOTE_ID_SEQUENCE")
	private Integer noteId;

	@Length(min = 0, max = 4000)
	@Column(name = "NOTE_CONTENT")
	private String noteContent;

	@Min(value = 1)
	@Column(name = "WEEK_NUMBER")
	private Short weekNumber;

	@Column(name = "BATCH_ID", nullable = true)
	private Integer batchId;

	@Column(name = "TRAINEE_ID", nullable = true)
	private Integer traineeId;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "MAX_VISIBILITY")
	private TrainerRole maxVisibility;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "NOTE_TYPE")
	private NoteType noteType;

	@Column(name = "IS_QC_FEEDBACK", nullable = false)
	private boolean qcFeedback;

	@Enumerated(EnumType.STRING)
	@Column(name = "QC_STATUS", nullable = true)
	private QCStatus qcStatus;

	public Note() {
		super();
	}

	public Note(Integer noteId, String noteContent, Short weekNumber, Integer batchId, Integer traineeId,
			TrainerRole maxVisibility, NoteType noteType, boolean qcFeedback, QCStatus qcStatus) {
		super();
		this.noteId = noteId;
		this.noteContent = noteContent;
		this.weekNumber = weekNumber;
		this.batchId = batchId;
		this.traineeId = traineeId;
		this.maxVisibility = maxVisibility;
		this.noteType = noteType;
		this.qcFeedback = qcFeedback;
		this.qcStatus = qcStatus;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Short getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Short weekNumber) {
		this.weekNumber = weekNumber;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public TrainerRole getMaxVisibility() {
		return maxVisibility;
	}

	public void setMaxVisibility(TrainerRole maxVisibility) {
		this.maxVisibility = maxVisibility;
	}

	public NoteType getNoteType() {
		return noteType;
	}

	public void setNoteType(NoteType noteType) {
		this.noteType = noteType;
	}

	public boolean isQcFeedback() {
		return qcFeedback;
	}

	public void setQcFeedback(boolean qcFeedback) {
		this.qcFeedback = qcFeedback;
	}

	public QCStatus getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(QCStatus qcStatus) {
		this.qcStatus = qcStatus;
	}

}
