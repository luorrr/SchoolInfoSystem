package com.nwnu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.nwnu.pojo.Manager;
import com.nwnu.util.PieChart;

/**
 * Filename: ShowPieChartPanel.java
 * 
 * ·饼状图显示页面
 * 
 * @author Luor
 * @version 1.0
 */
public class ShowPieChartPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static Date date = null;
	
	/**
	 * ·此界面负责显示绘制的饼状图
	 * 
	 * @param showWin
	 * @param showPanel
	 * @param manager
	 */
	public ShowPieChartPanel(JFrame showWin, JPanel showPanel, Manager manager) {
		// clear panel
		showPanel.removeAll();
		showPanel.updateUI();
		showPanel.setLayout(new BorderLayout(20, 15));

		// getDate
		date = new java.sql.Date(new java.util.Date().getTime());
		
		// titlePanel/northPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		Font titleFont = new Font("宋体", Font.BOLD, 28);
		JLabel labelTitle = new JLabel("学校成员上报情况统计图");
		labelTitle.setFont(titleFont);
		titlePanel.add(labelTitle, SwingConstants.CENTER);
		showPanel.add(titlePanel, BorderLayout.NORTH);

		// centerPanel
		JPanel centerPanel = new JPanel();
		centerPanel.add(new PieChart(manager.getDept(), date).getChartPanel());
		showPanel.add(centerPanel, BorderLayout.CENTER);

		// southPanel
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 2, 10, 10));
		// border
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Font southBorderFont = new Font("宋体", Font.BOLD, 18);
		Font borderFont = new Font("宋体", Font.BOLD, 16);
		Border southTitleBorder = BorderFactory.createTitledBorder(border, "日期输入\0\0\0统计图生成", TitledBorder.LEFT,
				TitledBorder.TOP, southBorderFont);
		Border dateTitleBorder = BorderFactory.createTitledBorder(border, "日期yyyyMMdd：", TitledBorder.LEFT,
				TitledBorder.TOP, borderFont);
		Font btnFont = new Font("宋体", Font.BOLD, 16);
		Font inputFont = new Font("宋体", Font.BOLD, 16);
		// JTextField
		JTextField fieldDate = new JTextField();
		fieldDate.setFont(inputFont);
		fieldDate.setBorder(dateTitleBorder);
		southPanel.add(fieldDate);
		// JButton
		JButton btn = new JButton("开始统计");
		btn.setFont(btnFont);
		southPanel.setBorder(southTitleBorder);
		southPanel.add(btn);
		// Set and add southPanel
		showPanel.add(southPanel, BorderLayout.SOUTH);

		// westPanel
		JPanel westPanel = new JPanel();
		showPanel.add(westPanel, BorderLayout.WEST);

		// eastPanel
		JPanel eastPanel = new JPanel();
		showPanel.add(eastPanel, BorderLayout.EAST);

		// Add to JFrame
		showWin.add(showPanel);

		// Add Action Listener
		btn.addActionListener(event -> {
			try {
				date = new Date(new SimpleDateFormat("yyyyMMdd").parse(fieldDate.getText()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showConfirmDialog(null, "日期格式输入错误！","信息提示",JOptionPane.DEFAULT_OPTION);
			}
			centerPanel.removeAll();
			centerPanel.add(new PieChart(manager.getDept(), date).getChartPanel());
			centerPanel.updateUI();
		});
	}
}
