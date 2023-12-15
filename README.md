# About this project
  ReadEazy is a E-commerce application for Book store management built using the spring boot as backend and thymeleaf for front end. This application has all the basic functionalities of a simple E-commerce application.
  I have Included the functionalities like user registration and user login. When user creates a new account he/she will recive a activation link to the registred email untill unless they verified their email Id
  they can't login into system. I have used the spring security for securing the web paths and web pages.

  After creation of new account user will be able to login and land on the home page of the application which contain some featured books. In the home page I have provided options for searching the books based on 
  based on the categeory or name of the book or by author name. And users can also filtered the books using the categeories. Users can views details of a particular book and provide the rating and review for that 
  particular book.

  I have also Added the profile section where in this section users can see their personal info and also they can update the profile picture if they want. And another thing I want to talk about the cart 
  functionality. Users can able to add the books to the cart and remove the books from the cart and they can also able to perform the payment process for a particular order and after successfull completion of 
  payment user order will be placed and the acknowledgement will be sent to the user registered E-mailId.

  I have Integrated the Razorpay for the Payment purpose and Spring Mail API for sending the E-mail for the end users.


# steps to set up project
  1. download the zip of this project and open with IDE's like Eclipse or Intellij or STS
  2. set up the data base schema with My-SQL work bench and copy the database tables code present in the database-schema.txt of this project folder and execute all commands according to the steps guided in that file.
  3. give the database url, username and password in application.properties file which is in src/main/resource path of this project
  4. Now go to the main Application class having the main method and run the application. And application will start defaulty at port 8080 in your local system.
