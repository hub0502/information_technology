package view;

import core.View;
import model.User;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.border.EmptyBorder;

import controller.AnalysisByCityController;
import controller.LoginController;
import controller.RegisterController;

public class Main extends View {
	public Main() {
		super("메인");
		setBounds(100, 100, 568, 381);
		getContentPane().setLayout(null);

		try {
			initConn();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("\uD83D\uDCC4");
		JLabel lblNewLabel = new JLabel("농수산물판매관리");
		lblNewLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		lblNewLabel.setForeground(new Color(50, 205, 50));
		lblNewLabel.setBounds(12, 10, 120, 30);
		getContentPane().add(lblNewLabel);

		String userString = "로그인을 먼저 해주세요.";
		String loginBtnString = "로그인";
		if (!User.u_id.isEmpty()) {
			userString = User.u_name + "님 환영합니다.";
			loginBtnString = "로그아웃";
		}
		JLabel lblNewLabel_1 = new JLabel(userString);
		lblNewLabel_1.setFont(new Font("HY헤드라인M", Font.PLAIN, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(400, 10, 140, 30);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(imageInit(path + "농산물/2.jpg", 528, 154));
		lblNewLabel_2.setBounds(12, 38, 528, 154);
		getContentPane().add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(1, 1, 1, 1));
		panel.setBackground(Color.BLACK);
		panel.setBounds(12, 202, 528, 130);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 1, 1));
		JLabel[] labelList = new JLabel[6];
		String[] labelTitle = new String[] { loginBtnString, "회원가입", "거래내역", "농산물관리", "농산물검색", "시도별분석" };

		boolean[] logined = new boolean[] { true, false, true, true, true, true };
		boolean[] notLogined = new boolean[] { true, true, false, false, false, false };

		for (int i = 0; i < labelList.length; i++) {
			labelList[i] = new JLabel(labelTitle[i]);
			labelList[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelList[i].setIcon(imageInit(path + "메인이미지/" + labelTitle[i] + ".jpg", 50, 50));
			labelList[i].setOpaque(true);
			
			boolean addEvent;
			
			if (User.u_id.isEmpty()) {
				labelList[i].setEnabled(notLogined[i]);
				addEvent = notLogined[i];
			} else {
				labelList[i].setEnabled(logined[i]);
				addEvent = logined[i];
			}

			int j = i;
			if(true) {
				labelList[i].addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();

						if (j == 0) {

							if (labelTitle[j] == "로그인") {
								LoginController loginController = new LoginController();
								new Login(loginController);
							} else {
								User.init();

								new view.Main();
							}

						} else if (j == 1) {
							RegisterController registerController = new RegisterController();
							new Register(registerController);
						} else if (j == 2) {

						} else if (j == 3) {

						} else if (j == 4) {

						} else if (j == 5) {
							AnalysisByCityController analysisByCityController = new AnalysisByCityController();
							new AnalysisByCity(analysisByCityController);
						}
					}
				});
			}
			
			panel.add(labelList[i]);
		}

		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					close();
					System.exit(0);
				} catch (Exception e2) {
				}
			}
		});
	}
}
