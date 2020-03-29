package com.nwnu.test;

/**
 * Filename: SystemMenu.java
 * 
 * ��ϵͳ������ʾ�������У�
 * 
 * @author Luor
 * @version 1.0
 */
public class SystemMenu {
	
	/**
	 * ���Զ����ʽ���������
	 * 
	 * @param n
	 * @param c
	 */
	public static void prints(int n,String c)
	{
		for(int i=0;i<n;i++)
		{
			System.out.printf("%2s",c);
		}
	}
	
	/**
	 * ����ӡϵͳ������
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
		System.out.print("1 -- �����Ϣ.");
		prints(10," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(11," ");
		System.out.print("2 -- ��Ϣ��ѯ.");
		prints(10," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(11," ");
		System.out.print("3 -- ͳ��ͼ����.");
		prints(9," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(11," ");
		System.out.print("4 -- �˳�ϵͳ.");
		prints(10," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
	/**
	 * ����ӡ��һ��˵�
	 */
	public static void firstMenu() {
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(10," ");
		System.out.print("1 -- ���ѧ����Ϣ.");
		prints(9," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(10," ");
		System.out.print("2 -- ��ӽ�ʦ��Ϣ.");
		prints(9," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
	/**
	 * ����ӡ�ڶ���˵�
	 */
	public static void secondMenu() {
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(8," ");
		System.out.print("1 -- ѧ/���Ų�ѯ. ");
		prints(11," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(8," ");
		System.out.print("2 -- ѧ/�������������ϲ�ѯ. ");
		prints(6," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
	/**
	 * ����ӡ������˵�
	 */
	public static void thirdMenu() {
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(6," ");
		System.out.print("1 -- ��ѯָ�����������ڵĸ�Ⱦ����.");
		prints(5," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(1,"*");
		prints(6," ");
		System.out.print("2 -- ��ѯָ�����������ڵ���Ա�����ֲ�.");
		prints(3," ");
		prints(1,"*");
		System.out.print("\n");
		prints(15," ");
		prints(30,"*");
		System.out.print("\n");
		System.out.print("\n");
	}
	
}
