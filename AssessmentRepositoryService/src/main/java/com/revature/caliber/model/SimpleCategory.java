package com.revature.caliber.model;

import java.io.Serializable;

public class SimpleCategory implements Serializable {
	private static final long serialVersionUID = 3363756954535297728L;

	private int categoryId;
	private String skillCategory;
	private boolean active;

	/**
	 * Instantiates a new Category.
	 */
	public SimpleCategory() {
		super();
	}

	/**
	 * Create new category
	 * 
	 * @param skillCategory
	 * @param active
	 */
	public SimpleCategory(String skillCategory, boolean active) {
		super();
		this.skillCategory = skillCategory;
		this.active = active;
	}

	/**
	 * Gets category id.
	 *
	 * @return the category id
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets category id.
	 *
	 * @param categoryId
	 *            the category id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Gets skill category.
	 *
	 * @return the skill category
	 */
	public String getSkillCategory() {
		return skillCategory;
	}

	/**
	 * Sets skill category.
	 *
	 * @param skillCategory
	 *            the skill category
	 */
	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((skillCategory == null) ? 0 : skillCategory.hashCode());
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
		SimpleCategory other = (SimpleCategory) obj;
		if (active != other.active)
			return false;
		if (skillCategory == null) {
			if (other.skillCategory != null)
				return false;
		} else if (!skillCategory.equals(other.skillCategory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return skillCategory;
	}
	
}
