insert into users values
(1,'john'),
(2,'mary');

insert into apartment values
(1,'BIG',50,2999),
(2,'MEDIUM',25,1999),
(3,'SMALL',10,999);

insert into booked_apartments values
(1,1),
(1,2),
(2,1);

insert into additional_option values
(1,'breakfast',100),
(2,'cleaning',50);

insert into apartment_option values
(1,1),
(1,2),
(2,1);

insert into booked_dates(id,booked_date,user_name,date_id) values
(1,'2018-11-25','john',1),
(2,'2018-09-24','mary',1),
(3,'2018-11-25','john',2);

