# RMIT SEPT 2022 Major Project

# Homy#1

## Members
* Connor Forster 	s3781475
* Bob Qian 		s3840234
* Dillon Whei Teik Teh	s3879594
* Gibril Farah		s3895509
* Connor Pan		s3902312

## Records

* Github repository :https://github.com/BobRMIT/Homy1
* jira Board : https://bobrmit.atlassian.net/jira/software/projects/HOM/boards/1


## Code documentation - Release 0.3.1 - 18/10/2022
* Login
* Signup
* Booking System



To run the application locally :
TBA

### Database

A mySQL docker image is used for this database.

The following sets assume you have docker installed on your system. Visit https://www.docker.com/products/docker-desktop for the installation

To set it up do the following:

Open a command prompt (power shell for windows) in setup/docker/db and run

```
docker build -t sept_db:latest .
```

Once finished it will print out the id for the image, if powershell does not return an image open up docker Desktop and head to Images and click run on the sept_db image:

This should open up a container which can be named by yourself in the optional settings.
I have named mine sept
Run the following command to start the docker container
```
docker run --name=sept -d mysql/mysql-server:latest
```
