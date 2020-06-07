/* Create the database */
create database HsptlMgmt;
use HsptlMgmt;

/* Create a new user with password . This will be used connect from our application */
create user 'svcuser'@'%' IDENTIFIED BY 'Testpassword';

/*Grant al the privillages to the new user on newly created Database*/
GRANT ALL ON HsptlMgmt.* TO 'svcuser'@'%';

/*Create table patient*/
create table patient(
	id int PRIMARY KEY,
	first_name varchar(20) NOT NULL,
	last_name varchar(30) NOT NULL,
	phone varchar(30) NOT NULL UNIQUE,
	Gender ENUM('Male', 'Female', 'Undisclosed') NOT NULL,
	disease varchar(30) NOT NULL,
	provider_name varchar(30),
	copay decimal(10,5),
	status boolean
);

/*create patient_sequence*/
CREATE TABLE PATIENT_SEQUENCE (
	id INT NOT NULL,
  next_val INT
);

/*Insert into Sequence*/
INSERT INTO PATIENT_SEQUENCE (id, next_val) VALUES (4210,4211);


create table doctor
(
	id int PRIMARY KEY AUTO_INCREMENT,
	first_name varchar(20),
	last_name varchar(30),
	speciality varchar(30)
);

create table patients_doctors
(
	patient_id int,
	doctor_id int,
	foreign KEY(patient_id)
	references patient(id),
	foreign KEY(doctor_id)
	references doctor(id)
);

create table appointment
(
	id int PRIMARY KEY AUTO_INCREMENT,
	patient_id int,
	doctor_id int,
	appointment_time datetime,
	started TINYINT(1),
	ended TINYINT(1),
	reason varchar(200),
	foreign KEY(patient_id)
	references patient(id),
	foreign KEY(doctor_id)
	references doctor(id)	
);

create table patient(
id int PRIMARY KEY AUTO_INCREMENT,
first_name varchar(20),
last_name varchar(30),
phone varchar(30),
provider_name varchar(30),
copay decimal(10,5)
)
