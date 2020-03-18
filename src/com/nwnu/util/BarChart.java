package com.nwnu.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
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
	
	/**
	 * ·柱状图参数设置
	 * 
	 * @param dateBeginString
	 * @param dateEndString
	 * @param choice
	 */
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
			title = dateBegin + "至" + dateEnd + "感染人员地区分布统计";
		}
		
		//创建数据集
		CategoryDataset dataSet = getDateSet(dateBegin, dateEnd, choice);
		//处理图表
		JFreeChart chart = ChartFactory.createBarChart3D(title, "数据类型", "数量", dataSet, 
				PlotOrientation.VERTICAL, true, false, false);
		//处理汉字
		//获取图表区域对象
		CategoryPlot plot = chart.getCategoryPlot();
		
		//设置宽度
		
		BarRenderer3D barRenderer = new BarRenderer3D();
		barRenderer.setBaseItemLabelsVisible(true);
		barRenderer.setBaseItemLabelPaint(Color.black);
		barRenderer.setBaseItemLabelFont(new Font("黑体",Font.BOLD,10));
		barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer.setMaximumBarWidth(0.5);
		barRenderer.setMinimumBarLength(0.1);
		//barRenderer.setItemMargin(-0.1);
		
		plot.setRenderer(barRenderer);
		
		//水平底部列表
		CategoryAxis domainAxis = plot.getDomainAxis();
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); 
		//垂直标题
		domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,6));
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
	
	/**
	 * ·数据集合获取与添加
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @param choice
	 * @return 数据集
	 */
	public static CategoryDataset getDateSet(Date dateBegin, Date dateEnd, int choice) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<Student> stuList = new ArrayList<Student>();
		List<Teacher> teaList = new ArrayList<Teacher>();
		
		try {
			if (choice == 1) {
				stuList = stuDao.quaryByDate(dateBegin, dateEnd, false);
				teaList = teaDao.quaryByDate(dateBegin, dateEnd, false);
			} else {
				stuList = stuDao.quaryByDate(dateBegin, dateEnd, true);
				teaList = teaDao.quaryByDate(dateBegin, dateEnd, true);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("查询出错！\n");
		}
		
		if (choice == 1) {
			for (Student s:stuList) {
				dataSet.addValue(s.getCount(), "学生", s.getRecordDate());
			}
			for (Teacher t:teaList) {
				dataSet.addValue(t.getCount(), "教师", t.getRecordDate());
			}
		} else {
			for (Student s:stuList) {
				dataSet.addValue(s.getCount(), s.getProvince(), s.getProvince());
			}
			for (Teacher t:teaList) {
				dataSet.addValue(t.getCount(), t.getProvince(), t.getProvince());
			}
		}
		return dataSet;
	}
	
	/**
	 * ·返回ChartPanel
	 * 
	 * @return panel
	 */
	public ChartPanel getChartPanel() {
		return panel;
	}
}
