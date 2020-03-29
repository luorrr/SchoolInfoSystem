package com.nwnu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Filename: SqlCreate.java
 * 
 * ������SQL���
 * 
 * @author Luor
 * @version 1.0
 */
public class SqlCreate {

	// �û�����
	private static int userCount = 2560;
	
	// ѧԺ-רҵ�б�
	List<String> college_major = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
			add("�������ѧ�빤��ѧԺ','�������ѧ�뼼��");
			add("�������ѧ�빤��ѧԺ','�������");
			add("�������ѧ�빤��ѧԺ','����������");
			add("��ѧ��ͳ��ѧԺ','��ѧ��Ӧ����ѧ");
			add("�����ѧԺ','Ӣ��");
			add("�����ѧԺ','��������");
			add("�����ѧԺ','����");
			add("���������ѧԺ','����ѧ");
			add("��ѧԺ','��������ѧ");
			add("����ѧԺ','����ѧ");
			add("�����뻷����ѧѧԺ','�����ѧ");
			add("��ѧ����ѧԺ','��ѧ");
			add("��ʷ�Ļ�ѧԺ','��ʷѧ");
			add("����ѧԺ','����ѧ");
			add("��ѧԺ','���ѧ");
			add("��ѧѧԺ','��ѧ");
			add("������ѧѧԺ','�����ѧ");
			add("��ѧԺ','��ѧ");
			add("��ýѧԺ','����ѧ");
			add("��ýѧԺ','����");
			add("����ѧԺ','���ι���");
			add("����ѧԺ','����ѧ");
			add("����ѧԺ','���ֱ���");
			add("�赸ѧԺ','�赸ѧ");
			add("����ѧԺ','����ѧ");
			add("����ѧԺ','�滭");
			add("�����Ļ�����ѧԺ','�����Ļ�����");
			add("����ѧԺ','��������");
			add("����ѧԺ','����ѧ");
		}
	};
	
	// ѧԺ�б�
	List<String> college = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("�������ѧ�빤��ѧԺ");
			add("��ѧ��ͳ��ѧԺ");
			add("�����ѧԺ");
			add("���������ѧԺ");
			add("��ѧԺ");
			add("����ѧԺ");
			add("�����뻷����ѧѧԺ");
			add("��ѧ����ѧԺ");
			add("��ʷ�Ļ�ѧԺ");
			add("����ѧԺ");
			add("��ѧԺ");
			add("��ѧѧԺ");
			add("������ѧѧԺ");
			add("��ѧԺ");
			add("��ýѧԺ");
			add("����ѧԺ");
			add("����ѧԺ");
			add("�赸ѧԺ");
			add("����ѧԺ");
			add("�����Ļ�����ѧԺ");
			add("����ѧԺ");
			add("����ѧԺ");
		}
	};
	
	// ʡ��-�����б�
	List<String> province_city = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("������','������");
			add("�Ϻ���','�Ϻ���");
			add("�����','�����");
			add("������','������");

			add("����������','������");
			add("���ɹ�������','���ͺ�����");
			add("����׳��������','������");
			add("�½�ά���������','��³ľ����");
			add("���Ļ���������','������");

			add("�ӱ�ʡ','ʯ��ׯ��");
			add("ɽ��ʡ','̫ԭ��");
			add("����ʡ','������");
			add("����ʡ','������");
			add("������ʡ','��������");
			add("����ʡ','�Ͼ���");
			add("�㽭ʡ','������");
			add("����ʡ','�Ϸ���");
			add("����ʡ','������");
			add("����ʡ','�ϲ���");
			add("ɽ��ʡ','������");
			add("����ʡ','֣����");
			add("����ʡ','�人��");
			add("����ʡ','��ɳ��");
			add("�㶫ʡ','������");
			add("����ʡ','������");
			add("�Ĵ�ʡ','�ɶ���");
			add("����ʡ','������");
			add("����ʡ','������");
			add("����ʡ','������");
			add("����ʡ','������");
			add("�ຣʡ','������");
			add("̨��ʡ','̨����");
		}
	};

	/**
	 * �����캯��
	 */
	public SqlCreate() {

		//ǰ������
		String id1 = "20170000";
		int id2 = 1000;
		int phoneNumber = 100000;

		File rfile = new File("resource/name.txt");
		File rfile2 = new File("resource/date.txt");
		File wfile = new File("resource/SQLTest.txt");
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(rfile));
			writer = new BufferedWriter(new FileWriter(wfile));
			String tempString = null;
			int line = 2001;
			
			//��������
			while ((tempString = reader.readLine()) != null && line <= userCount) {
				//��ʦ
				/*
				if (line <= 2000) {
					line++;
					continue;
				}*/
				reader2 = new BufferedReader(new FileReader(rfile2));
				String tempString2 = null;
				
				//�������
				Random rand = new Random();
				int sexNum = rand.nextInt(2);
				String sex = "Ů";
				if (sexNum == 1) {
					sex = "��";
				}
				//��ʦ
				//int collegeMajorNum = rand.nextInt(22);
				//ѧ��
				int collegeMajorNum = rand.nextInt(29);
				int provinceCityNum = rand.nextInt(32);
				//�̶���Ⱦ����
				//int diagNum = rand.nextInt(2);
				
				//���ڶ���
				while((tempString2 = reader2.readLine()) != null) {
					//ѧ��
					writer.write("('" + id1);
					writer.write((id2 + line) + "','");
					//��ʦ
					//writer.write("('" + (id2 + line) + "','");
					
					writer.write(tempString + "','");
					writer.write(sex + "','");
					//ѧ��
					writer.write(college_major.get(collegeMajorNum) + "','");
					//��ʦ
					//writer.write(college.get(collegeMajorNum) + "','");
					
					writer.write("17393" + (phoneNumber + line) + "','");
					writer.write(tempString2 + "','");
					//�����Ⱦ����
					int diagNum = rand.nextInt(2);
					writer.write(province_city.get(provinceCityNum) + "','");
					writer.write(diagNum + "','");
					BigDecimal tem = null;
					if (diagNum == 0) {
						tem = new BigDecimal((rand.nextInt(15) + 355)/10.0);
					} else {
						tem = new BigDecimal((rand.nextInt(25) + 370)/10.0);
					}
					writer.write(tem + "'");
					writer.write("), ");
				}
				line++;
			}
			reader.close();
			reader2.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

}
