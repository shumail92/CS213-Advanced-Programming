Advanced Programming <br>
Assignment # 1 – JDBC Driver <br>
Shumail Mohy-ud-Din <br>
BSCS-2B | 01947<br>
GitHub Link: https://github.com/shumail92/CS213-Advanced-Programming/tree/master/Assignment-1-JDBC <br>

##Documentation:<br>
Type 1 implementation of JDBC Driver. It uses the JDBC-ODBC bridge underlying the JDBC API. ODBC driver for any specific database vendor can be downloaded usually from vendor docs
JDBC (JAVA Database Connectivity) API is an application programming interface in Java to provide user to interact with Database (MySQL) for data manipulation and creation. 
In this implementation, as of now, following interfaces of JDBC API are implemented from java.sql package:<br>
• MyJDBCDriver.java <br>
	For registration of driver<br>
• MyJDBCConnection.java <br>
	For connection with DB<br>
• MyJDBCStatement.java <br>
	For executing statements<br>
• MyJDBCResultSet.java<br>
	For iterating the result sets<br>
<br>
##Testing (JUnit Test Cases)<br>
The implemented interfaces were tested for correctness by executing diff statements, agains their expected functionality. Test can be found under the com.ap.assignment1.test package; in it, JDBC API was used to create a connection, execute a query, extract results and closing the connection.
