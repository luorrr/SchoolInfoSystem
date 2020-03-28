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
 * ·Excel工具类
 * 
 * @author Luor
 * @version 1.0
 */
public class ExcelUtil {
	
	// 不需要的字段
	//private static String passString = "count";

	/**
	 * 将list集合转成Excel文件
	 * 
	 * @param list 对象集合
	 * @param path 输出路径
	 * @return 返回文件路径
	 */
	public static String createExcel(List<? extends Object> list, String path) {
		String result = "";
		if (list.size() == 0 || list == null) {
			result = "没有对象信息";
		} else {
			Object o = list.get(0);
			Class<? extends Object> clazz = o.getClass();
			String className = clazz.getSimpleName();
			// 通过反射获取字段数组
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
				// 创建xls文件
				book = Workbook.createWorkbook(file);
				WritableSheet sheet = book.createSheet(className, 0);
				// 列
				int i = 0;
				// 行
				int j = 0;
				for (Field f : fields) {
					j = 0;
					// 这里把字段名称写入excel第一行中
					Label label = new Label(i, j, f.getName());
					sheet.addCell(label);
					j = 1;
					for (Object obj : list) {
						Object temp = getFieldValueByName(f.getName(), obj);
						String strTemp = "";
						if (temp != null) {
							strTemp = temp.toString();
						}
						// 把每个对象此字段的属性写入这一列excel中
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
		// 输出文件路径
		return result;
	}

	/**
	 * ·根据属性名获取属性值
	 * 
	 * @param fieldName 属性名
	 * @param o
	 * @return 属性值
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			// 获取方法名
			String getter = "get" + firstLetter + fieldName.substring(1);
			// 获取方法对象
			Method method = o.getClass().getMethod(getter, new Class[] {});
			// 用invoke调用此对象的get字段方法
			Object value = method.invoke(o, new Object[] {});
			// 返回值
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
