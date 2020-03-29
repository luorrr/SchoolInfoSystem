package com.nwnu.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
 * Filename: SelectInfoPanel.java
 * 
 * ����Ա��Ϣ��ѯ����
 * 
 * @author Luor
 * @version 1.0
 */
public class SelectInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private static boolean isStudent = true;
	private static JTextField fieldDate = null;

	private static int ScreenWidth = 450;
	private static int ScreenHeight = 600;
	private static int ShowScreenWidth = 1200;
	private static int ShowScreenHeight = 600;

	/**
	 * ���˽��渺���Ա��Ϣ��¼���Խ��в�ѯ
	 * 
	 * @param win
	 * @param mainPanel
	 * @param manager
	 * @param chooseType
	 */
	public SelectInfoPanel(JFrame win, JPanel mainPanel, Manager manager, int chooseType) {
		// clear panel
		mainPanel.removeAll();
		mainPanel.updateUI();
		mainPanel.setLayout(null);

		// chooseType
		String title = "ѧ��/����";
		if (chooseType == 1) {
			title += "+��������";
		}

		// titlePanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBounds(ScreenWidth / 2 - 360 / 2, ScreenHeight / 4 - 220 / 2, 360, 60);
		Font titleFont = new Font("����", Font.BOLD, 28);
//		JLabel labelTitle = new JLabel(manager.getDept() + "��Ա��Ϣ���");
		JLabel labelTitle = new JLabel(title + "��ѯ");
		labelTitle.setFont(titleFont);
		titlePanel.add(labelTitle);
		mainPanel.add(titlePanel);

		// inputPanel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4, 1, 10, 10));
//		inputPanel.setBounds(ScreenWidth / 2 - 300 / 2, ScreenHeight / 2 - 300 / 2, 300, 600);
		Font inputFont = new Font("����", Font.BOLD, 18);
		// border
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Font mainBorderFont = new Font("����", Font.BOLD, 22);
		Font borderFont = new Font("����", Font.BOLD, 18);
		Border mainTitleBorder = BorderFactory.createTitledBorder(border, "��Ա��Ϣ��ѯ", TitledBorder.LEFT, TitledBorder.TOP,
				mainBorderFont);
		Border typeTitleBorder = BorderFactory.createTitledBorder(border, "��Ա���ͣ�", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border idTitleBorder = BorderFactory.createTitledBorder(border, "ѧ/���ţ�", TitledBorder.LEFT, TitledBorder.TOP,
				borderFont);
		Border dateTitleBorder = BorderFactory.createTitledBorder(border, "����yyyyMMdd��", TitledBorder.LEFT,
				TitledBorder.TOP, borderFont);
		// sex input button
		JPanel typeButtonPanel = new JPanel();
		ButtonGroup typeButtonGroup = new ButtonGroup();
		JRadioButton stuBtn = new JRadioButton("ѧ��", true);
		JRadioButton teaBtn = new JRadioButton("��ʦ", false);
		stuBtn.setFont(inputFont);
		teaBtn.setFont(inputFont);
		typeButtonGroup.add(stuBtn);
		typeButtonGroup.add(teaBtn);
		typeButtonPanel.add(stuBtn);
		typeButtonPanel.add(teaBtn);
		typeButtonPanel.setBorder(typeTitleBorder);
		inputPanel.add(typeButtonPanel);
		// id input
		JTextField fieldId = new JTextField();
		fieldId.setFont(inputFont);
		fieldId.setBorder(idTitleBorder);
		inputPanel.add(fieldId);
		// date input
		if (chooseType == 1) {
			fieldDate = new JTextField();
			fieldDate.setFont(inputFont);
			fieldDate.setBorder(dateTitleBorder);
			inputPanel.add(fieldDate);
		}
		// Set inputPanel
		inputPanel.setBorder(mainTitleBorder);
		Color bgColor = new Color(230, 230, 250);
		inputPanel.setBackground(bgColor);
		// JScollePane
		JScrollPane scollPane = new JScrollPane(inputPanel);
		scollPane.setBounds(ScreenWidth / 2 - 320 / 2, ScreenHeight / 2 - 380 / 2, 320, 360);
		scollPane.setBackground(Color.WHITE);
		scollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(scollPane);

		// buttonPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		buttonPanel.setBounds(ScreenWidth / 2 - 240 / 2, ScreenHeight / 2 + 200, 260, 40);
		Font buttonFont = new Font("����", Font.BOLD, 20);
		JButton chooseBtn = new JButton("ȷ��");
		chooseBtn.setFont(buttonFont);
		JButton cancelBtn = new JButton("����");
		cancelBtn.setFont(buttonFont);
		buttonPanel.add(chooseBtn);
		buttonPanel.add(cancelBtn);
		mainPanel.add(buttonPanel);

		// Add to JFrame
		win.add(mainPanel);

		// ActionListener
		// type
		stuBtn.addActionListener(event -> {
			if (stuBtn.isSelected()) {
				isStudent = true;
			}
		});
		teaBtn.addActionListener(event -> {
			if (teaBtn.isSelected()) {
				isStudent = false;
			}
		});

		// chooseButton
		chooseBtn.addActionListener(event -> {
			List<Student> stuList = null;
			List<Teacher> teaList = null;
			if (chooseType == 0) {
				// Select by id
				if (isStudent) {
					// isStudent
					stuList = new ArrayList<Student>();
					try {
						stuList = stuDao.quaryById(fieldId.getText());
						if (stuList.isEmpty() || stuList == null) {
							int selectResult = JOptionPane.showConfirmDialog(null, "û�и�ѧ����Ϣ��", "��Ϣ��ʾ", 
									JOptionPane.DEFAULT_OPTION);
							if (selectResult == JOptionPane.OK_OPTION) {
								// Jump
								isStudent = true;
								MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
								menuPanel.setSize(ScreenWidth, ScreenHeight);
							}
						} else {
							// Create new JFrame
							CreateShowFrame(manager, 0, stuList, teaList);
							// Jump
							isStudent = true;
							MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
							menuPanel.setSize(ScreenWidth, ScreenHeight);
						}
					} catch (SQLException e1) {
						JOptionPane.showConfirmDialog(null, "���ݿ����Ӳ�ѯ����", "��Ϣ��ʾ", JOptionPane.DEFAULT_OPTION);
						e1.printStackTrace();
					}
				} else {
					// isTeacher
					teaList = new ArrayList<Teacher>();
					try {
						teaList = teaDao.quaryById(fieldId.getText());
						if (teaList.isEmpty() || teaList == null) {
							int selectResult = JOptionPane.showConfirmDialog(null, "û�иý�ʦ��Ϣ��", "��Ϣ��ʾ", 
									JOptionPane.DEFAULT_OPTION);
							if (selectResult == JOptionPane.OK_OPTION) {
								// Jump
								isStudent = true;
								MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
								menuPanel.setSize(ScreenWidth, ScreenHeight);
							}
						} else {
							// Create new JFrame
							CreateShowFrame(manager, 0, stuList, teaList);
							// Jump
							isStudent = true;
							MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
							menuPanel.setSize(ScreenWidth, ScreenHeight);
						}
					} catch (SQLException e1) {
						JOptionPane.showConfirmDialog(null, "���ݿ����Ӳ�ѯ����", "��Ϣ��ʾ", JOptionPane.DEFAULT_OPTION);
						e1.printStackTrace();
					}
				}
			} else {
				// Select by id and date
				// date transform
				java.sql.Date recordDate = null;
				try {
					recordDate = new java.sql.Date(sdf.parse(fieldDate.getText()).getTime());
				} catch (ParseException e) {
					JOptionPane.showConfirmDialog(null, "������Ϣ��ʽ����", "��Ϣ��ʾ", JOptionPane.DEFAULT_OPTION);
					e.printStackTrace();
				}
				if (isStudent) {
					// isStudent
					stuList = new ArrayList<Student>();
					try {
						Student stu = stuDao.findStudentInfo(fieldId.getText(), recordDate);
						stuList.add(stu);
						if (stu == null) {
							stuList = new ArrayList<Student>();
						}
						if (stuList.isEmpty() || stuList == null) {
							int selectResult = JOptionPane.showConfirmDialog(null, "û�и�ѧ����Ϣ��", "��Ϣ��ʾ", 
									JOptionPane.DEFAULT_OPTION);
							if (selectResult == JOptionPane.OK_OPTION) {
								// Jump
								isStudent = true;
								MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
								menuPanel.setSize(ScreenWidth, ScreenHeight);
							}
						} else {
							// Create new JFrame
							CreateShowFrame(manager, 1, stuList, teaList);
							// Jump
							isStudent = true;
							MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
							menuPanel.setSize(ScreenWidth, ScreenHeight);
						}
					} catch (SQLException e1) {
						JOptionPane.showConfirmDialog(null, "���ݿ����Ӳ�ѯ����", "��Ϣ��ʾ", JOptionPane.DEFAULT_OPTION);
						e1.printStackTrace();
					}
				} else {
					// isTeacher
					teaList = new ArrayList<Teacher>();
					try {
						Teacher tea = teaDao.findTeacherInfo(fieldId.getText(), recordDate);
						teaList.add(tea);
						if (tea == null) {
							teaList = new ArrayList<Teacher>();
						}
						if (teaList.isEmpty() || teaList == null) {
							int selectResult = JOptionPane.showConfirmDialog(null, "û�иý�ʦ��Ϣ��", "��Ϣ��ʾ", 
									JOptionPane.DEFAULT_OPTION);
							if (selectResult == JOptionPane.OK_OPTION) {
								// Jump
								isStudent = true;
								MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
								menuPanel.setSize(ScreenWidth, ScreenHeight);
							}
						} else {
							// Create new JFrame
							CreateShowFrame(manager, 1, stuList, teaList);
							// Jump
							isStudent = true;
							MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
							menuPanel.setSize(ScreenWidth, ScreenHeight);
						}
					} catch (SQLException e1) {
						JOptionPane.showConfirmDialog(null, "���ݿ����Ӳ�ѯ����", "��Ϣ��ʾ", JOptionPane.DEFAULT_OPTION);
						e1.printStackTrace();
					}
				}
			}
		});

		// cancelButton
		cancelBtn.addActionListener(event -> {
			// Choose
			int chooseResult = JOptionPane.showConfirmDialog(null, "ȷ�����أ�", "������һ���˵�", JOptionPane.DEFAULT_OPTION);
			win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			if (chooseResult == JOptionPane.OK_OPTION) {
				// Jump
				MenuPanel menuPanel = new MenuPanel(win, mainPanel, manager);
				menuPanel.setSize(ScreenWidth, ScreenHeight);
			}
		});
	}
	
	/**
	 * ���˷������𴴽���ʾ��ѯ����Ĵ���
	 * 
	 * @param manager
	 * @param chooseType
	 * @param stuList
	 * @param teaList
	 */
	public void CreateShowFrame(Manager manager, int chooseType, List<Student> stuList, List<Teacher> teaList) {
		JFrame showWin = new JFrame();
		JPanel showPanel = new JPanel();
		
		showPanel.setSize(ShowScreenWidth, ShowScreenHeight);
		
		SelectShowPanel selectShowPanel = new SelectShowPanel(showWin, showPanel, manager, chooseType, stuList, teaList);
		selectShowPanel.setSize(ShowScreenWidth, ShowScreenHeight);
		
		showWin.setSize(ShowScreenWidth, ShowScreenHeight);
		showWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showWin.setLocationRelativeTo(null);
		showWin.setTitle("����ʦ����ѧ���������Ϣͳ��ϵͳ");
		showWin.setResizable(false);
		showWin.setVisible(true);
	}
}
