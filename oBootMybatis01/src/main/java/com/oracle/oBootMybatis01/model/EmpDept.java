package com.oracle.oBootMybatis01.model;

import lombok.Data;

//Join 목적
@Data
public class EmpDept {
	// Emp용
	private int empno; 		 private String ename;
	private String job;		 private int mgr;
	private String hiredate; private int sal;
	private int comm; 		 private int deptno;
	// Dept용  (많다는 가정)
	private String dname;
	private String loc;
}
