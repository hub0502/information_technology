package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
	protected Connection conn;
	protected Statement stmt;
	protected ResultSet rs;
	
	public Controller() {
		DatabaseConn db = new DatabaseConn();
		conn = db.conn;
		stmt = db.stmt;
	}
	
	public void dispose(){
		try {
			stmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
