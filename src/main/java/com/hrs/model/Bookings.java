package com.hrs.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Bookings {
	
	@Id
	private Long id;
	private Long hotelid;
	private String username;
	 private LocalDate checkInDate;
     private LocalDate checkOutDate;
     private char isCancel ;
	
	public Bookings() {
		
	}

	public Bookings(Long id, Long hotelid, String username, LocalDate checkInDate, LocalDate checkOutDate,
			char isCancel) {
		super();
		this.id = id;
		this.hotelid = hotelid;
		this.username = username;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.isCancel = isCancel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public char getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(char isCancel) {
		this.isCancel = isCancel;
	}

	@Override
	public String toString() {
		return "Bookings [id=" + id + ", hotelid=" + hotelid + ", username=" + username + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", isCancel=" + isCancel + "]";
	}
	

	

	
}
