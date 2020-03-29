package com.nwnu.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.nwnu.pojo.Manager;
import com.nwnu.pojo.Student;
import com.nwnu.pojo.Teacher;

/**
 * Filename: SelectShowPanel.java
 * 
 * ����ѯ�����ʾ����
 * 
 * @author Luor
 * @version 1.0
 */
public class SelectShowPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * ���˽�����ʾ����Ա��ѯ��Ϣ�õ��Ľ��
	 * 
	 * @param showWin
	 * @param showPanel
	 * @param manager
	 * @param chooseType
	 * @param stuList
	 * @param teaList
	 */
	public SelectShowPanel(JFrame showWin, JPanel showPanel, Manager manager, int chooseType, List<Student> stuList, 
			List<Teacher> teaList) {
		// clear panel
		showPanel.removeAll();
		showPanel.updateUI();
		showPanel.setLayout(new BorderLayout(20, 15));

		// chooseType
		String title = "ѧ��/����";
		if (chooseType == 1) {
			title += "+��������";
		}

		// titlePanel/northPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		Font titleFont = new Font("����", Font.BOLD, 28);
		JLabel labelTitle = new JLabel(title + "��ѯ");
		labelTitle.setFont(titleFont);
		titlePanel.add(labelTitle, SwingConstants.CENTER);
		showPanel.add(titlePanel, BorderLayout.NORTH);
		
		// tablePanel/centerPanel
		Font tableFont = new Font("����", Font.BOLD, 16);
		// data
		String[][] data = null;
		String[] titles = null;
		if (stuList != null) {
			data = new String[stuList.size()][];
			titles = new String[] {"ѧ��","����","�Ա�","ѧԺ","רҵ","��ϵ�绰","��¼����","����ʡ","������","�Ƿ��Ⱦ","����"};
			// Add data
			int count = 0;
			for (Student stu:stuList) {
				String isDiagnosed = "��";
				if (stu.getDiagnosed().equals("0")) {
					isDiagnosed = "��";
				}
				String[] strTemp = {stu.getId(), stu.getName(), stu.getSex(), stu.getCollege(), stu.getMajor(), 
						stu.getPhoneNumber(), stu.getRecordDate()+"", stu.getProvince(), stu.getCity(), isDiagnosed, 
						stu.getTemperature()+""};
				data[count] = strTemp;
				count++;
			}
		} else {
			data = new String[teaList.size()][];
			titles = new String[] { "ѧ��", "����", "�Ա�", "ѧԺ", "��ϵ�绰", "��¼����", "����ʡ", "������", "�Ƿ��Ⱦ", "����" };
			// Add data
			int count = 0;
			for (Teacher tea : teaList) {
				String isDiagnosed = "��";
				if (tea.getDiagnosed().equals("0")) {
					isDiagnosed = "��";
				}
				String[] strTemp = { tea.getId(), tea.getName(), tea.getSex(), tea.getCollege(), tea.getPhoneNumber(), 
						tea.getRecordDate() + "", tea.getProvince(), tea.getCity(), isDiagnosed, tea.getTemperature() + "" };
				data[count] = strTemp;
				count++;
			}
		}
		// JTable
		DefaultTableModel dtm = new DefaultTableModel(data, titles);
		JTable showTable = new JTable(dtm);
		showTable.setFont(tableFont);
		showTable.setEnabled(false);
		showTable.setRowHeight(24);
		FitTableColumns(showTable);
		showTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//JScrollPane
		JScrollPane scrollPane = new JScrollPane(showTable);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		showPanel.add(scrollPane, BorderLayout.CENTER);
		
		//southPanel
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		Font btnFont = new Font("����", Font.BOLD, 16);
		JButton btn = new JButton("����ΪExcel");
		btn.setFont(btnFont);
		southPanel.add(btn);
		showPanel.add(southPanel, BorderLayout.SOUTH);
		
		//westPanel
		JPanel westPanel = new JPanel();
		showPanel.add(westPanel, BorderLayout.WEST);
		
		// eastPanel
		JPanel eastPanel = new JPanel();
		showPanel.add(eastPanel, BorderLayout.EAST);
		
		// Add to JFrame
		showWin.add(showPanel);
		
		// Add Action Listener
		btn.addActionListener(event -> {
			String path = JOptionPane.showInputDialog(null, "�������������·��(˫��б��)��F:\\\\excel", "���·��", 
					JOptionPane.DEFAULT_OPTION);
			String outPath = com.nwnu.util.ExcelUtil.createExcel(stuList, path);
			JOptionPane.showConfirmDialog(null, "Excel����·��Ϊ��"+outPath, "��Ϣ��ʾ", JOptionPane.DEFAULT_OPTION);
		});
		
	}
	
	/**
	 * ���˷������ڱ�����п�����Ӧ
	 * 
	 * @param table
	 */
	public void FitTableColumns(JTable table) {
		JTableHeader header = table.getTableHeader();
		int rowCount = table.getRowCount();
		@SuppressWarnings("rawtypes")
		Enumeration columns = table.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) table.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(table, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) table.getCellRenderer(row, col)
						.getTableCellRendererComponent(table, table.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // ���к���Ҫ
			column.setWidth(width + table.getIntercellSpacing().width);
		}
	}
}
