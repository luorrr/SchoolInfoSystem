package com.nwnu.util;

//import jxl.Cell;
//import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Filename: ExcelUtil.java
 * 
 * ��Excel������
 * 
 * @author Luor
 * @version 1.0
 */
public class ExcelUtil {
	
	// ����Ҫ���ֶ�
	//private static String passString = "count";

	/**
	 * ���÷�����list����ת��Excel�ļ�
	 * 
	 * @param list ���󼯺�
	 * @param path ���·��
	 * @return ������ļ�·��
	 */
	public static String createExcel(List<? extends Object> list, String path) {
		String result = "";
		if (list.size() == 0 || list == null) {
			result = "û�ж�����Ϣ";
		} else {
			Object o = list.get(0);
			Class<? extends Object> clazz = o.getClass();
			String className = clazz.getSimpleName();
			// ͨ�������ȡ�ֶ�����
			Field[] fields = clazz.getDeclaredFields();
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
			String name = fileName.concat(".xls");
			WritableWorkbook book = null;
			File file = null;
			try {
				file = new File(path.concat(File.separator).concat(name));
				// ����xls�ļ�
				book = Workbook.createWorkbook(file);
				WritableSheet sheet = book.createSheet(className, 0);
				// ��
				int i = 0;
				// ��
				int j = 0;
				for (Field f : fields) {
					j = 0;
					// ������ֶ�����д��excel��һ����
					Label label = new Label(i, j, f.getName());
					sheet.addCell(label);
					j = 1;
					for (Object obj : list) {
						Object temp = getFieldValueByName(f.getName(), obj);
						String strTemp = "";
						if (temp != null) {
							strTemp = temp.toString();
						}
						// ��ÿ��������ֶε�����д����һ��excel��
						sheet.addCell(new Label(i, j, strTemp));
						j++;
					}
					i++;
				}
				book.write();
				result = file.getPath();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result = "SystemException";
				e.printStackTrace();
			} finally {
				fileName = null;
				name = null;
				folder = null;
				file = null;
				if (book != null) {
					try {
						book.close();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						result = "WriteException";
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						result = "IOException";
						e.printStackTrace();
					}
				}
			}

		}
		// ����ļ�·��
		return result;
	}

	/**
	 * ���÷���������������ȡ����ֵ
	 * 
	 * @param fieldName ������
	 * @param o
	 * @return ����ֵ
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			// ��ȡ������
			String getter = "get" + firstLetter + fieldName.substring(1);
			// ��ȡ��������
			Method method = o.getClass().getMethod(getter, new Class[] {});
			// ��invoke���ô˶����get�ֶη���
			Object value = method.invoke(o, new Object[] {});
			// ����ֵ
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
