 package com.revature.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Trainee.
 * 
 * (NOTE) Further iterations should include the following from the Salesforce:
 * recruiter_name, account_name, project_completion This way we can analyze
 * performance based on where they went to college, who recruited them, and if
 * they finished RevaturePro.
 */
@Entity
@Table(name = "CALIBER_TRAINEE")
@Cacheable
public class Trainee implements Serializable {

	private static final long serialVersionUID = -9090223980655307018L;
	
	@Id
	@Column(name = "TRAINEE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAINEE_ID_SEQUENCE")
	@SequenceGenerator(name = "TRAINEE_ID_SEQUENCE", sequenceName = "TRAINEE_ID_SEQUENCE")
	private int traineeId;

	@Column(name = "RESOURCE_ID")
	private String resourceId;

	@NotEmpty
	@Column(name = "TRAINEE_NAME")
	private String name;

	@NotEmpty
	@Email
	@Column(name = "TRAINEE_EMAIL", nullable = false)
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "TRAINING_STATUS")
	private TrainingStatus trainingStatus;
	
	@NotEmpty
	@Column(name= "BATCH_ID")
	private String batchid;

	

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "SKYPE_ID")
	private String skypeId;

	@Column(name = "PROFILE_URL")
	private String profileUrl;
	
	// new columns
	@Column(name = "RECRUITER_NAME")
	private String recruiterName;
	
	@Column(name = "COLLEGE")
	private String college;
	
	@Column(name = "DEGREE")
	private String degree;
	
	@Column(name = "MAJOR")
	private String major;
	
	@Column(name = "TECH_SCREENER_NAME")
	private String techScreenerName;
	
	@Column(name = "REVPRO_PROJECT_COMPLETION")
	private String projectCompletion;
	// end of new columns
	

	
	public Trainee() {
		super();
	}

	/**
	 * Constructor used mostly for testing. Default TrainingStatus as Training
	 * @param name
	 * @param resourceId
	 * @param email
	 * @param batch
	 */
	public Trainee(String name, String resourceId, String email, String batchId) {
		super();
		this.name = name;
		this.resourceId = resourceId;
		this.email = email;
		this.trainingStatus = TrainingStatus.Training;
		this.batchid = batchId;
	}

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TrainingStatus getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(TrainingStatus trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchid == null) ? 0 : batchid.hashCode());
		result = prime * result + ((college == null) ? 0 : college.hashCode());
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((profileUrl == null) ? 0 : profileUrl.hashCode());
		result = prime * result + ((projectCompletion == null) ? 0 : projectCompletion.hashCode());
		result = prime * result + ((recruiterName == null) ? 0 : recruiterName.hashCode());
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
		result = prime * result + ((skypeId == null) ? 0 : skypeId.hashCode());
		result = prime * result + ((techScreenerName == null) ? 0 : techScreenerName.hashCode());
		result = prime * result + traineeId;
		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
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
		Trainee other = (Trainee) obj;
		if (batchid == null) {
			if (other.batchid != null)
				return false;
		} else if (!batchid.equals(other.batchid))
			return false;
		if (college == null) {
			if (other.college != null)
				return false;
		} else if (!college.equals(other.college))
			return false;
		if (degree == null) {
			if (other.degree != null)
				return false;
		} else if (!degree.equals(other.degree))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (profileUrl == null) {
			if (other.profileUrl != null)
				return false;
		} else if (!profileUrl.equals(other.profileUrl))
			return false;
		if (projectCompletion == null) {
			if (other.projectCompletion != null)
				return false;
		} else if (!projectCompletion.equals(other.projectCompletion))
			return false;
		if (recruiterName == null) {
			if (other.recruiterName != null)
				return false;
		} else if (!recruiterName.equals(other.recruiterName))
			return false;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		if (skypeId == null) {
			if (other.skypeId != null)
				return false;
		} else if (!skypeId.equals(other.skypeId))
			return false;
		if (techScreenerName == null) {
			if (other.techScreenerName != null)
				return false;
		} else if (!techScreenerName.equals(other.techScreenerName))
			return false;
		if (traineeId != other.traineeId)
			return false;
		if (trainingStatus != other.trainingStatus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId +", name=" + name + ", email=" + email + ", trainingStatus="
				+ trainingStatus + ", major=" + major +  "]";
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTechScreenerName() {
		return techScreenerName;
	}

	public void setTechScreenerName(String techScreenerName) {
		this.techScreenerName = techScreenerName;
	}

	public String getProjectCompletion() {
		return projectCompletion;
	}

	public void setProjectCompletion(String projectCompletion) {
		this.projectCompletion = projectCompletion;
	}
}
