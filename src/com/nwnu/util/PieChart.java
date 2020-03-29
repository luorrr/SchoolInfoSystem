package com.nwnu.util;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

import com.nwnu.dao.StudentDao;
import com.nwnu.dao.TeacherDao;
import com.nwnu.pojo.Student;
import com.nwnu.pojo.Teacher;

/**
 * Filename: PieChart.java
 * 
 * ·扇形图生成
 * 
 * @author Luor
 * @version 1.0
 */
public class PieChart {
	
	private static ChartPanel panel = null;
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();
	
	/**
	 * ·饼状图的处理
	 * 
	 * @param dept
	 * @param date
	 */
	public PieChart(String dept, Date date) {
		// 设置标题
		String title = date + dept + "成员疫情信息上报情况";
		// 获取数据集
		DefaultPieDataset dataset = getDataSet(dept, date);
		// 中文处理
		StandardChartTheme chartTheme = new StandardChartTheme("CN");
        // 标题
        chartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD, 20));
        // legend
        chartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 18));
        // 轴向字体
        chartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 16));
        ChartFactory.setChartTheme(chartTheme);
        // 创建统计图
        JFreeChart pieChart = ChartFactory.createPieChart3D(
        		title, 
        		dataset, 
        		true, 
        		false, 
        		false
        );
        // panel赋值
        panel = new ChartPanel(pieChart, true);
	}
	
	/**
	 * ·数据集的获取
	 * 
	 * @param dept
	 * @param date
	 * @return 数据集
	 */
	public static DefaultPieDataset getDataSet(String dept, Date date) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		List<Student> stuListOfDeptAndDate = new ArrayList<Student>();
		List<Student> stuListOfDept = new ArrayList<Student>();
		List<Teacher> teaListOfDeptAndDate = new ArrayList<Teacher>();
		List<Teacher> teaListOfDept = new ArrayList<Teacher>();
		// 数据库查询
		try {
			stuListOfDeptAndDate = stuDao.quaryByDeptAndDate(dept, date);
			stuListOfDept = stuDao.quaryByDept(dept);
			teaListOfDeptAndDate = teaDao.quaryByDeptAndDate(dept, date);
			teaListOfDept = teaDao.quaryByDept(dept);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 信息添加
		dataset.setValue("已填报学生："+stuListOfDeptAndDate.size(), stuListOfDeptAndDate.size());
		dataset.setValue("未填报学生："+(stuListOfDept.size()-stuListOfDeptAndDate.size()), 
				(stuListOfDept.size()-stuListOfDeptAndDate.size()));
		dataset.setValue("已填报教师："+teaListOfDeptAndDate.size(), teaListOfDeptAndDate.size());
		dataset.setValue("未填报教师："+(teaListOfDept.size()-teaListOfDeptAndDate.size()), 
				(teaListOfDept.size()-teaListOfDeptAndDate.size()));
		// 数据集返回
		return dataset;
	}
	
	/**
	 * ·返回ChartPanel对象
	 * 
	 * @return panel
	 */
	public ChartPanel getChartPanel() {
		return panel;
	}
}
