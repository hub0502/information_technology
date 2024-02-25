package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.RegisterController;
import core.View;

public class Register extends View {
	Register(RegisterController registerController) {
		super("회원가입");
		setBounds(100, 100, 394, 336);
		getContentPane().setLayout(null);
		
		JTextField textField_1 = new JTextField(registerController.u_name);
		textField_1.setColumns(10);
		textField_1.setBounds(75, 10, 180, 25);
		getContentPane().add(textField_1);
		
		JLabel lblDlfma_1 = new JLabel("이름");
		lblDlfma_1.setBounds(12, 10, 51, 25);
		getContentPane().add(lblDlfma_1);

		JTextField textField_2 = new JTextField(registerController.u_id);
		textField_2.setColumns(10);
		textField_2.setBounds(75, 45, 180, 25);
		getContentPane().add(textField_2);
		
		JLabel lblDlfma_2 = new JLabel("아이디");
		lblDlfma_2.setBounds(12, 45, 51, 25);
		getContentPane().add(lblDlfma_2);

		JPasswordField textField_3 = new JPasswordField(registerController.u_pw);
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(75, 80, 180, 25);
		getContentPane().add(textField_3);
		
		
		
		JLabel lblDlfma_3 = new JLabel("비밀번호");
		lblDlfma_3.setBounds(12, 80, 51, 25);
		getContentPane().add(lblDlfma_3);
		

		JTextField textField_4 = new JTextField(registerController.u_birth);
		textField_4.setColumns(10);
		textField_4.setBounds(75, 111, 180, 25);
		getContentPane().add(textField_4);
		
		JLabel lblDlfma_4 = new JLabel("생년월일");
		lblDlfma_4.setBounds(12, 111, 51, 25);
		getContentPane().add(lblDlfma_4);
		
		JLabel lblDlfma_5 = new JLabel("지역");
		lblDlfma_5.setBounds(12, 146, 51, 25);
		getContentPane().add(lblDlfma_5);
		
		JLabel lblDlfma_6 = new JLabel("세부지역");
		lblDlfma_6.setBounds(12, 181, 51, 25);
		getContentPane().add(lblDlfma_6);
		
		JLabel lblNewLabel_1 = new JLabel("중복확인");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(new Color(50, 205, 50));
		lblNewLabel_1.setBounds(266, 45, 100, 25);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_1.addMouseListener(new MouseListener() {
			
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
				registerController.u_id = textField_2.getText().trim();
				int dupStatus = registerController.dupCheck();
				if(dupStatus == 1) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.","정보",JOptionPane.INFORMATION_MESSAGE);
				} else if(dupStatus == 2) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.","경고",JOptionPane.ERROR_MESSAGE);
				} else if(dupStatus == 3) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.","경고",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblDlfma_7 = new JLabel("구분");
		lblDlfma_7.setBounds(12, 216, 51, 25);
		getContentPane().add(lblDlfma_7);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(75, 146, 100, 23);
		for(String[] values : registerController.cities) {
			comboBox.addItem(values[1]);
		}
		comboBox.setSelectedIndex(registerController.u_city);
		getContentPane().add(comboBox);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(75, 182, 100, 23);
		if(registerController.cities.length > 0) {
			for(String[] values : registerController.towns) {
				comboBox_1.addItem(values[1]);
			}
		} else {
			comboBox_1.setEnabled(false);
		}
		
		comboBox_1.setSelectedIndex(registerController.u_town);
		getContentPane().add(comboBox_1);
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboBox_1.removeAllItems();
				
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				if(cb.getSelectedIndex() == 0) {
					comboBox_1.setEnabled(false);
				}
				else {
					comboBox_1.setEnabled(true);
					
					registerController.getTowns(registerController.cities[cb.getSelectedIndex()][0]);
					
					for(String[] values : registerController.towns) {
						comboBox_1.addItem(values[1]);
					}
				}
			}
		});
		
		ButtonGroup g = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("야채");
		rdbtnNewRadioButton.setBounds(75, 217, 60, 23);
		g.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		getContentPane().add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("과일");
		rdbtnNewRadioButton_1.setBounds(139, 217, 60, 23);
		g.add(rdbtnNewRadioButton_1);
		getContentPane().add(rdbtnNewRadioButton_1);
		
		if(registerController.division == "2") {
			rdbtnNewRadioButton_1.setSelected(true);	
		}
		
		
		textField_3.addMouseListener(new MouseListener() {
			
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
				
				String division = "1";
				
				if(rdbtnNewRadioButton_1.isSelected()) division = "2";
				registerController.setData(textField_1.getText().trim(),
						textField_2.getText().trim(),
						textField_3.getText().trim(),
						textField_4.getText().trim(),
						comboBox.getSelectedIndex(),
						comboBox_1.getSelectedIndex(),
						division);
				new KnockCode(registerController);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("회원가입");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(new Color(50, 205, 50));
		lblNewLabel_2.setBounds(139, 256, 100, 25);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_2.addMouseListener(new MouseListener() {
			
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
				String division = "1";
				
				if(rdbtnNewRadioButton_1.isSelected()) division = "2";
				registerController.setData(textField_1.getText().trim(),
						textField_2.getText().trim(),
						textField_3.getText().trim(),
						textField_4.getText().trim(),
						comboBox.getSelectedIndex(),
						comboBox_1.getSelectedIndex(),
						division);
				
				int registerStatus = registerController.register();
				if(registerStatus == 1) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.","정보",JOptionPane.INFORMATION_MESSAGE);
					registerController.dispose();
					dispose();
					
					new view.Main();
				} else if(registerStatus == 2) {
					JOptionPane.showMessageDialog(null, "생년월일을 확인해주세요.","경고",JOptionPane.ERROR_MESSAGE);
				} else if(registerStatus == 3) {
					JOptionPane.showMessageDialog(null, "비밀번호를 4글자로 해주세요.","경고",JOptionPane.ERROR_MESSAGE);
				} else if(registerStatus == 4) {
					JOptionPane.showMessageDialog(null, "중복확인을 해주세요.","경고",JOptionPane.ERROR_MESSAGE);
				} else if(registerStatus == 5) {
					JOptionPane.showMessageDialog(null, "공백이 존재합니다.","경고",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				registerController.dispose();
				dispose();
				new Main();
			}
		});
	}
}
