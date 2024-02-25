package core;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class View extends JFrame{
	static protected Connection conn;
	static protected Statement stmt;
	static protected ResultSet rs;
	
	protected String path;
	protected String sql;
	
	public View(String title){
		path = System.getProperty("user.dir").replaceAll("\\\\", "/");
		int i = path.lastIndexOf("/");
		path = path.substring(0, i)+"/datafiles/";

// 기본폰트에 대한 설명이 없을 시 사용하지 않아도 된다. 그리고 굳이 이거 외우는거 보다 코드 몇줄 더 쓰는게 이익
//		FontUIResource f = new javax.swing.plaf.FontUIResource("HY헤드라인M",Font.PLAIN,12);
//		
//		java.util.Enumeration keys = UIManager.getDefaults().keys();
//	    while (keys.hasMoreElements()) {
//	      Object key = keys.nextElement();
//	      Object value = UIManager.get (key);
//	      if (value instanceof javax.swing.plaf.FontUIResource)
//	        UIManager.put (key, f);
//	    }
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(path+"로고.jpg").getImage());
    } 
	
	protected void initConn() throws SQLException{
		DatabaseConn db = new DatabaseConn();
		conn = db.conn;
		stmt = db.stmt;
	}
	
	protected void close() throws SQLException{
		stmt.close();
		conn.close();
	}
	
	protected ImageIcon imageInit(String path, int w, int h) {
		ImageIcon icon = new ImageIcon(path);	
		Image img = icon.getImage();
		icon = new ImageIcon(img.getScaledInstance(w, h, 0));
		
		return icon;
	}
}
