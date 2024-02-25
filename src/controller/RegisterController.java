package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.Controller;

public class RegisterController extends Controller{
	public String u_name = "";
	public String u_id = "";
	public String u_pw = "";
	public String u_birth = "";
	public int u_city = -1;
	public int u_town = -1;
	public String division = "";
	
	boolean dupChecked = false;
	
	public String[][] cities = new String[][] {};
	public String[][] towns = new String[][] {};
	
	public RegisterController() {
		super();
		
		getCities();
	}
	
	public void setData(String u_name, String u_id, String u_pw, String u_birth, int  u_city, int u_town, String division) {
		this.u_name = u_name;
		this.u_id = u_id;
		this.u_pw = u_pw;
		this.u_birth = u_birth;
		this.u_city = u_city;
		this.u_town = u_town;
		this.division = division;
	}
	
	public void getCities() {
		try {
			rs = stmt.executeQuery("select * from city");
			
			rs.last();
			cities = new String[rs.getRow()+1][2];
			
			cities[0][0] = "";
			cities[0][1] = "";
			
			rs.beforeFirst();
			int i = 1;
			while(rs.next()) {
				cities[i][0] = rs.getString("c_no");
				cities[i++][1] = rs.getString("c_name");
			}
		} catch(Exception e) {
			
		}
	}
	
	public void getTowns(String c_no) {
		try {
			rs = stmt.executeQuery("select * from town where c_no = '"+c_no+"'");
			
			rs.last();
			towns = new String[rs.getRow()+1][2];
			
			towns[0][0] = "";
			towns[0][1] = "";
			
			rs.beforeFirst();
			int i = 1;
			while(rs.next()) {
				towns[i][0] = rs.getString("t_no");
				towns[i++][1] = rs.getString("t_name");
			}
		} catch(Exception e) {
			
		}
	}
	
	public int dupCheck() {
		dupChecked = false;
		try {
			if(u_id.isEmpty()) return 3;
			rs = stmt.executeQuery("select * from user where u_id = '"+u_id+"'");
			if(rs.next()) {
				return 2;
			}
			dupChecked = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public int register() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		Boolean isDate = false;
		try {
			Date birth_dt = dateFormat.parse(u_birth);
			Date now = new Date();
			
			if(now.after(birth_dt)) {
				isDate = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(u_name.isEmpty() || u_id.isEmpty() || u_pw.isEmpty() || u_birth.isEmpty()) {
			return 5;
		} else if(!dupChecked) {
			return 4;
		} else if(u_pw.length() != 4) {
			return 3;
		} else if(!isDate) {
			return 2;
		} else {
			String town = "";
			if(u_town != -1) {
				town = towns[u_town][0];
			}
			try {
				if(u_town == -1) {
					stmt.execute("insert into user(u_name, u_id, u_pw, u_birth, division) "
							+ "values('"+u_name+"','"+u_id+"','"+u_pw+"','"+u_birth+"','"+division+"')");
				} else {
					stmt.execute("insert into user(u_name, u_id, u_pw, u_birth, division, t_no) "
							+ "values('"+u_name+"','"+u_id+"','"+u_pw+"','"+u_birth+"','"+division+"',"+town+")");
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			return 1;
		}
	}
	
	public void init() {  
		u_name = "";
		u_id = "";
		u_pw = "";
		u_birth = "";
		u_city = -1;
		u_town = -1;
		division = "";
	}
}
