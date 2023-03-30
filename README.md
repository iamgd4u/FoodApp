# FoodApp

To run this app on your machine:

=> CHANGE THE APPLICATIONS.PROPERTIES

spring.datasource.url = jdbc:mysql://localhost:3306/schemaname
spring.datasource.username = username
spring.datasource.password = password
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL55Dialect
spring.jpa.hibernate.ddl-auto=update

=>Run Application.java

=>(one time step)ADD A ADMIN/USER FOR SPRING SECURITY TO IDENTIFY U AS A LEGIT USER
Go to your SQLworkbench or any dbmanagement app
Go to foodapp table > user table
Add a user with role as ROLE_ADMIN or ROLE_USER(case sensitive)

=>Go to localhost://8080
To access the index page

=>Go to localhost://8080/admin
To access the admin page and add users and food items.


