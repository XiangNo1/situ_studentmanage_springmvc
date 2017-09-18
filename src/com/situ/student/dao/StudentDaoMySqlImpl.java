package com.situ.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.Student;
import com.situ.student.util.JdbcUtil;
import com.situ.student.util.ModelConvert;
import com.situ.student.vo.SearchCondition;

public class StudentDaoMySqlImpl implements IStudentDao {


	

	@Override
	public int getSearchStudentCount(int index, Integer pageSize, SearchCondition searchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from student where 1=1 ";
			List<String> listCondition = new ArrayList<String>();
			if (searchCondition.getName() != null && searchCondition.getName() != "") {
				
				sql += " and name like ? ";
				listCondition.add("%" + searchCondition.getName() + "%");
			}
			if (searchCondition.getAge() != null
		               && !searchCondition.getAge().equals("")) {
		           sql += " and age = ? ";
		           listCondition.add(searchCondition.getAge());
		       }
		       if (searchCondition.getGender() != null
		               && !searchCondition.getGender().equals("")) {
		           sql += " and gender = ? ";
		           listCondition.add(searchCondition.getGender());
		       }
		       if (searchCondition.getAddress() != null
		               && !searchCondition.getAddress().equals("")) {
		           sql += " and address = ? ";
		           listCondition.add(searchCondition.getAddress());
		       }
		       if (searchCondition.getBanji() != null
		               && !searchCondition.getBanji().equals("")) {
		           sql += " and Banji = ? ";
		           listCondition.add(searchCondition.getBanji());
		       }
			preparedStatement = connection.prepareStatement(sql);
			for(int i =0 ; i <listCondition.size();i++){
		    	   preparedStatement.setString(i+1, listCondition.get(i));
		    	   System.out.println(listCondition.get(i));
		       }
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
		           result++;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findSearchStudentList(int index, Integer pageSize, SearchCondition searchCondition) {
		// TODO Auto-generated method stub
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				List<Map<String, Object>> list = null;
				try {
					connection = JdbcUtil.getConnection();
					String sql = "select student.id as id,student.name as student_name,student.age as age,student.gender as gender,student.address as address,student.birthday as birthday,banji.name as banji_name from student left join banji on student.banji_id=banji.id where 1=1 ";
					List<String> listCondition = new ArrayList<String>();
					if (searchCondition.getName() != null && searchCondition.getName() != "") {
						
						sql += " and student.name like ? ";
						listCondition.add("%" + searchCondition.getName() + "%");
					}
					if (searchCondition.getAge() != null
				               && !searchCondition.getAge().equals("")) {
				           sql += " and age = ? ";
				           listCondition.add(searchCondition.getAge());
				       }
				       if (searchCondition.getGender() != null
				               && !searchCondition.getGender().equals("")) {
				           sql += " and gender = ? ";
				           listCondition.add(searchCondition.getGender());
				       }
				       if (searchCondition.getAddress() != null
				               && !searchCondition.getAddress().equals("")) {
				           sql += " and address = ? ";
				           listCondition.add(searchCondition.getAddress());
				       }
				       if (searchCondition.getBanji() != null
				               && !searchCondition.getBanji().equals("")) {
				           sql += " and banji.name = ? ";
				           listCondition.add(searchCondition.getBanji());
				       }
				       sql += " limit ?,?; ";
					preparedStatement = connection.prepareStatement(sql);
					for(int i =0 ; i <listCondition.size();i++){
						System.out.println(listCondition);
				    	   preparedStatement.setString(i+1, listCondition.get(i));
				       }
					preparedStatement.setInt(listCondition.size() + 1, index);
					preparedStatement.setInt(listCondition.size() + 2, pageSize);
					resultSet = preparedStatement.executeQuery();
					System.out.println(preparedStatement.toString());
					list = ModelConvert.convertList(resultSet);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					JdbcUtil.close(connection, preparedStatement, resultSet);
				}
				return list;
	}

	@Override
	public List<Accounts> findAccountsByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Accounts> list = new ArrayList<Accounts>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from accounts where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name1 = resultSet.getString("name");
				String password = resultSet.getString("password");
				Accounts accounts = new Accounts(name1, password);
				list.add(accounts);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteStudentById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM student WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
	}

	@Override
	public int getBanjiTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from banji;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Banji> findBanjiPageBeanList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Banji> list =new ArrayList<Banji>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from banji limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           Banji banji = new Banji(id, name);
		           list.add(banji);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getKechengTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from kecheng;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Kecheng> findKechengPageBeanList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Kecheng> list =new ArrayList<Kecheng>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from kecheng limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           int credit = resultSet.getInt("credit");
		           Kecheng kecheng = new Kecheng(id, name, credit);
		           list.add(kecheng);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Banji> findAllBanji() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				
				list.add(banji);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public void addStudent(Student student) {
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		           "yyyy-MM-dd");
		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO student(NAME,age,gender,address,birthday,banji_id) VALUES(?,?,?,?,?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.setString(4, student.getAddress());
			String time = simpleDateFormat.format(student.getBirthday());
			preparedStatement.setString(5, time);
			preparedStatement.setInt(6,student.getBanji_id());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
	}

	@Override
	public boolean checkStudentName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				isExist = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return isExist;
	}

	@Override
	public void deleteAllStudent(String[] ids) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM student WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			for(int i =0;i<ids.length; i++){
				System.out.println(ids[i]);
				preparedStatement.setInt(1, Integer.parseInt(ids[i]));
				preparedStatement.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public List<Map<String, Object>> findById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT student.id AS id,student.name AS studentname,student.age AS age,student.gender AS gender,student.address AS address,student.birthday AS birthday,banji.id as banjiid,banji.name AS banjiname FROM student LEFT JOIN banji ON student.banji_id=banji.id WHERE student.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.convertList(resultSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Banji> findBanji() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				
				list.add(banji);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public void updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		           "yyyy-MM-dd");
		try {
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE student SET name=?,age=?,gender=?,address=?,birthday=?,banji_id=? WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.setString(4, student.getAddress());
			String time = simpleDateFormat.format(student.getBirthday());
			preparedStatement.setString(5, time);
			preparedStatement.setInt(6, student.getBanji_id());
			preparedStatement.setInt(7, student.getId());
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public void addBanji(Banji banji) {
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO banji(NAME) VALUES(?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banji.getName());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
	}

	@Override
	public void deleteBanji(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM banji WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public Banji findBanjiById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Banji banji = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				banji = new Banji(id, name);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return banji;
	}

	@Override
	public void updateBanji(Banji banji) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE banji SET name=? WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banji.getName());
			preparedStatement.setInt(2, banji.getId());
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public boolean checkBanjiName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return isExist;
	}

	@Override
	public void addKecheng(Kecheng kecheng) {
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO kecheng(NAME,credit) VALUES(?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, kecheng.getName());
			preparedStatement.setInt(2, kecheng.getCredit());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
	}

	@Override
	public void deleteKecheng(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM kecheng WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public Kecheng findKechengById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Kecheng kecheng = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM kecheng where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int credit = resultSet.getInt("credit");
				kecheng = new Kecheng(id, name, credit);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return kecheng;
	}

	@Override
	public void updateKecheng(Kecheng kecheng) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE kecheng SET name=?,credit=? WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, kecheng.getName());
			preparedStatement.setInt(2, kecheng.getCredit());
			preparedStatement.setInt(3, kecheng.getId());
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public boolean checkKechengName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM kecheng where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				isExist = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return isExist;
	}

	@Override
	public int getJiaowuBanjiTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT student.name AS student_name,banji.NAME AS banji_name,kecheng.name AS kecheng_name,kecheng.credit AS kecheng_credit  FROM student LEFT JOIN banji ON student.banji_id=banji.id LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List findJiaowuBanjiPageBeanList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT student.name AS student_name,banji.NAME AS banji_name,kecheng.name AS kecheng_name,kecheng.credit AS kecheng_credit  FROM student LEFT JOIN banji ON student.banji_id=banji.id LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.convertList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Student> findAllStudent() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM student;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("address");
				Date birthday = resultSet.getDate("birthday");
				int banji = resultSet.getInt("banji_id");

				Student student = new Student(id, name, age, gender, address,birthday, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	
	@Override
	public List findJiaowuBanjiPageBeanList(int index, int pageSize, Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT student.name AS student_name,banji.NAME AS banji_name,kecheng.name AS kecheng_name,kecheng.credit AS kecheng_credit  FROM student LEFT JOIN banji ON student.banji_id=banji.id LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id where student.id=? limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, index);
			preparedStatement.setInt(3, pageSize);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.convertList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getJiaowuBanjiTotalCount(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT student.name AS student_name,banji.NAME AS banji_name,kecheng.name AS kecheng_name,kecheng.credit AS kecheng_credit  FROM student LEFT JOIN banji ON student.banji_id=banji.id LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id where student.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Kecheng> findAllKecheng() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Kecheng> list = new ArrayList<Kecheng>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM kecheng;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int credit = resultSet.getInt("credit");
				Kecheng kecheng = new Kecheng(id, name, credit);
				
				list.add(kecheng);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public void addBanjiKecheng(Integer banji, Integer kecheng) {
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO banji_kecheng VALUES(?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, banji);
			preparedStatement.setInt(2, kecheng);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
	}

	@Override
	public int getBanjiKechengTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT banji.name AS banji_name,kecheng.name AS kecheng_name,kecheng.credit,banji_kecheng.banji_id,banji_kecheng.kecheng_id AS kecheng_credit FROM banji LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List findBanjiKechengPageBeanList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT banji.name AS banji_name,kecheng.name AS kecheng_name,kecheng.credit AS kecheng_credit,banji_kecheng.banji_id,banji_kecheng.kecheng_id FROM banji LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.convertList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public void deleteBanjiKecheng(Integer banji_id, Integer kecheng_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM banji_kecheng WHERE banji_id=? AND kecheng_id=?;";
			preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, banji_id);
				preparedStatement.setInt(2, kecheng_id);
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public int getBanjiKechengTotalCount(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT banji.name AS banji_name,kecheng.name AS kecheng_name,kecheng.credit,banji_kecheng.banji_id,banji_kecheng.kecheng_id AS kecheng_credit FROM banji LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id where banji.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List findBanjiKechengPageBeanList(int index, int pageSize, Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT banji.name AS banji_name,kecheng.name AS kecheng_name,kecheng.credit AS kecheng_credit,banji_kecheng.banji_id,banji_kecheng.kecheng_id FROM banji LEFT JOIN banji_kecheng ON banji.id=banji_kecheng.banji_id LEFT JOIN kecheng ON banji_kecheng.kecheng_id=kecheng.id where banji.id=? limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, index);
			preparedStatement.setInt(3, pageSize);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.convertList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getBanjiStudentTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji LEFT JOIN student ON banji.id=student.banji_id;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List findBanjiStudentPageBeanList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT banji.name as banji_name,student.name as student_name FROM banji LEFT JOIN student ON banji.id=student.banji_id limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.convertList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getBanjiStudent2TotalCount(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji LEFT JOIN student ON banji.id=student.banji_id where banji.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List findBanjiStudent2PageBeanList(int index, int pageSize, Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT banji.name as banji_name,student.name as student_name FROM banji LEFT JOIN student ON banji.id=student.banji_id where banji.id=? limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, index);
			preparedStatement.setInt(3, pageSize);
			resultSet = preparedStatement.executeQuery();
			list = ModelConvert.convertList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
}
