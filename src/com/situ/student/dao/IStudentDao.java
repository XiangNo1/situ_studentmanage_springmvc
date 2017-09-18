package com.situ.student.dao;

import java.util.List;
import java.util.Map;

import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.Student;
import com.situ.student.vo.SearchCondition;

public interface IStudentDao {

	int getSearchStudentCount(int index, Integer pageSize, SearchCondition searchCondition);

	List<Map<String, Object>> findSearchStudentList(int index, Integer pageSize, SearchCondition searchCondition);

	List<Accounts> findAccountsByName(String name);

	void deleteStudentById(Integer id);

	int getBanjiTotalCount();

	List<Banji> findBanjiPageBeanList(int index, int pageSize);

	int getKechengTotalCount();

	List<Kecheng> findKechengPageBeanList(int index, int pageSize);

	List<Banji> findAllBanji();

	void addStudent(Student student);

	boolean checkStudentName(String name);

	void deleteAllStudent(String[] selectIds);

	List<Map<String, Object>> findById(Integer id);

	List<Banji> findBanji();

	void updateStudent(Student student);

	void addBanji(Banji banji);

	void deleteBanji(Integer id);

	Banji findBanjiById(Integer id);

	void updateBanji(Banji banji);

	boolean checkBanjiName(String name);

	void addKecheng(Kecheng kecheng);

	void deleteKecheng(Integer id);

	Kecheng findKechengById(Integer id);

	void updateKecheng(Kecheng kecheng);

	boolean checkKechengName(String name);

	int getJiaowuBanjiTotalCount();

	List findJiaowuBanjiPageBeanList(int index, int pageSize);

	List<Student> findAllStudent();

	List findJiaowuBanjiPageBeanList(int index, int pageSize, Integer id);

	int getJiaowuBanjiTotalCount(Integer id);

	List<Kecheng> findAllKecheng();

	void addBanjiKecheng(Integer banji, Integer kecheng);

	int getBanjiKechengTotalCount();

	List findBanjiKechengPageBeanList(int index, int pageSize);

	void deleteBanjiKecheng(Integer banji_id, Integer kecheng_id);

	int getBanjiKechengTotalCount(Integer id);

	List findBanjiKechengPageBeanList(int index, int pageSize, Integer id);

	int getBanjiStudentTotalCount();

	List findBanjiStudentPageBeanList(int index, int pageSize);

	int getBanjiStudent2TotalCount(Integer id);

	List findBanjiStudent2PageBeanList(int index, int pageSize, Integer id);

}
