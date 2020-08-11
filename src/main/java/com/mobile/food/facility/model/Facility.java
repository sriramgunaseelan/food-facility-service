package com.mobile.food.facility.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mobile.food.facility.model.lookup.FacilityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "FACILITY")
public class Facility implements Serializable {

	private static final long serialVersionUID = -4422099106689464716L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "SCHEDULE")
	private String schedule;

	@Column(name = "DAYHOURS")
	private String dayhours;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
	private FacilityType type;

}
