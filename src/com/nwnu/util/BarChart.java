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
 * ����״ͼ����
 * 
 * @author Luor
 * @version 1.0
 */
public class BarChart {

	private static ChartPanel panel = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();

	/**
	 * ����״ͼ��������
	 * 
	 * @param dateBeginString
	 * @param dateEndString
	 * @param choice
	 */
	public BarChart(String dateBeginString, String dateEndString, int choice) {
		// �����������������
		Date dateBegin = null;
		Date dateEnd = null;
		try {
			dateBegin = new Date(sdf.parse(dateBeginString).getTime());
			dateEnd = new Date(sdf.parse(dateEndString).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("�������������/��ʽ����\n");
		}
		String title = "";
		if (choice == 1) {
			title = dateBegin + "��" + dateEnd + "��Ⱦ��Ա����ͳ��";
		} else if (choice == 2) {
			title = dateBegin + "��" + dateEnd + "��Ⱦ��Ա�����ֲ�ͳ��";
		}

		// �������ݼ�
		CategoryDataset dataSet = getDataSet(dateBegin, dateEnd, choice);
		// ����ͼ��
		JFreeChart chart = ChartFactory.createBarChart3D(title, "��������", "����", dataSet, PlotOrientation.VERTICAL, true,
				false, false);
		// ������
		// ��ȡͼ���������
		CategoryPlot plot = chart.getCategoryPlot();

		// ���ÿ��

		BarRenderer3D barRenderer = new BarRenderer3D();
		barRenderer.setBaseItemLabelsVisible(true);
		barRenderer.setBaseItemLabelPaint(Color.black);
		barRenderer.setBaseItemLabelFont(new Font("����", Font.BOLD, 10));
		barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer.setMaximumBarWidth(0.5);
		barRenderer.setMinimumBarLength(0.1);
		// barRenderer.setItemMargin(-0.1);

		plot.setRenderer(barRenderer);

		// ˮƽ�ײ��б�
		CategoryAxis domainAxis = plot.getDomainAxis();
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// ��ֱ����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 6));
		// ˮƽ�ײ�����
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 16));
		// ��ȡ��״
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 12));
		// ���ñ�������
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));

		panel = new ChartPanel(chart, true);
	}

	/**
	 * �����ݼ��ϻ�ȡ�����
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @param choice
	 * @return ���ݼ�
	 */
	public static CategoryDataset getDataSet(Date dateBegin, Date dateEnd, int choice) {
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
			System.out.print("��ѯ����\n");
		}

		if (choice == 1) {
			for (Student s : stuList) {
				dataSet.addValue(s.getCount(), "ѧ��", s.getRecordDate());
			}
			for (Teacher t : teaList) {
				dataSet.addValue(t.getCount(), "��ʦ", t.getRecordDate());
			}
		} else {
			for (Student s : stuList) {
				dataSet.addValue(s.getCount(), s.getProvince(), s.getProvince());
			}
			for (Teacher t : teaList) {
				dataSet.addValue(t.getCount(), t.getProvince(), t.getProvince());
			}
		}
		return dataSet;
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
