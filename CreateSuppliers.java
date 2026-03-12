/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */ 


import java.sql.*;
     
public class CreateSuppliers {

	public static void main(String args[]) {
		String url = "<jdbc-url>";
	        
		Connection con;
		String createString;
		createString = "create table SUPPLIERS " + 
						"(SUP_ID int, " +
						"SUP_NAME varchar(40), " +
						"STREET varchar(40), " +
						"CITY varchar(20), " +
						"STATE char(2), ZIP char(5))";
	
		Statement stmt;


		try {
			con = DriverManager.getConnection(url,"<username>","<password>");
	
			stmt = con.createStatement();							
	   		stmt.executeUpdate(createString);
	
			stmt.close();
			con.close();
	
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}

