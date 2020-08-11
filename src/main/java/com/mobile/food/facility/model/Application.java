package com.mobile.food.facility.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mobile.food.facility.model.lookup.PermitStatus;

@Entity
@Table(name = "APPLICATION")
public class Application {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "RECEIVED")
	private String received;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PermitStatus getPermitStatus() {
		return permitStatus;
	}

	public void setPermitStatus(PermitStatus permitStatus) {
		this.permitStatus = permitStatus;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Column(name = "PRIOR_PERMIT")
	private String priorPermit;

	@Column(name = "NOI_SENT")
	private Date noiSent;

	@Column(name = "APPROVED")
	private Date approved;

	@Column(name = "EXPIRATION_DATE")
	private Date expirationDate;

	@OneToOne
	@JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
	private Location location;

	@OneToOne
	@JoinColumn(name = "PERMIT_STATUS_ID", referencedColumnName = "ID")
	private PermitStatus permitStatus;

	@OneToOne
	@JoinColumn(name = "FOOD_ID", referencedColumnName = "ID")
	private Food food;

	@OneToOne
	@JoinColumn(name = "FACILITY_ID", referencedColumnName = "ID")
	private Facility facility;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public String getPriorPermit() {
		return priorPermit;
	}

	public void setPriorPermit(String priorPermit) {
		this.priorPermit = priorPermit;
	}

	public Date getNoiSent() {
		return noiSent;
	}

	public void setNoiSent(Date noiSent) {
		this.noiSent = noiSent;
	}

	public Date getApproved() {
		return approved;
	}

	public void setApproved(Date approved) {
		this.approved = approved;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
