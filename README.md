# Student Grade Management System

This is a web implementation of a Student Grade Management System, completed as part of a coursework for my Software Engineering module. 

## Backend

It utilises a Java backend, containing all the basic logic in the [Springboot](https://spring.io/projects/spring-boot) framework, packaged as a Maven project. The `model` package contains all of the classes pertaining to the system itself, consisting of Student, Module, Registration and Grade.

- **Student:** represents a single student with the system and implenets all of the methods required to fit the following SQL schema:
```
CREATE TABLE student(
  id INT PRIMARY KEY,
  firstName VARCHAR(30),
  lastName VARCHAR(30),
  username VARCHAR(30),
  email VARCHAR(50)
);
```

- **Module:** represents a module on a course, with the field `mnc` describing whether the module is mandatory non-condonable. It follows the following SQL schema:
```
CREATE TABLE module(
  code VARCHAR(10) PRIMARY KEY,
  name VARCHAR(100),
  mnc BOOLEAN
);
```

- **Registration:** represents the relaitonship between a student and a module, follwoing this SQL schema:
```
CREATE TABLE registration(
  id SERIAL PRIMARY KEY,
  student_id INT,
  module_code VARCHAR(10),
  FOREIGN KEY (student_id)
    REFERENCES student (id),
  FOREIGN KEY (module_code)
    REFERENCES module (code)
);
```

- **Grade:** represents a grade and implements all of the methods required to fit the following SQL schema:
```sql
CREATE TABLE grade(
  id SERIAL PRIMARY KEY,
  score INT,
  student_id INT,
  module_code VARCHAR(10),
  FOREIGN KEY (student_id)
    REFERENCES student (id),
  FOREIGN KEY (module_code)
    REFERENCES module (code)
);
```

**Note:** this web application only uses H2 in-memory database, so data is not persistent (so don't be afraid if it all disappears when you close your browser)

### Running the Backend

Should you wish to run the backend for yourself, make sure your current working directory is `backend`

Next, run ```mvn compile```

Then, run ```mvn spring-boot:run```

- This will initialise the local server

## Frontend

The frontend is written in JavaScript using [Vite](https://vite.dev/) and interacts with the backend locally. 

### Running the Frontend

In the `frontend` folder, run ```npm ci``` to build the frontend.
- If this doesn't work, please run ```npm install```
.
Once you successfully build the frontend, you can run it with the ```npm run dev``` command.

Then, you can see your frontend working on your browser at http://localhost:5173


This project was originally developed on a university-hosted Gitlab.
