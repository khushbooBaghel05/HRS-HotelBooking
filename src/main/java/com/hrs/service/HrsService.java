package com.hrs.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import com.hrs.exception.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hrs.model.Bookings;
import com.hrs.repository.HrsRepository;




@Service
public class HrsService {
	
	@Autowired 
	private HrsRepository hrsrepository;


	/**
	 * Retrieves all non-canceled bookings.
	 *
	 * @return A list of non-canceled bookings.
	 * @throws BookingNotFoundException if no non-canceled bookings are found.
	 */

	public List<Bookings> getAllBooking() throws BookingNotFoundException {
		List<Bookings> l = new ArrayList<>();
		hrsrepository.findAll().forEach(l::add);

		List<Bookings> bookings =l.stream()
				.filter(b -> b.getIsCancel() == 'N')
				.collect(Collectors.toList());

		if (bookings.isEmpty()) {
			throw new BookingNotFoundException("Booking Not Found");
		}
		return bookings;
	}



	/**
	 * Adds a new booking.
	 *
	 * @param bookings The booking to add.
	 * @return A message indicating the successful addition.
	 * @throws IllegalArgumentException if the provided booking is null.
	 */
	public String addBooking(Bookings bookings) {

		if (bookings == null) {
			throw new IllegalArgumentException("please provide proper data");
		}
		hrsrepository.save(bookings);
		return "successfully added";
	}



	/**
	 * Retrieves a booking by its ID.
	 *
	 * @param id The ID of the booking to retrieve.
	 * @return The booking with the specified ID.
	 * @throws BookingNotFoundException if the booking is not found or is canceled.
	 */
	public Bookings getBookingById(Long id) throws BookingNotFoundException {
		Bookings booking = hrsrepository.findById(id)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found "));
		if (booking.getIsCancel() == 'Y') {
			throw new BookingNotFoundException("Booking already canceled");
		}
		return booking;
}


	/**
	 * Cancels a booking.
	 *
	 * @param bookings The booking to cancel.
	 * @return A message indicating the successful cancellation.
	 * @throws IllegalArgumentException if the provided booking is null.
	 */
public String cancelBooking(Bookings bookings)
	{
		if (bookings == null) {
			throw new IllegalArgumentException("please provide proper data");
		}
		hrsrepository.save(bookings);
		return "successfully cancel the booking";
		
	}


	/**
	 * Retrieves all canceled bookings.
	 *
	 * @return A list of canceled bookings.
	 * @throws BookingNotFoundException if no canceled bookings are found.
	 */
	public List<Bookings> canceledList() throws BookingNotFoundException {

		List<Bookings> l = new ArrayList<>();
		hrsrepository.findAll().forEach(l::add);

		List<Bookings> canceledBookings =l.stream()
				.filter(b -> b.getIsCancel() == 'Y')
				.collect(Collectors.toList());


		if (canceledBookings.isEmpty()) {
			throw new BookingNotFoundException("No canceled bookings found");
		}

		return canceledBookings;
	}
}
