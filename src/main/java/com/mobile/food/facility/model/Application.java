package com.mobile.food.facility.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mobile.food.facility.model.lookup.PermitStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "APPLICATION")
public class Application implements Serializable {

	private static final long serialVersionUID = -4422099106689464716L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "RECEIVED")
	private String received;

	@Column(name = "PRIOR_PERMIT")
	private String priorPermit;

	@Column(name = "NOI_SENT")
	private String noiSent;

	@Column(name = "APPROVED")
	private String approved;

	@Column(name = "EXPIRATION_DATE")
	private String expirationDate;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
	private Location location;

	@OneToOne
	@JoinColumn(name = "PERMIT_STATUS_ID", referencedColumnName = "ID")
	private PermitStatus permitStatus;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "FOOD_ID", referencedColumnName = "ID")
	private Food food;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "FACILITY_ID", referencedColumnName = "ID")
	private Facility facility;

}
