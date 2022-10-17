DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS users;


CREATE TABLE booking (
                    eventID INTEGER NOT NULL AUTO_INCREMENT,
                    eventName VARCHAR(255) NOT NULL,
                    eventStart VARCHAR(255) NOT NULL,
                    eventEnd VARCHAR(255) NOT NULL,
                    eventDetails VARCHAR(255) NOT NULL,
                    userID INTEGER NOT NULL,
                    doctorID INTEGER NOT NULL,
                    PRIMARY KEY (eventID)
);
CREATE TABLE users (
                    id INTEGER NOT NULL AUTO_INCREMENT,
                    username VARCHAR(255) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL,
                    firstName VARCHAR(255) NOT NULL,
                    lastName VARCHAR(255) NOT NULL,
                    permission VARCHAR(255) NOT NULL,
                    PRIMARY KEY (id,username)
    );

INSERT INTO users(username, password, firstName, lastName, permission)
VALUES ('admin', 'password', 'Admin', 'Telehealth', 'Admin');

