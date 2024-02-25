package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LoginController;
import controller.RegisterController;
import core.View;

public class KnockCode extends View{
	LoginController loginController = null;
	RegisterController registerController = null;
	
	String knock_pw = "";
	
	private void view(String callback) {
		setBounds(100,100,240,310);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(12, 10, 199, 199);
		panel.setLayout(new GridLayout(3, 3, 1, 1));
		getContentPane().add(panel);
		
		JLabel[] lblNewLabel = new JLabel[9];
		for(int i = 0; i < 9; i++) {
			lblNewLabel[i] = new JLabel("●");
			lblNewLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel[i].setOpaque(true);
			panel.add(lblNewLabel[i]);
			
			int j = i;
			lblNewLabel[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseClicked(MouseEvent e) {
					knock_pw += Integer.toString(j+1);
				}
			});
		}
		
		JLabel lblNewLabel_1 = new JLabel("확인");
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(60, 220, 100, 25);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(50, 205, 50));
		lblNewLabel_1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
				if(callback == "login") {
					loginController.textField_1 = knock_pw;
					new Login(loginController);
				} else if(callback == "register") {
					registerController.u_pw = knock_pw;
					new Register(registerController);
				}
			}
		});
		getContentPane().add(lblNewLabel_1);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				if(callback == "login") {
					new Login(loginController);
				} else if(callback == "register") {
					new Register(registerController);
				}
			}
		});
	}
	
	public KnockCode(LoginController loginController) {
		super("노크코드");
		this.loginController = loginController;
		
		view("login");
	}
	
	public KnockCode(RegisterController registerContoller) {
		super("노크코드");
		this.registerController = registerContoller;
		
		view("register");
	}
}
