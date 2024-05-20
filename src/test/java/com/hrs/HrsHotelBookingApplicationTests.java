package com.hrs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.*;
import java.util.Optional;

import com.hrs.exception.BookingNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hrs.model.Bookings;
import com.hrs.repository.HrsRepository;
import com.hrs.service.HrsService;

@SpringBootTest
class HrsHotelBookingApplicationTests {



	@InjectMocks
	private HrsService hrsService;

	@Mock
	private HrsRepository hrsRepository;
	@Test
	void contextLoads() {
	}


	@Test
	void testGetAllBookings_BookingsFound() throws BookingNotFoundException {
		List<Bookings> expectedBookings = new ArrayList<>();
		Bookings booking = new Bookings();
		booking.setIsCancel('N');
		expectedBookings.add(booking);

		when(hrsRepository.findAll()).thenReturn(expectedBookings);

		List<Bookings> actualBookings = hrsService.getAllBooking();

		assertEquals(1, actualBookings.size());
		assertEquals('N', actualBookings.get(0).getIsCancel());
	}
	@Test
	public void getAllBooking_ShouldThrowException_WhenNoActiveBookingsFound() {
		// Arrange
		when(hrsRepository.findAll()).thenReturn(new ArrayList<>());

		// Act & Assert
		assertThrows(BookingNotFoundException.class, () -> hrsService.getAllBooking());
	}




	@Test
	public void addBooking_ShouldAddBooking() {
		// Arrange
		Bookings booking = new Bookings();

		// Act
		String result = hrsService.addBooking(booking);

		// Assert
		assertEquals("successfully added", result);
		verify(hrsRepository, times(1)).save(booking);
	}

	@Test
	public void addBooking_ShouldThrowException_WhenBookingIsNull() {
		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> hrsService.addBooking(null));
	}



	@Test
	public void getBookingById_ShouldReturnBooking_WhenBookingExistsAndIsActive() throws BookingNotFoundException {
		// Arrange
		Bookings booking = new Bookings();
		booking.setIsCancel('N');
		when(hrsRepository.findById(anyLong())).thenReturn(Optional.of(booking));

		// Act
		Bookings result = hrsService.getBookingById(1L);

		// Assert
		assertNotNull(result);
		assertEquals('N', result.getIsCancel());
	}

	@Test
	public void getBookingById_ShouldThrowException_WhenBookingNotFound() {
		// Arrange
		when(hrsRepository.findById(anyLong())).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(BookingNotFoundException.class, () -> hrsService.getBookingById(1L));
	}

	@Test
	public void getBookingById_ShouldThrowException_WhenBookingIsCanceled() {
		// Arrange
		Bookings booking = new Bookings();
		booking.setIsCancel('Y');
		when(hrsRepository.findById(anyLong())).thenReturn(Optional.of(booking));

		// Act & Assert
		assertThrows(BookingNotFoundException.class, () -> hrsService.getBookingById(1L));
	}
	@Test
	public void cancelBooking_ShouldCancelBooking() {
		// Arrange
		Bookings booking = new Bookings();

		// Act
		String result = hrsService.cancelBooking(booking);

		// Assert
		assertEquals("successfully cancel the booking", result);
		verify(hrsRepository, times(1)).save(booking);
	}

	@Test
	public void cancelBooking_ShouldThrowException_WhenBookingIsNull() {
		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> hrsService.cancelBooking(null));
	}


	//canceledList

	@Test
	public void getCanceledList_ShouldReturnCanceledBookings() throws BookingNotFoundException {
		// Arrange
		List<Bookings> bookings = new ArrayList<>();
		Bookings booking = new Bookings();
		booking.setIsCancel('Y');
		bookings.add(booking);

		when(hrsRepository.findAll()).thenReturn(bookings);

		// Act
		List<Bookings> result = hrsService.canceledList();

		// Assert
		assertEquals(1, result.size());
		assertEquals('Y', result.get(0).getIsCancel());
	}

	@Test
	public void getCanceledList_ShouldThrowException_WhenNoCanceledBookingsFound() {
		// Arrange
		when(hrsRepository.findAll()).thenReturn(new ArrayList<>());

		// Act & Assert
		assertThrows(BookingNotFoundException.class, () -> hrsService.canceledList());
	}
}
