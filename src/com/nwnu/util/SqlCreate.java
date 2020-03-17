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
 * ·生成SQL语句
 * 
 * @author Luor
 * @version 1.0
 */
public class SqlCreate {

	//用户数量
	private static int userCount = 2050;
	
	List<String> college_major = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
			add("计算机科学与工程学院','计算机科学与技术");
			add("计算机科学与工程学院','软件工程");
			add("计算机科学与工程学院','物联网工程");
			add("数学与统计学院','数学与应用数学");
			add("外国语学院','英语");
			add("外国语学院','阿拉伯语");
			add("外国语学院','日语");
			add("物理与电子学院','物理学");
			add("文学院','汉语言文学");
			add("教育学院','教育学");
			add("地理与环境科学学院','地理科学");
			add("化学化工学院','化学");
			add("历史文化学院','历史学");
			add("经济学院','金融学");
			add("商学院','会计学");
			add("哲学学院','哲学");
			add("生命科学学院','生物科学");
			add("法学院','法学");
			add("传媒学院','新闻学");
			add("传媒学院','动画");
			add("旅游学院','旅游管理");
			add("音乐学院','音乐学");
			add("音乐学院','音乐表演");
			add("舞蹈学院','舞蹈学");
			add("美术学院','美术学");
			add("美术学院','绘画");
			add("国际文化交流学院','国际文化交流");
			add("体育学院','体育教育");
			add("心理学院','心理学");
		}
	};
	
	List<String> college = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("计算机科学与工程学院");
			add("数学与统计学院");
			add("外国语学院");
			add("物理与电子学院");
			add("文学院");
			add("教育学院");
			add("地理与环境科学学院");
			add("化学化工学院");
			add("历史文化学院");
			add("经济学院");
			add("商学院");
			add("哲学学院");
			add("生命科学学院");
			add("法学院");
			add("传媒学院");
			add("旅游学院");
			add("音乐学院");
			add("舞蹈学院");
			add("美术学院");
			add("国际文化交流学院");
			add("体育学院");
			add("心理学院");
		}
	};
	
	List<String> province_city = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("北京市','北京市");
			add("上海市','上海市");
			add("天津市','天津市");
			add("重庆市','重庆市");

			add("西藏自治区','拉萨市");
			add("内蒙古自治区','呼和浩特市");
			add("广西壮族自治区','南宁市");
			add("新疆维吾尔自治区','乌鲁木齐市");
			add("宁夏回族自治区','银川市");

			add("河北省','石家庄市");
			add("山西省','太原市");
			add("辽宁省','沈阳市");
			add("吉林省','长春市");
			add("黑龙江省','哈尔滨市");
			add("江苏省','南京市");
			add("浙江省','杭州市");
			add("安徽省','合肥市");
			add("福建省','福州市");
			add("江西省','南昌市");
			add("山东省','济南市");
			add("河南省','郑州市");
			add("湖北省','武汉市");
			add("湖南省','长沙市");
			add("广东省','广州市");
			add("海南省','海口市");
			add("四川省','成都市");
			add("贵州省','贵阳市");
			add("云南省','昆明市");
			add("陕西省','西安市");
			add("甘肃省','兰州市");
			add("青海省','西宁市");
			add("台湾省','台北市");
		}
	};

	/**
	 * ·构造函数
	 */
	public SqlCreate() {
		System.out.println(province_city);

		//前置数据
		String id1 = "20170000";
		int id2 = 1000;
		int phoneNumber = 100000;

		File rfile = new File("resource/260Wname.txt");
		File rfile2 = new File("resource/date.txt");
		File wfile = new File("resource/SQLTeacher.txt");
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(rfile));
			writer = new BufferedWriter(new FileWriter(wfile));
			String tempString = null;
			int line = 1;
			
			//姓名读入
			while ((tempString = reader.readLine()) != null && line <= userCount) {
				if (line <= 2000) {
					line++;
					continue;
				}
				reader2 = new BufferedReader(new FileReader(rfile2));
				String tempString2 = null;
				
				//随机数据
				Random rand = new Random();
				int sexNum = rand.nextInt(2);
				String sex = "女";
				if (sexNum == 1) {
					sex = "男";
				}
				//教师
				//int collegeMajorNum = rand.nextInt(22);
				//学生
				int collegeMajorNum = rand.nextInt(29);
				int provinceCityNum = rand.nextInt(32);
				int diagNum = rand.nextInt(2);
				
				//日期读入
				while((tempString2 = reader2.readLine()) != null) {
					//学生
					writer.write("('" + id1);
					writer.write((id2 + line) + "','");
					//教师
					//writer.write("('" + (id2 + line) + "','");
					
					writer.write(tempString + "','");
					writer.write(sex + "','");
					//学生
					writer.write(college_major.get(collegeMajorNum) + "','");
					//教师
					//writer.write(college.get(collegeMajorNum) + "','");
					
					writer.write("17393" + (phoneNumber + line) + "','");
					writer.write(tempString2 + "','");
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
