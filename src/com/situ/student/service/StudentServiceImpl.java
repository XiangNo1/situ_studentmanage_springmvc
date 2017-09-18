package com.situ.student.service;

import java.util.List;
import java.util.Map;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.StudentDaoMySqlImpl;
import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.Student;
import com.situ.student.vo.PageBean;
import com.situ.student.vo.SearchCondition;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao studentDao = new StudentDaoMySqlImpl();

	@Override
	public PageBean getStudentPageBean(SearchCondition searchCondition) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(searchCondition.getPageIndex());
		pageBean.setPageSize(searchCondition.getPageSize());
		//总条数
		int index =( searchCondition.getPageIndex() - 1) * searchCondition.getPageSize();
		int totalCount = studentDao.getSearchStudentCount(index, searchCondition.getPageSize(),searchCondition);
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount /searchCondition.getPageSize() );
		pageBean.setTotalPage(totalPage);
		List<Map<String, Object>> list = studentDao.findSearchStudentList(index, searchCondition.getPageSize(),searchCondition);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public List<Accounts> findAccountsByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.findAccountsByName(name);
	}

	@Override
	public void deleteStudentById(Integer id) {
		// TODO Auto-generated method stub
		studentDao.deleteStudentById(id);
	}

	@Override
	public PageBean getPageBeanBanji(int pageIndex, int pageSize) {
		PageBean<Banji> pageBean = new PageBean<Banji>();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getBanjiTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List<Banji> list = studentDao.findBanjiPageBeanList(index, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public PageBean getPageBeanKecheng(int pageIndex, int pageSize) {
		PageBean<Kecheng> pageBean = new PageBean<Kecheng>();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getKechengTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List<Kecheng> list = studentDao.findKechengPageBeanList(index, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public List<Banji> findAllBanji() {
		// TODO Auto-generated method stub
		return studentDao.findAllBanji();
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentDao.addStudent(student);
	}

	@Override
	public boolean checkStudentName(String name) {
		// TODO Auto-generated method stub
		return studentDao.checkStudentName(name);
	}

	@Override
	public void deleteAllStudent(String[] selectIds) {
		// TODO Auto-generated method stub
		studentDao.deleteAllStudent(selectIds);
	}

	@Override
	public List<Map<String, Object>> findById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.findById(id);
	}

	@Override
	public List<Banji> findBanji() {
		// TODO Auto-generated method stub
		return studentDao.findBanji();
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		studentDao.updateStudent(student);
	}

	@Override
	public void addBanji(Banji banji) {
		// TODO Auto-generated method stub
		studentDao.addBanji(banji);
	}

	@Override
	public void deleteBanji(Integer id) {
		// TODO Auto-generated method stub
		studentDao.deleteBanji(id);
	}

	@Override
	public Banji findBanjiById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.findBanjiById(id);
	}

	@Override
	public void updateBanji(Banji banji) {
		// TODO Auto-generated method stub
		studentDao.updateBanji(banji);
	}

	@Override
	public boolean checkBanjiName(String name) {
		// TODO Auto-generated method stub
		return studentDao.checkBanjiName(name);
	}

	@Override
	public void addKecheng(Kecheng kecheng) {
		// TODO Auto-generated method stub
		studentDao.addKecheng(kecheng);
	}

	@Override
	public void deleteKecheng(Integer id) {
		// TODO Auto-generated method stub
		studentDao.deleteKecheng(id);
	}

	@Override
	public Kecheng findKechengById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.findKechengById(id);
	}

	@Override
	public void updateKecheng(Kecheng kecheng) {
		// TODO Auto-generated method stub
		studentDao.updateKecheng(kecheng);
	}

	@Override
	public boolean checkKechengName(String name) {
		// TODO Auto-generated method stub
		return studentDao.checkKechengName(name);
	}

	@Override
	public PageBean getPageBeanJiaowuBanji(int pageIndex, int pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getJiaowuBanjiTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List list = studentDao.findJiaowuBanjiPageBeanList(index, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return studentDao.findAllStudent();
	}

	@Override
	public PageBean getPageBeanJiaowuBanjiSearch(int pageIndex, int pageSize, Integer id) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getJiaowuBanjiTotalCount(id);
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List list = studentDao.findJiaowuBanjiPageBeanList(index, pageSize, id);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public List<Kecheng> findAllKecheng() {
		// TODO Auto-generated method stub
		return studentDao.findAllKecheng();
	}

	@Override
	public void addBanjiKecheng(Integer banji, Integer kecheng) {
		// TODO Auto-generated method stub
		studentDao.addBanjiKecheng(banji, kecheng);
	}

	@Override
	public PageBean getPageBeanBanjiKecheng(int pageIndex, int pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getBanjiKechengTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List list = studentDao.findBanjiKechengPageBeanList(index, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void deleteBanjiKecheng(Integer banji_id, Integer kecheng_id) {
		// TODO Auto-generated method stub
		studentDao.deleteBanjiKecheng(banji_id, kecheng_id);
	}

	@Override
	public PageBean getPageBeanBanjiKechengSearch(int pageIndex, int pageSize, Integer id) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getBanjiKechengTotalCount(id);
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List list = studentDao.findBanjiKechengPageBeanList(index, pageSize, id);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public PageBean getPageBeanBanjiStudent(int pageIndex, int pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getBanjiStudentTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List list = studentDao.findBanjiStudentPageBeanList(index, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public PageBean getPageBeanBanjiStudentSearch(int pageIndex, int pageSize, Integer id) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getBanjiStudent2TotalCount(id);
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List list = studentDao.findBanjiStudent2PageBeanList(index, pageSize, id);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	
}
