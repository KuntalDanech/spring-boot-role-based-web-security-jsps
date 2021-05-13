# Spring-boot-role-based-web-security-jsps
Spring boot role based web security with jsps and mysql with custom login page
- Spring boot web security with user roles
- Each user has many roles

# What are the functionalty ?
- Spring boot Runner class has registered User details with roles.
- Password has been encrypted in database by using BCryptPasswordEncoder encoder.
- Custom login page has been added.
- After success authentication, based on roles URL has been redirected by using AuthenticationSuccessHandler (I have used lambda function)

# Which Version I have used
- Spring boot Version : 2.4.5
- Mysql Version : 5.7.33-0ubuntu0.18.04.1 (Ubuntu)
- Java SE 8
- Maven 3.3+
