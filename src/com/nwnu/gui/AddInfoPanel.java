package com.nwnu.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.nwnu.dao.StudentDao;
import com.nwnu.dao.TeacherDao;
import com.nwnu.pojo.Manager;
import com.nwnu.pojo.Student;
import com.nwnu.pojo.Teacher;

/**
 * Filename: AddInfoPanel.java
 * 
 * ·添加成员信息
 * 
 * @author Luor
 * @version 1.0
 */
public class AddInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static String sexString = "男";
	private static String diagnosedString = "0";
	private static JTextField fieldMajor = null;

	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();

	private static int ScreenWidth = 450;
	private static int ScreenHeight = 600;

	public AddInfoPanel(JFrame win, JPanel mainPanel, Manager manager, int chooseType) {
		// clear panel
		mainPanel.removeAll();
		mainPanel.updateUI();
		mainPanel.setLayout(null);

		// uesrType
		String user = "学生";
		String idName = "学号";
		if (chooseType == 1) {
			user = "教师";
			idName = "工号";
		}

		// titlePanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBounds(ScreenWidth / 2 - 360 / 2, ScreenHeight / 4 - 220 / 2, 360, 60);
		Font titleFont = new Font("宋体", Font.BOLD, 28);
		JLabel labelTitle = new JLabel(manager.getDept() + "成员信息添加");
		labelTitle.setFont(titleFont);
		titlePanel.add(labelTitle);
		mainPanel.add(titlePanel);

		// inputPanel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(10, 1, 10, 10));
//		inputPanel.setBounds(ScreenWidth / 2 - 300 / 2, ScreenHeight / 2 - 300 / 2, 300, 600);
		Font inputFont = new Font("宋体", Font.BOLD, 18);
		// border
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Font mainBorderFont = new Font("宋体", Font.BOLD, 22);
		Font borderFont = new Font("宋体", Font.BOLD, 18);
		Border mainTitleBorder = BorderFactory.createTitledBorder(border, user + "信息录入", TitledBorder.LEFT,
				TitledBorder.TOP, mainBorderFont);
		Border idTitleBorder = BorderFactory.createTitledBorder(border, idName + "：", TitledBorder.LEFT,
				TitledBorder.TOP, borderFont);
		Border nameTitleBorder = BorderFactory.createTitledBorder(border, "姓名：", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border sexTitleBorder = BorderFactory.createTitledBorder(border, "性别：", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border collegeTitleBorder = BorderFactory.createTitledBorder(border, "学院：", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border majorTitleBorder = null;
		if (chooseType == 0) {
			majorTitleBorder = BorderFactory.createTitledBorder(border, "专业：", TitledBorder.LEFT, TitledBorder.TOP,
					borderFont);
		}
		Border telTitleBorder = BorderFactory.createTitledBorder(border, "联系电话：", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border provinceTitleBorder = BorderFactory.createTitledBorder(border, "所在省：", TitledBorder.LEFT,
				TitledBorder.TOP, borderFont);
		Border cityTitleBorder = BorderFactory.createTitledBorder(border, "所在市：", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border diagTitleBorder = BorderFactory.createTitledBorder(border, "是否确诊：", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border temTitleBorder = BorderFactory.createTitledBorder(border, "体温：", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		// id input
		JTextField fieldId = new JTextField();
		fieldId.setFont(inputFont);
		fieldId.setBorder(idTitleBorder);
		inputPanel.add(fieldId);
		// name input
		JTextField fieldName = new JTextField();
		fieldName.setFont(inputFont);
		fieldName.setBorder(nameTitleBorder);
		inputPanel.add(fieldName);
		// sex input button
		JPanel sexButtonPanel = new JPanel();
//		sexButtonPanel.setLayout(new FlowLayout());
		ButtonGroup sexButtonGroup = new ButtonGroup();
		JRadioButton maleBtn = new JRadioButton("男", true);
		JRadioButton femaleBtn = new JRadioButton("女", false);
		maleBtn.setFont(inputFont);
		femaleBtn.setFont(inputFont);
		sexButtonGroup.add(maleBtn);
		sexButtonGroup.add(femaleBtn);
		sexButtonPanel.add(maleBtn);
		sexButtonPanel.add(femaleBtn);
		sexButtonPanel.setBorder(sexTitleBorder);
		inputPanel.add(sexButtonPanel);
		// college input
		JTextField fieldCollege = new JTextField();
		fieldCollege.setFont(inputFont);
		fieldCollege.setBorder(collegeTitleBorder);
		inputPanel.add(fieldCollege);
		// major input
		if (chooseType == 0) {
			fieldMajor = new JTextField();
			fieldMajor.setFont(inputFont);
			fieldMajor.setBorder(majorTitleBorder);
			inputPanel.add(fieldMajor);
		}
		// phoneNumber input
		JTextField fieldTel = new JTextField();
		fieldTel.setFont(inputFont);
		fieldTel.setBorder(telTitleBorder);
		inputPanel.add(fieldTel);
		// province input
		JTextField fieldProvince = new JTextField();
		fieldProvince.setFont(inputFont);
		fieldProvince.setBorder(provinceTitleBorder);
		inputPanel.add(fieldProvince);
		// city input
		JTextField fieldCity = new JTextField();
		fieldCity.setFont(inputFont);
		fieldCity.setBorder(cityTitleBorder);
		inputPanel.add(fieldCity);
		// dignosed input
		JPanel diagButtonPanel = new JPanel();
//		sexButtonPanel.setLayout(new FlowLayout());
		ButtonGroup diagButtonGroup = new ButtonGroup();
		JRadioButton diagTrueBtn = new JRadioButton("是", false);
		JRadioButton diagFalseBtn = new JRadioButton("否", true);
		diagTrueBtn.setFont(inputFont);
		diagFalseBtn.setFont(inputFont);
		diagButtonGroup.add(diagTrueBtn);
		diagButtonGroup.add(diagFalseBtn);
		diagButtonPanel.add(diagTrueBtn);
		diagButtonPanel.add(diagFalseBtn);
		diagButtonPanel.setBorder(diagTitleBorder);
		inputPanel.add(diagButtonPanel);
		// temperature input
		JTextField fieldTem = new JTextField();
		fieldTem.setFont(inputFont);
		fieldTem.setBorder(temTitleBorder);
		inputPanel.add(fieldTem);
		// Set inputPanel
		inputPanel.setBorder(mainTitleBorder);
		Color bgColor = new Color(230,230,250);
		inputPanel.setBackground(bgColor);
		// JScollePane
		JScrollPane scrollPane = new JScrollPane(inputPanel);
		scrollPane.setBounds(ScreenWidth / 2 - 320 / 2, ScreenHeight / 2 - 380 / 2, 320, 360);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(scrollPane);

		// buttonPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		buttonPanel.setBounds(ScreenWidth / 2 - 240 / 2, ScreenHeight / 2 + 200, 260, 40);
		Font buttonFont = new Font("宋体", Font.BOLD, 20);
		JButton chooseBtn = new JButton("确定");
		chooseBtn.setFont(buttonFont);
		JButton cancelBtn = new JButton("返回");
		cancelBtn.setFont(buttonFont);
		buttonPanel.add(chooseBtn);
		buttonPanel.add(cancelBtn);
		mainPanel.add(buttonPanel);

		// Add to JFrame
		win.add(mainPanel);

		// ActionListener
		// sex
		maleBtn.addActionListener(event -> {
			if (maleBtn.isSelected()) {
				sexString = "男";
			}
		});
		femaleBtn.addActionListener(event -> {
			if (femaleBtn.isSelected()) {
				sexString = "女";
			}
		});

		// diagnosed
		diagTrueBtn.addActionListener(event -> {
			if (diagTrueBtn.isSelected()) {
				diagnosedString = "1";
			}
		});
		diagFalseBtn.addActionListener(event -> {
			if (diagFalseBtn.isSelected()) {
				diagnosedString = "0";
			}
		});

		// chooseButton
		chooseBtn.addActionListener(event -> {
			Student stu = null;
			Teacher tea = null;
			BigDecimal temBD = new BigDecimal(fieldTem.getText());
			temBD.setScale(2, BigDecimal.ROUND_HALF_UP);
			if (chooseType == 0) {
				stu = new Student(fieldId.getText(), fieldName.getText(), sexString, fieldCollege.getText(), fieldMajor.getText(), 
						fieldTel.getText(), new java.sql.Date(new java.util.Date().getTime()), fieldProvince.getText(), 
						fieldCity.getText(), diagnosedString, temBD);
				// Add student
				try {
					if (stuDao.addStudent(stu)) {
						int addResult = JOptionPane.showConfirmDialog(null, "学生信息添加成功！","信息提示",JOptionPane.DEFAULT_OPTION);
						if (addResult == JOptionPane.OK_OPTION) {
							// Jump
							MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
							menuPanel.setSize(ScreenWidth, ScreenHeight);
						}
					} else {
						JOptionPane.showConfirmDialog(null, "信息添加失败！","信息提示",JOptionPane.DEFAULT_OPTION);
					}
				} catch (SQLException e) {
					JOptionPane.showConfirmDialog(null, "数据库连接查询错误！","信息提示",JOptionPane.DEFAULT_OPTION);
					e.printStackTrace();
				}
			} else {
				tea = new Teacher(fieldId.getText(), fieldName.getText(), sexString, fieldCollege.getText(), 
						fieldTel.getText(), new java.sql.Date(new java.util.Date().getTime()), fieldProvince.getText(), 
						fieldCity.getText(), diagnosedString, temBD);
				// Add teacher
				try {
					if (teaDao.addTeacher(tea)) {
						int addResult = JOptionPane.showConfirmDialog(null, "教师信息添加成功！","信息提示",JOptionPane.DEFAULT_OPTION);
						if (addResult == JOptionPane.OK_OPTION) {
							// Jump
							MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
							menuPanel.setSize(ScreenWidth, ScreenHeight);
						}
						
					} else {
						JOptionPane.showConfirmDialog(null, "信息添加失败！","信息提示",JOptionPane.DEFAULT_OPTION);
					}
				} catch (SQLException e) {
					JOptionPane.showConfirmDialog(null, "数据库连接查询错误！","信息提示",JOptionPane.DEFAULT_OPTION);
					e.printStackTrace();
				}
			}
		});
		
		// cancelButton
		cancelBtn.addActionListener(event -> {
			// Choose
			int chooseResult = JOptionPane.showConfirmDialog(null, "确定返回？","返回上一级菜单",JOptionPane.DEFAULT_OPTION);
			win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			if (chooseResult == JOptionPane.OK_OPTION) {
				// Jump
				MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
				menuPanel.setSize(ScreenWidth, ScreenHeight);
			}
		});
		
	}
}
