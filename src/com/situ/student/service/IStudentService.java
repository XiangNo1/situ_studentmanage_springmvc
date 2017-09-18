package com.situ.student.service;

import java.util.List;
import java.util.Map;

import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.Student;
import com.situ.student.vo.PageBean;
import com.situ.student.vo.SearchCondition;

public interface IStudentService{

	PageBean<Student> getStudentPageBean(SearchCondition searchCondition);

	List<Accounts> findAccountsByName(String name);

	void deleteStudentById(Integer id);

	PageBean getPageBeanBanji(int pageIndex1, int pageSize1);

	PageBean getPageBeanKecheng(int pageIndex1, int pageSize1);

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

	PageBean getPageBeanJiaowuBanji(int pageIndex1, int pageSize1);

	List<Student> findAllStudent();

	PageBean getPageBeanJiaowuBanjiSearch(int pageIndex1, int pageSize1, Integer studentSearch);

	List<Kecheng> findAllKecheng();

	void addBanjiKecheng(Integer banji, Integer kecheng);

	PageBean getPageBeanBanjiKecheng(int pageIndex1, int pageSize1);

	void deleteBanjiKecheng(Integer banji_id, Integer kecheng_id);

	PageBean getPageBeanBanjiKechengSearch(int pageIndex1, int pageSize1, Integer banjiSearch);

	PageBean getPageBeanBanjiStudent(int pageIndex1, int pageSize1);

	PageBean getPageBeanBanjiStudentSearch(int pageIndex1, int pageSize1, Integer banjiSearch);

}
