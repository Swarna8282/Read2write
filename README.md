# read2write
### This application is for reading data from a file and writing that data to H2 Database.

1. First run the test createFile() in class CreateTestFile. This will create the TextData.txt file with numbers from 1 to 50. For running the process faster, I used only 50 records. To test for 5000000 records, please change the RECORDS_COUNT variable in Constants class.
2. Now run the application with 'dev' profile.
3. In postman, enter this POST request 'http://localhost:8080/readWrite/file' to copy the data from the TestData.txt file to the H2 Database.
4. Now in a browser, enter this endpoint 'http://localhost:8080/h2-console'. If the JDBC URL is not pointing to 'jdbc:h2:mem:testDb', change it to that and then login using these credentials:
   1. User Name: sa
   2. Password: test
5. Now run this query in the H2 console: SELECT * FROM ID_TEXT;
6. You will see the entries as 1-One, 2-Two etc.