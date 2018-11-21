drop table  if exists users;
create table users (
id int not null,
name varchar (68) default null,
primary key (id),
);

drop table  if exists apartment;
create table apartment(
id int not null auto_increment,
number int not null,
category varchar not null,
price double not null,
primary key(id),
);

drop table  if exists booked_apartments;
create table booked_apartments(
user_id int not null,
apartment_id int not null,
primary key (user_id,apartment_id),
foreign key (user_id) references users(id),
foreign key (apartment_id) references apartment(id)
);

drop table  if exists additional_option;
create table additional_option(
id int not null auto_increment,
name varchar not null,
price double not null,
primary key (id)
);


drop table  if exists booked_dates;
create table booked_dates(
id int not null auto_increment,
number_of_apartment int not null,
date date not null,
primary key (id),
foreign key (number_of_apartment) references apartment(number)
);

drop table if exists apartment_option;
create table apartment_option(
apartment_number int not null,
option_id int not null,
primary key (apartment_number,option_id),
foreign key (apartment_number) references apartment(number),
foreign key (option_id) references additional_option(id)
);

