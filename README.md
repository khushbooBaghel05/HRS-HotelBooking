 HRS-HotelBooking

Description:-
HRS-HotelBooking is a Spring Boot application designed to manage hotel bookings. It provides a RESTful API for creating, retrieving, updating, and canceling hotel bookings.

**Features**:-
--Create new bookings
--Retrieve all bookings
--Retrieve a booking by ID
--Cancel a booking
--Retrieve all canceled bookings


**Technologies Used**

*Java 17
*Spring Boot 3.2.5
*Spring Data JPA
*MySQL
*Mockito
*JUnit 5


**Prerequisites**
Before you begin, ensure you have met the following requirements:

You have installed Java 17 or higher
You have installed Maven
You have a MySQL database running


**Installation**

1)Clone the repository
git clone https://github.com/khushbooBaghel05/hrs-hotelBooking.git
cd HRS-HotelBooking

2).Configure the database

Open src/main/resources/application.properties
Update the database connection details:

`spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update`


3)Build the project using Maven
mvn clean install

4)Run the application
mvn spring-boot:run


**Usage**
Once the application is running, you can use tools like Postman or curl to interact with the API.

Example Requests:-

1)Get all bookings
curl -X GET http://localhost:8080/bookings

2)Create a new booking
curl -X POST http://localhost:8080/booking -H "Content-Type: application/json" -d '{"name": "John Doe", "isCancel": "N"}'

**API Endpoints**
Method	   Endpoint   	             Description

GET	     `/bookings`	            Get all bookings
GET	   `  /booking/{id}	`           Get booking by ID
POST	` /bookings`	            Create a new booking
PUT	     `/booking/cancel`	        Cancel a booking
GET	     `/booking/allCanceled`	    Get all canceled bookings



**Running Tests**
To run the tests, execute the following command: mvn test

This will run all unit tests and integration tests in the project.

**Test Cases**

#### 1. `testGetAllBooking()`
- Description: Tests the retrieval of all bookings that are not canceled.
- Setup: Mocks the `findAll()` method of `HrsRepository` to return a predefined list of bookings.
- Assertions:
    - Verifies the size of the returned list.
    - Checks that the names of the bookings match the expected values.

#### 2. `testAddBooking()`
- Description: Tests the addition of a new booking.
- Setup: Creates a new `Bookings` object and calls the `addBooking` method.
- Assertions:
    - Ensures that an `IllegalArgumentException` is thrown if the booking is `null`.
    - Verifies the booking is saved successfully.

#### 3. `testGetBookingById()`
- Description: Tests the retrieval of a booking by its ID.
- Setup: Mocks the `findById()` method of `HrsRepository` to return an optional booking.
- Assertions:
    - Checks that a `BookingNotFoundException` is thrown if the booking does not exist or is canceled.
    - Verifies the details of the retrieved booking.

#### 4. `testCancelBooking()`
- Description: Tests the cancellation of an existing booking.
- Setup: Creates a new `Bookings` object and calls the `cancelBooking` method.
- Assertions:
    - Ensures that an `IllegalArgumentException` is thrown if the booking is `null`.
    - Verifies the booking is canceled successfully.

#### 5. `testGetAllCanceled()`
- Description: Tests the retrieval of all canceled bookings.
- Setup: Mocks the `findAll()` method of `HrsRepository` to return a predefined list of bookings.
- Assertions:
    - Verifies the size of the returned list of canceled bookings.
    - Checks that a `BookingNotFoundException` is thrown if no canceled bookings are found.


**Contributing**
Contributions are always welcome! Please follow these steps to contribute:

Fork the repository.
Create a new branch.
Make your changes.
Submit a pull request.
Please make sure to update tests as appropriate.


**Contact**
If you have any questions or suggestions, feel free to contact us at khushboo.baghel.personal@gmail
