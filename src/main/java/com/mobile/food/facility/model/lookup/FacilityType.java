package com.mobile.food.facility.model.lookup;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "FACILITY_TYPE")
public class FacilityType implements Serializable {

	private static final long serialVersionUID = -4422099106689464716L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

}
