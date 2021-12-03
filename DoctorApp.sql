DROP DATABASE IF EXISTS DOCTORAPP;
CREATE DATABASE DOCTORAPP;
USE DOCTORAPP;

/* Delete the tables if they already exist */
drop table if exists PublicUsers;
drop table if exists PatientVitals;
drop table if exists Administrator;
drop table if exists Offices;
drop table if exists Reviews;
drop table if exists Reservation;

/* Create the schema for our tables */
create table PublicUsers
(
    uID INT AUTO_INCREMENT,
    name VARCHAR(100) not null,
    username VARCHAR(100) not null,
    password VARCHAR(100) not null,
    primaryDoctor VARCHAR(100),
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(uID),
    UNIQUE(username)
);

create table PatientVitals
(
    uID INT,
    bloodPressure VARCHAR(20),
    glucose INT,
    heartRate INT,
    date DATE,
   PRIMARY KEY(uID, date),
   FOREIGN KEY (uID) REFERENCES PublicUsers(uID) ON UPDATE CASCADE ON DELETE CASCADE
 );

create table Administrator
(
    dID INT AUTO_INCREMENT,
   doctorName VARCHAR(100) not null,
    deaNumber INT,
    prescriptionNumber INT,
    specialty VARCHAR(100),
    PRIMARY KEY (dID),
    UNIQUE (deaNumber),
    UNIQUE (prescriptionNumber)
 );

create table Offices
(
    dID INT,
    cityName VARCHAR(100),
    FOREIGN KEY (dID) REFERENCES Administrator(dID)
 );
 
 create table Reviews
(
    dID INT,
    reviewer VARCHAR(100) not null,
    stars INT,
   PRIMARY KEY (dID, reviewer),
   FOREIGN KEY (dID) REFERENCES Administrator(dID)
 );
 
 create table Reservation
(
    appointmentDate DATE,
    appointmentTime TIME,
    uID INT,
    dID INT,
    FOREIGN KEY (uID) REFERENCES PublicUsers(uID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (dID) REFERENCES Administrator(dID)
 );
 
 insert into PublicUsers (uID, name, username, password, primaryDoctor) values(1000, "John Doe", "jdoe", "jdoe123", "Howard Stewart");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Katie Thomas", "kthomas", "katie567", "Anna Joseph");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Allison Judge", "ajudge", "allycat345", "Kristen Davis");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Josh Richardson", "jrichardson", "richthekid9", "Howard Stewart");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Steve Beck", "sbeck", "sb987", "Christopher Smith");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Nova Starr", "nstarr", "constellations111", "Anna Joseph");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Callie Robbins", "crobbins", "heycallie21", "Kristen Davis");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Matt Jones", "mjones", "jones567", "Christopher Smith");
 insert into PublicUsers (name, username, password, primaryDoctor) values("Kevin Garcia", "kgarcia", "keving123", "Howard Stewart");
--  
 insert into PatientVitals values(1000, "120/80", 100, 70, "2021-06-01");
 insert into PatientVitals values(1001, "110/80", 90, 65, "2021-05-02");
 insert into PatientVitals values(1002, "110/70", 85, 70, "2021-07-13");
 insert into PatientVitals values(1003, "115/80", 87, 60, "2021-10-10");
 insert into PatientVitals values(1004, "130/80", 90, 60, "2021-09-14");
 insert into PatientVitals values(1005, "120/80", 180, 65, "2021-01-01");
 insert into PatientVitals values(1006, "110/80", 85, 120, "2021-02-21");
 insert into PatientVitals values(1007, "110/80", 200, 115, "2021-04-02");
 insert into PatientVitals values(1008, "140/80", 90, 150, "2021-08-1");
 
 insert into Administrator values(1000, "Howard Stewart", 783939, 2474940, "Physician");
 insert into Administrator (doctorName, deaNumber, prescriptionNumber, specialty) values("Kristen Davis", 638935, 1638493, "Physician");
 insert into Administrator (doctorName, deaNumber, prescriptionNumber, specialty) values("Christopher Smith", 763289, 9456784, "Physician");
 insert into Administrator (doctorName, deaNumber, prescriptionNumber, specialty) values("Anna Joseph", 456935, 9263845, "Physician");
 insert into Administrator (doctorName, deaNumber, prescriptionNumber, specialty) values("Gabrielle Anderson", 743946, 8354021, "Dietician");
 insert into Administrator (doctorName, deaNumber, prescriptionNumber, specialty) values("Alex Johnson", 162998, 1163927, "Cardiologist");
 insert into Administrator (doctorName, deaNumber, prescriptionNumber, specialty) values("Mason Miller", 764288, 9985561, "Dietician");
 insert into Administrator (doctorName, deaNumber, prescriptionNumber, specialty) values("Holly Jack", 516682, 1936481, "Cardiologist");
 
 insert into Offices values(1000, "San Jose");
 insert into Offices values (1001, "San Jose");
 insert into Offices values (1002, "San Francisco");
 insert into Offices values (1003, "San Francisco");
 insert into Offices values (1004, "San Jose");
 insert into Offices values (1005, "San Jose");
 insert into Offices values (1006, "San Francisco");
 insert into Offices values (1007, "San Francisco");
 
 insert into Reviews values(1000, "John Doe", 3);
 insert into Reviews values(1005, "John Doe", 5);
 insert into Reviews values(1002, "Steve Beck", 5);
 insert into Reviews values(1001, "Callie Robinson", 2);
 insert into Reviews values(1001, "Allison Judge", 4);
 
 insert into Reservation values("2021-11-05", "15:00:00", 1000, 1000);
 insert into Reservation values("2021-12-13", "16:30:00", 1007, 1007);
 insert into Reservation values("2021-11-21", "12:00:00", 1007, 1002);

-- create relation Archive : uID, updatedAt
CREATE TABLE Archive (
   uID INT,
   updatedAt TIMESTAMP
);
-- stored procedure to archive row using cutoff date
DROP PROCEDURE IF EXISTS haveNotModified;
DELIMITER //
CREATE PROCEDURE haveNotModified (IN cutoff TIMESTAMP)
BEGIN
-- get result and copies to Archive
INSERT INTO Archive
SELECT uID, updatedAt
FROM PublicUsers
WHERE updatedAt < cutoff;

-- delete from PublicUsers
DELETE FROM PublicUsers p
WHERE p.uID IN (
   SELECT a.uID
   FROM Archive a
);
END //
DELIMITER ;



DROP TRIGGER IF EXISTS Check_Duplicate_Before_Insert_Reservation;
delimiter //
CREATE TRIGGER Check_Duplicate_Before_Insert_Reservation
BEFORE INSERT ON Reservation
FOR EACH ROW
BEGIN
	IF exists (select * from Reservation 
    where appointmentDate = NEW.appointmentDate and appointmentTime = NEW.appointmentTime and dID = NEW.dID)
	THEN signal sqlstate '45000' set message_text = 'TriggerError: Duplicate appointments - this doctor is not available at this time';
	END IF;
END;
//
delimiter ;

DROP TRIGGER IF EXISTS Check_Duplicate_Before_Update_Reservation;
delimiter //
CREATE TRIGGER Check_Duplicate_Before_Update_Reservation
BEFORE UPDATE ON Reservation
FOR EACH ROW
BEGIN
	IF exists (select * from Reservation 
    where appointmentDate = NEW.appointmentDate and appointmentTime = NEW.appointmentTime and dID = NEW.dID)
	THEN signal sqlstate '45000' set message_text = 'TriggerError: Duplicate appointments - this doctor is not available at this time';
	END IF;
END;
//
delimiter ;

DROP TRIGGER IF EXISTS Valid_Star_Insert_Reviews;
delimiter //
CREATE TRIGGER Valid_Star_Insert_Reviews
BEFORE INSERT ON Reviews
FOR EACH ROW
BEGIN
	IF NEW.stars>5 or NEW.stars<1
  	THEN signal sqlstate '45000' set message_text = 'TriggerError: Number of stars cannot be less than 1 or greater than 5';
	END IF;
END;
//
delimiter ;

DROP TRIGGER IF EXISTS Valid_Star_Update_Reviews;
delimiter //
CREATE TRIGGER Valid_Star_Update_Reviews
BEFORE UPDATE ON Reviews
FOR EACH ROW
BEGIN
	IF NEW.stars>5 or NEW.stars<1
  	THEN signal sqlstate '45000' set message_text = 'TriggerError: Number of stars cannot be less than 1 or greater than 5';
	END IF;
END;
//
delimiter ;

DROP TRIGGER IF EXISTS Check_User_Duplicate_Before_Insert_Reservation;
delimiter //
CREATE TRIGGER Check_User_Duplicate_Before_Insert_Reservation
BEFORE INSERT ON Reservation
FOR EACH ROW
BEGIN
	IF exists (select * from Reservation 
    where appointmentDate = NEW.appointmentDate and appointmentTime = NEW.appointmentTime and uID = NEW.uID)
	THEN signal sqlstate '45000' set message_text = 'TriggerError: Duplicate appointments - you already have an appointment at this time';
	END IF;
END;
//
delimiter ;

DROP TRIGGER IF EXISTS Check_User_Duplicate_Before_Update_Reservation;
delimiter //
CREATE TRIGGER Check_User_Duplicate_Before_Update_Reservation
BEFORE UPDATE ON Reservation
FOR EACH ROW
BEGIN
	IF exists (select * from Reservation 
    where appointmentDate = NEW.appointmentDate and appointmentTime = NEW.appointmentTime and uID = NEW.uID)
	THEN signal sqlstate '45000' set message_text = 'TriggerError: Duplicate appointments - you already have an appointment at this time';
	END IF;
END;
//
delimiter ;

DROP TRIGGER IF EXISTS update_valid_primaryDoctor_in_PublicUsers;
delimiter //
CREATE TRIGGER update_valid_primaryDoctor_in_PublicUsers
BEFORE UPDATE ON PublicUsers
FOR EACH ROW
BEGIN
	IF NEW.primaryDoctor not in (select doctorName from Administrator)
	THEN SET NEW.primaryDoctor = 'Other Doctor';
	END IF;
END;
//
delimiter ;

DROP TRIGGER IF EXISTS insert_valid_primaryDoctor_in_PublicUsers;
delimiter //
CREATE TRIGGER insert_valid_primaryDoctor_in_PublicUsers
BEFORE INSERT ON PublicUsers
FOR EACH ROW
BEGIN
	IF NEW.primaryDoctor not in (select doctorName from Administrator)
	THEN SET NEW.primaryDoctor='Other Doctor';
	END IF;
END;
//
delimiter ;
