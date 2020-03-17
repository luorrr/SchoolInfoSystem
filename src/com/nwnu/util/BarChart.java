package com.nwnu.util;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.nwnu.dao.StudentDao;
import com.nwnu.dao.TeacherDao;
import com.nwnu.pojo.Student;
import com.nwnu.pojo.Teacher;

/**
 * Filename: BarChart.java
 * 
 * ·柱状图生成
 * 
 * @author Luor
 * @version 1.0
 */
public class BarChart {
	
	private static ChartPanel panel;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();
	
	public BarChart(String dateBeginString, String dateEndString, int choice) {
		//处理表名与日期数据
		Date dateBegin = null;
		Date dateEnd = null;
		try {
			dateBegin = new Date(sdf.parse(dateBeginString).getTime());
			dateEnd = new Date(sdf.parse(dateEndString).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("输入的日期数据/格式错误！\n");
		}
		String title = "";
		if (choice == 1) {
			title = dateBegin + "至" + dateEnd + "感染成员数量统计";
		} else if (choice == 2) {
			title = dateBegin + "至" + dateEnd + "成员地区分布统计";
		}
		
		//创建数据集
		CategoryDataset dataSet = getDateSet(dateBegin, dateEnd);
		//处理图表
		JFreeChart chart = ChartFactory.createBarChart(title, "数据类型", "数量", dataSet, 
				PlotOrientation.VERTICAL, true, false, false);
		//处理汉字
		//获取图表区域对象
		CategoryPlot plot = chart.getCategoryPlot();
		//水平底部列表
		CategoryAxis domainAxis = plot.getDomainAxis();
		//垂直标题
		domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));
		//水平底部标题
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD,16));
		//获取柱状
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 12));
		//设置标题字体
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
		
		panel = new ChartPanel(chart, true);
	}
	
	public static CategoryDataset getDateSet(Date dateBegin, Date dateEnd) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<Student> stuList = new ArrayList<Student>();
		List<Teacher> teaList = new ArrayList<Teacher>();
		
		try {
			stuList = stuDao.quaryByDate(dateBegin, dateEnd);
			teaList = teaDao.quaryByDate(dateBegin, dateEnd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("查询出错！\n");
		}
		
		for (Student s:stuList) {
			int count = 0;
			if (s.getDiagnosed().equalsIgnoreCase("0")) {
				count++;
			}
			dataSet.addValue(count, "学生", s.getRecordDate());
		}
		for (Teacher t:teaList) {
			int count = 0;
			if (t.getDiagnosed().equalsIgnoreCase("0")) {
				count++;
			}
			dataSet.addValue(count, "教师", t.getRecordDate());
		}
		
		return dataSet;
	}
	
	public ChartPanel getChartPanel() {
		return panel;
	}
}
