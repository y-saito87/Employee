package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;


@Repository
public class DepartmentDao implements BaseDao<Department> {
	@Autowired
	DepartmentRepository repository;
	
	@Override
	public List<Department> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Department findById(Integer deptId) throws DataNotFoundException {
		return this.repository.findById(deptId).orElseThrow(() -> new DataNotFoundException());
	}
	
	@Override
	public void save(Department department) {
		this.repository.save(department);
	}
	
	@Override
	public void deleteById(Integer deptId) {
		try {
			Department department = this.findById(deptId);
			this.repository.deleteById(department.getDept_id());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
}