package com.nwnu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.nwnu.pojo.Manager;
import com.nwnu.util.BarChart;

/**
 * Filename: BarChartPanel.java
 * 
 * @author Luor
 * @version 1.0
 */
public class ShowChartPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ShowChartPanel(JFrame showWin, JPanel showPanel, Manager manager, int chooseType) {
		// clear panel
		showPanel.removeAll();
		showPanel.updateUI();
		showPanel.setLayout(new BorderLayout(20, 15));

		// chooseType
		String title = "��Ⱦ����";
		if (chooseType == 1) {
			title = "��Ⱦ�����ֲ�";
		}

		// titlePanel/northPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		Font titleFont = new Font("����", Font.BOLD, 28);
		JLabel labelTitle = new JLabel(title + "ͳ��ͼ");
		labelTitle.setFont(titleFont);
		titlePanel.add(labelTitle, SwingConstants.CENTER);
		showPanel.add(titlePanel, BorderLayout.NORTH);

		// centerPanel
		JPanel centerPanel = new JPanel();
		showPanel.add(centerPanel, BorderLayout.CENTER);

		// southPanel
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2, 2, 10, 10));
		// border
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Font southBorderFont = new Font("����", Font.BOLD, 18);
		Font borderFont = new Font("����", Font.BOLD, 16);
		Border southTitleBorder = BorderFactory.createTitledBorder(border, "�������\0\0\0ͳ��ͼ����", TitledBorder.LEFT,
				TitledBorder.TOP, southBorderFont);
		Border beginDateTitleBorder = BorderFactory.createTitledBorder(border, "��ʼ����yyyyMMdd��", TitledBorder.LEFT,
				TitledBorder.TOP, borderFont);
		Border endDateTitleBorder = BorderFactory.createTitledBorder(border, "��ֹ����yyyyMMdd��", TitledBorder.LEFT, 
				TitledBorder.TOP, borderFont);
		Font btnFont = new Font("����", Font.BOLD, 16);
		Font inputFont = new Font("����", Font.BOLD, 16);
		// JTextField
		JTextField fieldBeginDate = new JTextField();
		fieldBeginDate.setFont(inputFont);
		fieldBeginDate.setBorder(beginDateTitleBorder);
		southPanel.add(fieldBeginDate);
		JTextField fieldEndDate = new JTextField();
		fieldEndDate.setFont(inputFont);
		fieldEndDate.setBorder(endDateTitleBorder);
		southPanel.add(fieldEndDate);
		//JButton
		JButton btn1 = new JButton("����ͳ��");
		btn1.setFont(btnFont);
		JButton btn2 = new JButton("����ͳ��");
		btn2.setFont(btnFont);
		southPanel.setBorder(southTitleBorder);
		if (chooseType == 0) {
			southPanel.add(btn1);
		} else {
			southPanel.add(btn2);
		}
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
		btn1.addActionListener(event -> {
			centerPanel.add(new BarChart(fieldBeginDate.getText(), fieldEndDate.getText(), 1).getChartPanel());
			centerPanel.updateUI();
		});
		btn2.addActionListener(event -> {
			centerPanel.add(new BarChart(fieldBeginDate.getText(), fieldEndDate.getText(), 2).getChartPanel());
			centerPanel.updateUI();
		});
	}

}
