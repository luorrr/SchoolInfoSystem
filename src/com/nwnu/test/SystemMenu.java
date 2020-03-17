package com.nwnu.test;

/**
 * Filename: SystemMenu.java
 * 
 * ·系统界面显示
 * 
 * @author Luor
 * @version 1.0
 */
public class SystemMenu {
	
	/**
	 * @param n
	 * @param c
	 * 
	 * ·自定义格式化输出
	 */
	public static void prints(int n,String c)
	{
		for(int i=0;i<n;i++)
		{
			System.out.printf("%2s",c);
		}
	}
	
	/**
	 * ·打印系统界面
	 */
	public static void mainMenu()
	{
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(11," ");
		System.out.print("1 -- 添加信息.");
		prints(10," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(11," ");
		System.out.print("2 -- 信息查询.");
		prints(10," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(11," ");
		System.out.print("3 -- 统计图生成.");
		prints(9," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(11," ");
		System.out.print("4 -- 退出系统.");
		prints(10," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
	/**
	 * ·打印第一层菜单
	 */
	public static void firstMenu() {
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(10," ");
		System.out.print("1 -- 添加学生信息.");
		prints(9," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(10," ");
		System.out.print("2 -- 添加教师信息.");
		prints(9," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
	/**
	 * ·打印第二层菜单
	 */
	public static void secondMenu() {
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(8," ");
		System.out.print("1 -- 学/工号查询. ");
		prints(11," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(8," ");
		System.out.print("2 -- 学/工号与日期联合查询. ");
		prints(6," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
	/**
	 * ·打印第二层菜单
	 */
	public static void thirdMenu() {
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(6," ");
		System.out.print("1 -- 查询指定日期区间内的感染人数.");
		prints(5," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(6," ");
		System.out.print("2 -- 查询指定日期区间内的人员地区分布.");
		prints(3," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
}
