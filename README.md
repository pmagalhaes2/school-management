
# School Management

This is a simple CRUD application for managing students and teachers. It allows you to create, read, update, and delete records of students and teachers in a database.

## Table of Contents

[](https://github.com/pmagalhaes2/school-management?tab=readme-ov-file#table-of-contents)

- [Technologies](https://github.com/pmagalhaes2/school-management?tab=readme-ov-file#-technologies)
- [Dependencies](https://github.com/pmagalhaes2/school-management?tab=readme-ov-file#%EF%B8%8F--dependencies)
- [Installation](https://github.com/pmagalhaes2/school-management?tab=readme-ov-file#--installation--and-usage)
- [API Endpoints](https://github.com/pmagalhaes2/school-management?tab=readme-ov-file#-api-endpoints)
- [Database](https://github.com/pmagalhaes2/school-management?tab=readme-ov-file#database)


## 🚀 Technologies

-   **Backend**: Java e Spring
-   **Database**: H2

## ⚠️  Dependencies

[](https://github.com/pmagalhaes2/marvel-search-heroes#%EF%B8%8F-dependencies)

Before you begin, you will need to have the following tools installed on your machine:
- [Git](https://git-scm.com) (optional for cloning the repository)
- [Java](https://www.java.com/pt-BR/) (required for running the application)
- An editor like [IntelliJ](https://www.jetbrains.com/idea/) (optional, but recommended for development)


## 📥  Installation  and usage

```bash  
  
# Clone this repository  
  
$  git  clone  https://github.com/pmagalhaes2/school-management.git
  
  
# Access the project folder in your terminal  
  
$  cd  school-management 
  
  
# Install dependencies with Maven  
  
  
# Run the application  
  
$  \.mvnm spring-boot:run  

  
# The API will be accessible at http://localhost:8080/

```  

##  📖 API Endpoints

### Authentication

To access the API endpoints for GET requests, no authentication is required. However, for POST, PUT, and DELETE requests, you need to authenticate using Basic Auth with the following credentials:

- Username: admin
- Password: admin

The API provides the following endpoints:


### Teachers

-   `GET /teachers`: Retrieves a list of all teachers.
-   `GET /teachers/{id}`: Retrieves a teacher by ID.
-   `POST /teachers`: Registers a new teacher.
-   `PUT /teachers/{id}`: Updates a teacher's information.
-   `DELETE /teachers/{id}`: Deletes a teacher.



Example request body for `POST /teachers`:

```json
{
  "name": "John Doe"
}
```


Example response for `GET /teachers/{id}`:


```json
{
  "createdAt": "15-04-2024 00:00:00",
  "id": "3443420c-8afe-439d-802d-5d80cbbc2014",
  "name": "John Doe"
}
```

### Students

-   `GET /students`: Retrieves a list of all students.
-   `GET /students/{id}`: Retrieves a student by ID.
-   `POST /students`: Registers a new student.
-   `PUT /students/{id}`: Updates a student's information.
-   `DELETE /students/{id}`: Deletes a student.

## Database


The project uses H2 as the database. You can access the H2 console by navigating to [http://localhost:8080/h2-console](http://localhost:8080/h2-console) in your web browser. Use the JDBC URL `jdbc:h2:mem:testdb` and the default username and password (`sa`) to connect.

Flyway is used for managing database migrations. Database migrations are applied automatically when the application starts.

  ---

Made by  [Patricia Magalhães](https://github.com/pmagalhaes2) 💙