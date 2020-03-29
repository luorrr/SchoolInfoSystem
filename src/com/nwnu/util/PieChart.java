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
 * ������ͼ����
 * 
 * @author Luor
 * @version 1.0
 */
public class PieChart {
	
	private static ChartPanel panel = null;
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();
	
	/**
	 * ����״ͼ�Ĵ���
	 * 
	 * @param dept
	 * @param date
	 */
	public PieChart(String dept, Date date) {
		// ���ñ���
		String title = date + dept + "��Ա������Ϣ�ϱ����";
		// ��ȡ���ݼ�
		DefaultPieDataset dataset = getDataSet(dept, date);
		// ���Ĵ���
		StandardChartTheme chartTheme = new StandardChartTheme("CN");
        // ����
        chartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
        // legend
        chartTheme.setRegularFont(new Font("����", Font.PLAIN, 18));
        // ��������
        chartTheme.setLargeFont(new Font("����", Font.PLAIN, 16));
        ChartFactory.setChartTheme(chartTheme);
        // ����ͳ��ͼ
        JFreeChart pieChart = ChartFactory.createPieChart3D(
        		title, 
        		dataset, 
        		true, 
        		false, 
        		false
        );
        // panel��ֵ
        panel = new ChartPanel(pieChart, true);
	}
	
	/**
	 * �����ݼ��Ļ�ȡ
	 * 
	 * @param dept
	 * @param date
	 * @return ���ݼ�
	 */
	public static DefaultPieDataset getDataSet(String dept, Date date) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		List<Student> stuListOfDeptAndDate = new ArrayList<Student>();
		List<Student> stuListOfDept = new ArrayList<Student>();
		List<Teacher> teaListOfDeptAndDate = new ArrayList<Teacher>();
		List<Teacher> teaListOfDept = new ArrayList<Teacher>();
		// ���ݿ��ѯ
		try {
			stuListOfDeptAndDate = stuDao.quaryByDeptAndDate(dept, date);
			stuListOfDept = stuDao.quaryByDept(dept);
			teaListOfDeptAndDate = teaDao.quaryByDeptAndDate(dept, date);
			teaListOfDept = teaDao.quaryByDept(dept);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��Ϣ���
		dataset.setValue("���ѧ����"+stuListOfDeptAndDate.size(), stuListOfDeptAndDate.size());
		dataset.setValue("δ�ѧ����"+(stuListOfDept.size()-stuListOfDeptAndDate.size()), 
				(stuListOfDept.size()-stuListOfDeptAndDate.size()));
		dataset.setValue("�����ʦ��"+teaListOfDeptAndDate.size(), teaListOfDeptAndDate.size());
		dataset.setValue("δ���ʦ��"+(teaListOfDept.size()-teaListOfDeptAndDate.size()), 
				(teaListOfDept.size()-teaListOfDeptAndDate.size()));
		// ���ݼ�����
		return dataset;
	}
	
	/**
	 * ������ChartPanel����
	 * 
	 * @return panel
	 */
	public ChartPanel getChartPanel() {
		return panel;
	}
}
