# RESTful-web-API
This is the backend for Collecting Service for Mobile App data. It is a part of the bachelor project IDATA2900 at NTNU. The backend consist of a REST API built in Spring Boot connected to a SQL database. The main purpose of the application is to provide a service for the mobile app and website to sende and reseve SQLite files data using API cals.

# Functionality
* Login
* Create User
* Upload SQLite file
* Download SQLite file
* Delete SQLite file
* Update Checkmark for SQLite file.
* User managment and authetication.

# Endpoints
1. ("/api/user")
   * Get("/company/{id}"):Get user based of company.
   * Post("/authenticate"): Authenticate a user.
   * Get("/sessionuser"): Get session user.
   * POST: Create a user.
   * Get("/getuser/{id}): Get a user based of id.
2. ("/api/sqlite-files")
   * Get("/company/{id}"): Get all sqlite files based of company.
   * Get("/all): Get all sqlite files.
   * PUT("/checked"): Update check mark of sqlite files.
   * POST("/upload"): Upload a sqlite file.
   * GET("/download/{id}"): Download a sqlite based of id.
   * DELETE("/delete/{id}") Delete a sqlite file based of a id.
3. ("/api/role")
   * Get("/all"): Get all roles.
4. ("/api/company")
   * Get("/companies"): Get all companies.

# Running the Project

Before running the project, ensure that you have set up a MySQL database and update the database configuration in both the `application.properties` and `env.properties` files.

**application.properties:**
```properties
spring.datasource.url=jdbc:mysql://<database_ip>:<database_port>/<your_database_name>
spring.datasource.username=<your_database_username>
spring.datasource.password=<your_database_password>
```

After configuring the database, navigate to the root directory of the project and run the following command in the terminal:
```shell
mvn spring-boot:run
```
This command will build and run the Spring Boot application. Make sure Maven is installed on your system before executing the command.

Once the application is running, you can access it in your web browser at the specified address (Default http://localhost:8080). If any issues arise during the setup, double-check your database configuration and ensure that all dependencies are installed.
