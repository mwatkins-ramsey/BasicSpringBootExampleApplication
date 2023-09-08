***

# Spring Boot Example

<br>

---

<details>
<summary><h2 style="display:inline;"> Intent</h2></summary>

This repo exists as a reference for using some basic tools for a Spring Boot Web App, including:
 - Controller/Service/Repository/Model
 - JPA Entities (Not Yet Implemented)
 - Interceptors/MDC (Not Yet Implemented)
 - Controller Advice (Not Yet Implemented)
 - Unit testing with mockito (Not Yet Implemented)
 - Flyway Migrations
 - Lombok
 - Docker

</details>

---

<br>

---

<details>
<summary><h2 style="display:inline;"> Infrastructure</h2></summary>

This application currently uses just a Spring Boot application and a MySQL DB.
You should be able to run by first running `docker compose up mysql` and then running the application in your IDE.

- MySql on port 33066 (mapped to 3306 of container)
- docker compose utilizes a volume with this DB, so it will persist DB changes between runs as long as the volume is
not deleted from your computer. The first time `docker-compose up mysql` is ran, it will spawn the messaging database
</details>

---

<br>

---

<details>
<summary><h2 style="display:inline;"> Domain</h2></summary>

In this sample app the intention is to support messages between users.  
A Message is just a string, and it always has one author identified by their user_id.  
A User has a first name, last name, and email address (all strings).  
A User can view any number of messages as long as they are listed as a viewer with the join table.  
so there should be a users table, a messages table, and a users_messages table.  

</details>

---

<br>

---

<details>
<summary><h2 style="display:inline;">Api Endpoints</h2></summary>

CRUD is available on:

<ul>
<li>localhost:8080/message</li>
<li>localhost:8080/user</li>
</ul>

</details>

---

<br>

---

<details>
<summary><h2 style="display:inline;">Flyway Migrations</h2></summary>

Flyway is a tool which is used to do DB migrations, it is somewhat flexible and can be used in many different ways.
In this project, there is a plugin defined in the pom.xml which specifies a flyway configuration. This plugin allows
you to use maven to execute the migrations which is helpful locally. This configuration would not typically be used
in a CICD pipeline which would execute flyway manually via a command.

Migration files can be found in src/main/resources/db/migration (your IDE might show it as db.migration)

</details>

---

<br>

---

<details>
<summary><h2 style="display:inline;">Lombok</h2></summary>

Lombok is a library for automating the generation of boilerplate code (like getters/setters, constructors, and more).
It is utilized by adding an annotation (such as @AllArgsConstructor and @Data) to the declaration of something
(classes, fields, methods, constructors, parameters, and even packages)

</details>

***