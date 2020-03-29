package com.nwnu.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.nwnu.dao.ManagerDao;
import com.nwnu.pojo.Manager;

/**
 * Filename: SystemPanel.java
 * 
 * ������Ա��¼����
 * 
 * @author Luor
 * @version 1.0
 */
public class LoginPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static ManagerDao manDao = new ManagerDao();
	
	private static int ScreenWidth = 450;
	private static int ScreenHeight = 600;

	/**
	 * ���ڴ˽�����й���Ա��������������Խ��е�¼
	 * 
	 * @param win
	 * @param mainPanel
	 */
	public LoginPanel(JFrame win, JPanel mainPanel) {
		// clear panel
		mainPanel.removeAll();
		mainPanel.updateUI();
		mainPanel.setLayout(null);
		
		//titlePanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBounds(ScreenWidth/2-240/2, ScreenHeight/4-160/2, 240, 60);
		Font titleFont = new Font("����", Font.BOLD, 38);
		JLabel labelTitle = new JLabel("������Ա��¼");
		labelTitle.setFont(titleFont);
		titlePanel.add(labelTitle);
		mainPanel.add(titlePanel);
		
		//loginPanel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(4, 1, 10, 10));
		loginPanel.setBounds(ScreenWidth/2-220/2, ScreenHeight/2-240/2, 220, 200);
		Font labelFont = new Font("����", Font.BOLD, 20);
		//id label
		JLabel labelId = new JLabel("���ţ�");
		labelId.setFont(labelFont);
		loginPanel.add(labelId);
		JTextField fieldId = new JTextField();
		fieldId.setFont(labelFont);
		loginPanel.add(fieldId);
		//password label
		JLabel labelPasswd = new JLabel("���룺");
		labelPasswd.setFont(labelFont);
		loginPanel.add(labelPasswd);
		JPasswordField fieldPasswd = new JPasswordField();
		fieldPasswd.setFont(labelFont);
		loginPanel.add(fieldPasswd);
		mainPanel.add(loginPanel);
		
		//buttonPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBounds(ScreenWidth/2-100/2, ScreenHeight/2+120, 100, 50);
		Font buttonFont = new Font("����", Font.BOLD, 20);
		JButton btn = new JButton("��¼");
		btn.setFont(buttonFont);
		buttonPanel.add(btn, BorderLayout.CENTER);
		mainPanel.add(buttonPanel);
		
		// Add to JFrame
		win.add(mainPanel);
		
		btn.addActionListener(event -> {
			Manager manager = null;
			try {
				manager = manDao.findManager(fieldId.getText(), String.valueOf(fieldPasswd.getPassword()));
			} catch (SQLException e) {
				manager = null;
				e.printStackTrace();
			}
			if (manager == null) {
				JOptionPane.showConfirmDialog(null, "��¼ʧ�ܣ����Ż��������","��Ϣ��ʾ",JOptionPane.DEFAULT_OPTION);
			} else {
				MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
				menuPanel.setSize(ScreenWidth, ScreenHeight);
			}
		});
	}
}
