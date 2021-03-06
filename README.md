# BusPassApp
A primitive console based BusPassApp based on core Java and utilises MySQL database.
Practice project aimed to focus on modularity, OOPs concepts, design patterns, DBMS and other CS fundamentals

Below are the minimum software requirements for running the application :

> JRE - 1.8 and above
> JDK - 8.0 and above
> MySql Server 8.0 and above
> MySql Workbench 8.0 and above
> Recommended IDE - intellij IDEA 2019.3.4
> Performance Tested on IDE - intellij IDEA 2019.3.4
> Optional Requirement - XAMPP to run MySql Server


Database and Server Set-up steps:
1. Make sure that MySql Server 8.0 is installed on your systems, with the JDBC connector and CLASSPATH configured appropriately.
2. Start your MySql Server locally using XAMPP or, Command Prompt.
3. Connect to your server in MySql Workbench 8.0 by logging in with your credentials.
4. Create a database with the name of your choice. The application was tested using database name as - buspass_app. To create a database, write the query-
Create database <enter database name here>;
5. Start using this database on your running server with the query- 
use database <enter database name here>;
6. Once the database is being used by your running server, open the file - “SQL Commands for Set-Up” which is present in current folder. The file provides commands to create all tables and dump data required to test all features of the application.
7. Press CTRL+A to copy all contents of the file.
8. Paste the commands in a new sql tab in your currently open MySql Workbench 8.0. Select all the queries, and hit the run button to load the data. 
9. Click on show tables, to check if the below tables and views have been created :
-- Tables : bus, buspass, feedback, route, routerequest, stop, user, idgenerator 
-- Views : availableroutebusmap, routestopname
10. Check the contents for each of the tables, by suing select query. Each of the tables and views will have demo data.
11. Now you may proceed to set-up connection in the application.

Set-up Connection in the application :
1. Open the project folder with the name “BusPassApp” or, you may use the included jar file in this folder.
2. Navigate to the file - “/BusPassApp/src/assets/ConnectionManager.java”
You may use Recommended IDE - intellij IDEA 2019.3.4 - Or, any other IDE with JRE 1.8 nad JDK 8.0 support to open and edit this file. The application was created and tested on this IDE.
Make sure that the IDE you are using is configured to use JDBC database connections for MySql 8.0.
3. In the file - “ConnectionManager.java” edit the url to the one being used on your 
Local server, as created and set-up with dump data from the previous “Database and Server Set-up steps”.
The defalult link will be set as "jdbc:mysql://localhost:3306/buspass_app?serverTimezone=UTC”
4. Enter the credentials for the server connection on which the server is running.
The default value will be set as - “root’, “”.
5. Please run the “/BusPassApp/src/Driver.java” class from your IDE to initiate the application.
6. Your application is now ready to use and test. 


Please find the file - “ BusPassApp Manual” - in current folder to learn more on how to use app.
