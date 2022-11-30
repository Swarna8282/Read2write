# read2write
### This application is for reading data from a file and writing that data to H2 Database.

1. Run the ReadWriteIt test class to check that the no.of lines in the TestData.txt file is same as the no.of records written to the H2 Database. For running the process faster, I used only 50 records. To test for 5000000 records, please change the RECORDS_COUNT variable in Constants class.
2. Run the application with 'dev' profile and check the data in the H2 console by using the following steps:
   1. In a browser, enter this endpoint 'http://localhost:8080/h2-console'. If the JDBC URL is not pointing to 'jdbc:h2:mem:testDb', change it to that and then login using these credentials:
      1. User Name: sa
      2. Password: test
   2. Now run this query in the H2 console: SELECT * FROM ID_TEXT;
   3. You will see the entries as 1-One, 2-Two etc.