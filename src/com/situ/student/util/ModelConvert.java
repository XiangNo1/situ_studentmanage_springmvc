package com.situ.student.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelConvert {

	/*
	 * 用于将rs查询结果封装为List<Map<String, Object>>对象
	 * 
	 * @param rs数据库查询结果
	 * 
	 * @return 返回list map封装后的结果
	 */
	public static List<Map<String, Object>> convertList(ResultSet rs) {
		// 新建一个map list集合用于存放多条查询记录
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData md = rs.getMetaData();// 结果集(rs)的结构信息，比如字段数、字段名等。
			int columnCount = md.getColumnCount();// 取得查询出来的字段个数
			while (rs.next()) {// 迭代rs
				// 新建一个map集合 将查询出内容按照字段名：值 的键值对形式存储在map集合中
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {// 循环所有查询出字段
					rowData.put(md.getColumnLabel(i), rs.getObject(i));
					// getColumnName(i) 获取第i个列名
					// getObject(i) 获取第i个对象的值
				}
				list.add(rowData);// 将map放入list集合中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 关闭连接
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}