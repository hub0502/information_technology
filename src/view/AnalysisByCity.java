package view;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.AnalysisByCityController;
import controller.RegisterController;
import core.View;
import model.User;

public class AnalysisByCity extends View{
	public AnalysisByCity(AnalysisByCityController analysisByCityController) {
		super("지도");
		setBounds(100, 100, 700, 840);
		getContentPane().setLayout(null);
		
		RegisterController resCon = new RegisterController();
		
		JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("구매하기");
		
		try {
			rs = stmt.executeQuery("select * from city");
			rs.last();
			int rowTotal = rs.getRow();
			
			JLabel[] lblNewLabel = new JLabel[rowTotal];
			JLabel[] cityname = new JLabel[rowTotal];
			rs.beforeFirst();
			
			int i = 0;
			ImageIcon s;
			while(rs.next()) {
				s = new ImageIcon(path+"지역/"+rs.getString("c_no")+".png");
				
				int x = rs.getInt("c_x")+s.getIconWidth()/2-20;
				int y = rs.getInt("c_y")+s.getIconHeight()/2-10;
				boolean isMyCity = false;
				
				cityname[i] = new JLabel(rs.getString("c_name"));
				
				
				if(cityname[i].getText().equals("충북")) { 
					x -= 20;
					y -= 30;
				} else if(cityname[i].getText().equals("경기")) {
					x += 20;	
				}
				
				cityname[i].setBounds(x, y, 40, 25);
				cityname[i].setHorizontalAlignment(SwingConstants.CENTER);
				

				resCon.getTowns(rs.getString("c_no"));
				
				for(String[] town : resCon.towns) {
					if(town[0].isEmpty()) continue;
					if(town[0].equals(User.t_no)) {
						cityname[i].setForeground(Color.YELLOW);
						isMyCity = true;
					}
				}
				if(isMyCity) {
					cityname[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							if(e.isPopupTrigger()){
								popupMenu.removeAll();
								JMenuItem menuItem = new JMenuItem("판매하기");
						        menuItem.addActionListener(new ActionListener() {
						            public void actionPerformed(ActionEvent e) {
						                JOptionPane.showMessageDialog(null, "메뉴 항목 1을 클릭했습니다.");
						            }
						        });
						        popupMenu.add(menuItem);
						        
			                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
			                }
						}
					});
				} else {
					cityname[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if(e.isPopupTrigger()){
								popupMenu.removeAll();
								JMenuItem menuItem = new JMenuItem("구매하기");
						        menuItem.addActionListener(new ActionListener() {
						            public void actionPerformed(ActionEvent e) {
						                JOptionPane.showMessageDialog(null, "메뉴 항목 1을 클릭했습니다.");
						            }
						        });
						        popupMenu.add(menuItem);
						        
			                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
							}
						}
						
					});
				}
				
				
				getContentPane().add(cityname[i]);
				++i;
			}
			
			rs.beforeFirst();
			i = 0;
			while(rs.next()) {
				s = new ImageIcon(path+"지역/"+rs.getString("c_no")+".png");
				
				lblNewLabel[i] = new JLabel();
				lblNewLabel[i].setIcon(s);
				lblNewLabel[i].setBounds(rs.getInt("c_x"), rs.getInt("c_y"), s.getIconWidth(), s.getIconHeight());
				
				getContentPane().add(lblNewLabel[i]);
				
				++i;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
	      public void mousePressed(MouseEvent e) {
	          popupMenu.setVisible(false);
	      }
	
	      public void mouseReleased(MouseEvent e) {
	    	  popupMenu.setVisible(false);
	      }
		});
		
		
		
		
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new view.Main();
			}
		});
	}
}
