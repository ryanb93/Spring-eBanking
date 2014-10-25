# Backend - an Spring M*C application for delivering json via RESTful service

## Setup Instructions

- Install Gradle (NOT REQUIRED CAN USE `gradlew`)
	* Mac OS X via brew: `brew install gradle`
	* Windows / OS X: [DOWNLOAD](https://services.gradle.org/distributions/gradle-2.1-all.zip)

- Clone the project

- `cd` to the Backend folder 

- Run: `gradle check` / `./gradlew check` in Terminal. This will pull down all the libraries and configure project for NetBeans.

- Open NetBeans: `Tools > Plugins > Available Plugins > Search: gradle > Select Gradle > Install`

- Restart NetBeans

- Set the gradle path: `Preferences > Gradle`:
	* Set the install directory to where it was installed
		* Mac OS X via Homebrew: `/usr/local/opt/gradle/libexec`
		* Windows: `C:\Program Files\Gradle`
		* Gradle Wrapper: `PROJECT LOCATION\Backend\gradlew`
	* Set the Gradle User Home directory to whereever you'd like:
		* Mac OS X for example: `/Users/USERNAME/.gradle`

- Open the project `File > Open Project... > Select this project`

- Wait for the Gradle magic to happen

## Running the Backend API

There are two ways you can run the backend:
* In terminal navigate to the Backend folder and run: `gradle run` / `./gradlew run`
* In NetBeans select the class containing the main method and press run.

Once running open a browser and go to: [http://localhost:8080/api/](http://localhost:8080/api/)

## Contributing Guidelines

- `cd` to the root folder project location 

- Make a new branch (with a meaningful name):
```
git checkout -b BRANCH-NAME
```
- Make changes

- Write tests for your changes 

- Make a pull request (PR)

- A member of the team will look at your changes and ask any questions before a merge.

## Libraries Used

- [Spring Boot](http://projects.spring.io/spring-boot/)
- [Spring Data: Mongo](http://projects.spring.io/spring-data-mongodb/)
- [Spring Security](http://projects.spring.io/spring-security/)
- [Gradle](http://www.gradle.org/)