package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import core.View;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Login extends View{
	private JTextField textField;
	private JPasswordField textField_1;

	public Login(LoginController loginController) {
		super("로그인");
		setBounds(100,100,296,138);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(12, 10, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PW:");
		lblNewLabel_1.setBounds(12, 38, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField(loginController.textField);
		textField.setBounds(56, 7, 212, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField(loginController.textField_1);
		textField_1.setColumns(10);
		textField_1.setEchoChar('●');
		textField_1.setBounds(56, 35, 212, 21);
		textField_1.setEnabled(false);
		
		textField_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
				loginController.SetData(textField.getText().trim(), textField_1.getText().trim());
				new KnockCode(loginController);
			}
		});
		
		getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(34, 139, 34));
		btnNewButton.setBounds(97, 66, 97, 23);
		getContentPane().add(btnNewButton);
		
		btnNewButton.addMouseListener(new MouseListener() {
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
				if(loginController.ValidLogin()) {
					dispose();
					loginController.dispose();
				}
				else {
					textField.setText("");
					textField_1.setText("");
					loginController.init();
				}
			}
		});
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					dispose();
					loginController.dispose();
					new view.Main();
				} catch(Exception e2) {}
			}
		});
	}
}
