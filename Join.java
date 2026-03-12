/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */ 

import java.sql.*;
     
public class Join {

	public static void main(String args[]) {
		  
		String url = "<jdbc-url>";

		Connection con;
		String query = "select SUPPLIERS.SUP_NAME, COFFEES.COF_NAME " +
					   "from COFFEES, SUPPLIERS " +
					   "where SUPPLIERS.SUP_NAME like 'Acme, Inc.' and " +
					   "SUPPLIERS.SUP_ID = COFFEES.SUP_ID";
		Statement stmt;
	
	
		try {
			con = DriverManager.getConnection(url,"<username>","<password>");
	
			stmt = con.createStatement();							
	
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Supplier, Coffee:");
			while (rs.next()) {
				String supName = rs.getString(1);
				String cofName = rs.getString(2);
				System.out.println("    " + supName + ", " + cofName);
			}

			stmt.close();
			con.close();

		} catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}	
	}
}

