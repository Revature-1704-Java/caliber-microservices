package com.revature.caliber.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Imported from Caliber
 */


public enum QCStatus implements Serializable {
	@JsonProperty("Superstar")
	Superstar,
	@JsonProperty("Good")
	Good,
	@JsonProperty("Average")
	Average,
	@JsonProperty("Poor")
	Poor,
	@JsonProperty("Undefined")
	Undefined
}
