package com.hrs.controller;

import java.util.List;
import java.util.Optional;

import com.hrs.exception.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.model.Bookings;
import com.hrs.service.HrsService;



@RestController
public class HrsController {

	
	@Autowired
	private HrsService hrsService;



	/**
	 * Retrieves all bookings.
	 *
	 * @return A list of all bookings.
	 * @throws BookingNotFoundException if no bookings are found.
	 */
	@RequestMapping("/bookings")
	public List <Bookings> getAllBooking() throws BookingNotFoundException
	{
		return hrsService.getAllBooking();
		
	}


	/**
	 * Retrieves a booking by its ID.
	 *
	 * @param id The ID of the booking to retrieve.
	 * @return The booking with the specified ID.
	 * @throws BookingNotFoundException if the booking with the given ID is not found.
	 */
	
	@RequestMapping(value="/booking/{id}",method=RequestMethod.GET)
	public Bookings getBookingById(@PathVariable Long id) throws BookingNotFoundException {
		return hrsService.getBookingById(id);
		
	}




	/**
	 * Adds a new booking.
	 *
	 * @param bookings The booking to add.
	 * @return A message indicating the successful addition.
	 */
		
	@RequestMapping(value="/booking",method=RequestMethod.POST)
	
	public String addBooking(@RequestBody Bookings bookings)
	{

		 return hrsService.addBooking(bookings);
		
						
	}



	/**
	 * Cancels a booking.
	 *
	 * @param bookings The booking to cancel.
	 * @return A message indicating the successful cancellation.
	 */
@RequestMapping(value="/booking/cancel",method=RequestMethod.PUT)
	
	public String cancelBooking(@RequestBody Bookings bookings)
	{
	return hrsService.cancelBooking(bookings);
	}




	/**
	 * Retrieves all canceled bookings.
	 *
	 * @return A list of all canceled bookings.
	 * @throws BookingNotFoundException if no canceled bookings are found.
	 */


	@RequestMapping(value="/booking/allCanceled",method=RequestMethod.GET)
	public List<Bookings> canceledList() throws BookingNotFoundException
	{
		return hrsService.canceledList();
	}


}
