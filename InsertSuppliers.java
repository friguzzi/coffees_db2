/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */ 

import java.sql.*;
     
public class InsertSuppliers {

	public static void main(String args[]) {
		  
		String url = "jdbc:db2://<ip>:<port>/<db>";
		Connection con;
		Statement stmt;
		String query = "select SUP_NAME, SUP_ID from SUPPLIERS";
	
		
		try {
			con = DriverManager.getConnection(url,"<username>","<password>");
	
			stmt = con.createStatement();							
	
			stmt.executeUpdate("insert into SUPPLIERS " +
	                 "values(49, 'Superior Coffee', '1 Party Place', " +
				 "'Mendocino', 'CA', '95460')");
		
			stmt.executeUpdate("insert into SUPPLIERS " +
				"values(101, 'Acme, Inc.', '99 Market Street', " +
				"'Groundsville', 'CA', '95199')");
	
			stmt.executeUpdate("insert into SUPPLIERS " +
	                 "values(150, 'The High Ground', '100 Coffee Lane', " +
				 "'Meadows', 'CA', '93966')");
	
			ResultSet rs = stmt.executeQuery(query);
	
			System.out.println("Suppliers and their ID Numbers:");
			while (rs.next()) {
				String s = rs.getString("SUP_NAME");
				int n = rs.getInt("SUP_ID");
				System.out.println(s + "   " + n);
			}
	
			stmt.close();
			con.close();
	
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}

