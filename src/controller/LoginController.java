package controller;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.Controller;
import core.DatabaseConn;
import model.User;
import view.Main;

public class LoginController extends Controller{
	public String textField = "";
	public String textField_1 = "";
	
	public LoginController() {
		super();
	}
	
	public void SetData(String textField, String textField_1) {
		this.textField = textField;
		this.textField_1 = textField_1;
	}
	
	public boolean ValidLogin() {
		if(textField.isEmpty() || textField_1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "공백이 존재합니다.","경고",JOptionPane.ERROR_MESSAGE);
		} else if(textField.equals("admin") && textField_1.equals("1234")) {
			User.u_id = "admin";
			User.u_pw = "1234";
			
			JOptionPane.showMessageDialog(null, "관리자로 로그인하였습니다.","정보",JOptionPane.INFORMATION_MESSAGE);
//			new view.admin.Main();
			return true;
		} else{
			try {
				rs = stmt.executeQuery("select * from user where u_id = '"+textField+"' and u_pw = '"+textField_1+"'");
				
				if(rs.next()) {
					User.u_id = rs.getString("u_id");
					User.u_pw = rs.getString("u_pw");
					User.u_name = rs.getString("u_name");
					User.u_birth = rs.getString("u_birth");
					User.division = rs.getString("division");
					User.t_no = rs.getString("t_no");
					
					JOptionPane.showMessageDialog(null, User.u_name+"님 로그인하였습니다.","정보",JOptionPane.INFORMATION_MESSAGE);
					new Main();
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.","경고",JOptionPane.ERROR_MESSAGE);
				}
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	public void init(){
		textField = "";
		textField_1 = "";
	}
}
