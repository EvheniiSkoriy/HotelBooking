# HotelBooking

It is a RESTFul web application where you can do next actions.

  1. View list of available rooms (room have a number, category, price, additional options
like breakfast, cleaning with additional cost) for specified dates.
  2. View rooms filtered by category.
  3. Create user.
  4. User can book the room for specified days.
  5. User can view his booking.
  6. User can get the total price of the booking (room for dates period + cost of additional
options).
  7. View all bookings for the hotel.
  
  For this application you must have jdk. You have to go to application's folder, open terminal and run next command 
 `mvn:spring-boot run` 
And now more details about each endpoint.
####  1: View list of available rooms (room have a number, category, price, additional options
#### like breakfast, cleaning with additional cost) for specified dates.

For this endpoint you need to make `GET` request on `http://localhost:9000/{date}` where date is the date you want to see available rooms.

#### 2. View rooms filtered by category.

For this enpoint you need to make `GET` request on `http://localhost:9000/{date}?sort=category` where sort means that you make sort by category.

#### 3. Create user.

For this enpoint you need to make `POST` request on ``http://localhost:9000/register``.
Example JSON that you have to send: `{"name":"scot"}`. If you try to insert name that contains in the database , you will get
HTTP status NO_CONTENT and name will not add to the database. Name must be unique for each user.

#### 4. User can book the room for specified days.

For this enpoint you need to make `POST` request on `http://localhost:9000/hotel/book`.
Example JSON that you have to send:
`{"name":"mary",
"roomId":3,
"date":"2020-04-23",
"duration":3,
"additionalOptions":[]}`
 Where name is a name who books, roomId is id of apartment that you want to book, date is date when you want to start living,
 duration is duration of your living in the apartment, additionalOptions are options that you want to offer for your room like
breakfast, cleaning.
If you inputed the name that no contain in database you would get `UserNotFoundException`.
If you inputed the roomId that no contain in database you would get `ApartmentNotFoundException`.
If you inputed the date that was booked earlier you would get `ApartmentWasBookedException`.

####   5. User can view his booking.
For this enpoint you need to make `GET` request on `http://localhost:9000/hotel/{username}`.
Where username is name of the user for which his booked apartments will be displayed.

#### 6. User can get the total price of the booking (room for dates period + cost of additional
#### options).

For this enpoint you need to make `GET` request on `http://localhost:9000/hotel/{username}/price`.
Where username is name of the user for which price of apartments will be displayed.

#### 7. View all bookings for the hotel.

For this enpoint you need to make `GET` request on `http://localhost:9000/hotel/booked`.
