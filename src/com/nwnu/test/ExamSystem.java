package com.nwnu.test;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nwnu.dao.StudentDao;
import com.nwnu.dao.TeacherDao;
import com.nwnu.gui.LoginPanel;
import com.nwnu.pojo.Student;
import com.nwnu.pojo.Teacher;
import com.nwnu.util.BarChart;

/**
 * Filename: SystemTest.java
 * 
 * ·系统测试类
 * 
 * @author Luor
 * @version 1.0
 */
public class ExamSystem {
	
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private static int ScreenWidth = 450;
	private static int ScreenHeight = 600;
	
	private static JFrame win = new JFrame();
	private static JPanel panel = new JPanel();
	
	public static void main(String[] args) {
		panel.setSize(ScreenWidth, ScreenHeight);
		
		LoginPanel menuPanel = new LoginPanel(win, panel);
		menuPanel.setSize(ScreenWidth, ScreenHeight);
		win.setSize(ScreenWidth, ScreenHeight);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLocationRelativeTo(null);
		win.setTitle("西北师范大学疫情防控信息统计系统");
		win.setResizable(false);
		win.setVisible(true);
		
		win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		win.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出?", "确认", JOptionPane.OK_CANCEL_OPTION, 
                		JOptionPane.INFORMATION_MESSAGE);
                if(result == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
		
	}
	
	/**
	 * ·主函数
	 * @param args
	 * @throws Exception
	 */
	/*
	public static void main(String[] args) throws Exception {
		
		//执行
		Scanner sc = new Scanner(System.in);
		while(true) {
			//界面
			SystemMenu.mainMenu();
			//SQL生成
			//com.nwnu.util.SqlCreate sql = new com.nwnu.util.SqlCreate();
			//分支
			System.out.print("请输入要执行的选项：");
			int choice = sc.nextInt();
			switch(choice) {
				case 1: {
					SystemMenu.firstMenu();
					System.out.print("请输入要执行的选项：");
					boolean judge = true;
					while(judge) {
						int choice1 = sc.nextInt();
						switch(choice1) {
							case 1: {
								System.out.print("请输入要插入的学生信息（每一项用空格隔开）：\n学号（12位）   姓名   性别   学院   专业   电话号码    记录日期（yyyyMMdd）   "
										+ "省/自治州/直辖市（详细名称）   市/州/区（详细名称）   是否感染（1=是，0=否）   体温（3x.x）\n");
								Student stu = new Student(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), 
										sc.next(), sc.next(), sc.next(), sc.next(), sc.nextBigDecimal());
								if (stuDao.addStudent(stu)) {
									System.out.print("插入信息成功！\n");
								} else {
									System.out.print("插入信息失败！\n");
								}
								judge = false;
								break;
							}
							case 2: {
								System.out.print("请输入要插入的教师信息（每一项用空格隔开）：\n工号（4位）   姓名   性别   学院   电话号码    记录日期（yyyyMMdd）   "
										+ "省/自治州/直辖市（详细名称）   市/州/区（详细名称）   是否感染（是=1，否=0）   体温（3x.x）\n");
								Teacher tea = new Teacher(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), 
										sc.next(), sc.next(), sc.next(), sc.nextBigDecimal());
								if (teaDao.addTeacher(tea)) {
									System.out.print("插入信息成功！\n");
								} else {
									System.out.print("插入信息失败！\n");
								}
								judge = false;
								break;
							}
							default: {
								System.out.print("\n非法输入！\n请重新输入：");
								break;
							}
						}
					}
					break;
				}
				case 2: {
					SystemMenu.secondMenu();
					System.out.print("请输入要执行的选项：");
					boolean judge = true;
					while(judge) {
						int choice2 = sc.nextInt();
						switch(choice2) {
							case 1: {
								System.out.print("请输入要查询的成员类型与成员信息（每一项用空格隔开）：\n教师/学生（1=教师，2=学生）   学号/工号\n");
								int userType = sc.nextInt();
								if (userType == 1) {
									List<Teacher> teaList = new ArrayList<Teacher>();
									teaList = teaDao.quaryById(sc.next());
									System.out.print(" 工号        姓名     性别          学院             电话号码         记录日期      省/自治州/直辖市     市/州/区    是否感染    体温\n");
									for (Teacher t:teaList) {
										System.out.print(t);
									}
								} else if (userType == 2) {
									List<Student> stuList = new ArrayList<Student>();
									stuList = stuDao.quaryById(sc.next());
									System.out.print("    学号          姓名      性别           学院          专业        电话号码        记录日期       省/自治州/直辖市      市/州/区    是否感染    体温\n");
									for (Student s:stuList) {
										System.out.print(s);
									}
								} else {
									System.out.print("非法输入！\n");
								}
								
								judge = false;
								break;
							}
							case 2: {
								System.out.print("请输入要查询的成员类型与成员信息（每一项用空格隔开）：\n教师/学生（1=教师，2=学生）   学号/工号   记录日期（yyyyMMdd）\n");
								int userType = sc.nextInt();
								String id = sc.next();
								String dateString = sc.next();
								Date recordDate = new Date(sdf.parse(dateString).getTime());
								if (userType == 1) {
									Teacher tea = teaDao.findTeacherInfo(id, recordDate);
									System.out.print(" 工号      姓名      性别        学院          电话号码          记录日期        省/自治州/直辖市      市/州/区      是否感染      体温\n" + tea);
								} else if (userType == 2) {
									Student stu = stuDao.findStudentInfo(id, recordDate);
									System.out.print("  学号      姓名      性别          学院            专业          电话号码          记录日期         省/自治州/直辖市      市/州/区      是否感染      体温\n" + stu);
								} else {
									System.out.print("非法输入！\n");
								}
								
								judge = false;
								break;
							}
							default: {
								System.out.print("\n非法输入！\n请重新输入：");
								break;
							}
						}
					}
					
					break;
				}
				case 3: {
					SystemMenu.thirdMenu();
					System.out.print("请输入要执行的选项：");
					boolean judge = true;
					while(judge) {
						int choice3 = sc.nextInt();
						switch(choice3) {
							case 1: {
								System.out.print("请输入要查询的日期区间（日期格式：yyyyMMdd）：\n起始日期      截止日期\n");
								
								JFrame frame = new JFrame("疫情信息统计图");
								frame.add(new BarChart(sc.next(), sc.next(), choice3).getChartPanel());
								frame.setBounds(50, 50, 800, 400);
								frame.setVisible(true);
								
								judge = false;
								break;
							}
							case 2: {
								System.out.print("请输入要查询的日期区间（日期格式：yyyyMMdd）：\n起始日期   截止日期\n");
								
								JFrame frame = new JFrame("疫情信息统计图");
								frame.add(new BarChart(sc.next(), sc.next(), choice3).getChartPanel());
								frame.setBounds(50, 50, 800, 400);
								frame.setVisible(true);
								
								judge = false;
								break;
							}
							default: {
								System.out.print("\n非法输入！\n请重新输入：");
								break;
							}
						}
					}
					
					break;
				}
				case 4: {
					System.out.print("\n系统关闭，谢谢使用！\n");
					sc.close();
					return;
				}
				default: {
					System.out.print("\n非法输入！\n请重新输入：");
					break;
				}
			}
		}
		
	}*/
	
}
