package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	private final EmpService es;
	private final JavaMailSender mailSender;
	
	@RequestMapping(value = "listEmp") 
	public String empList(Emp emp , String currentPage, Model model) {
		System.out.println("EmpController Start listEmp..." );
		// Emp 전체 Count  23
		int totalEmp =  es.totalEmp();

		// Paging 작업
		Paging   page = new Paging(totalEmp, currentPage);
		// Parameter emp --> Page만 추가 Setting
		emp.setStart(page.getStart());   // 시작시 1
		emp.setEnd(page.getEnd());       // 시작시 10 

		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()=>" + listEmp.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp" , listEmp);
		model.addAttribute("page"    , page);

		return "list";
		
	}
	
	
	@GetMapping(value = "detailEmp")
	public String detailEmp(int empno , Model model) {
		System.out.println("EmpController Start detailEmp..." );
//		1. EmpService안에 detailEmp method 선언
//		   1) parameter : empno
//		   2) Return      Emp
//
//		2. EmpDao   detailEmp method 선언 
////		                    mapper ID   ,    Parameter
//		emp = session.selectOne("tkEmpSelOne",    empno);
		Emp emp = es.detailEmp(empno);
		model.addAttribute("emp",emp);

		return "detailEmp";


	}
	
	
	@GetMapping(value = "updateFormEmp")
	public String updateFormEmp(int empno , Model model) {
		System.out.println("EmpController Start updateForm..." );
		
		Emp emp = es.detailEmp(empno);
	    System.out.println("emp.getEname()->"+emp.getEname());
	    System.out.println("emp.getHiredate()->"+emp.getHiredate());
		// 문제 
		// 1. DTO  String hiredate
		// 2.View : 단순조회 OK ,JSP에서 input type="date" 문제 발생
		// 3.해결책  : 년월일만 짤라 넣어 주어야 함 
		String hiredate = "";
		if (emp.getHiredate() != null) {
			hiredate = emp.getHiredate().substring(0, 10);
			emp.setHiredate(hiredate);
		}
		System.out.println("hiredate->"+hiredate);

	    
		model.addAttribute("emp",emp);
		return "updateFormEmp";

	}
	
	@PostMapping(value="updateEmp")
	public String updateEmp(Emp emp, Model model) {
        log.info("updateEmp Start...");

//      1. EmpService안에 updateEmp method 선언
//      1) parameter : Emp
//      2) Return      updateCount (int)
//
//   2. EmpDao updateEmp method 선언
////                              mapper ID   ,    Parameter
//   updateCount = session.update("tkEmpUpdate",emp);
		int updateCount = es.updateEmp(emp);
		System.out.println("empController es.updateEmp updateCount-->"+updateCount);
		model.addAttribute("uptCnt",updateCount);    // Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test");    // Test Controller간 Data 전달
   		return "forward:listEmp";   
   		//return "redirect:listEmp";   
	}
	
	@RequestMapping(value = "writeFormEmp" )
	public String writeFormEmp(Model model) {
		System.out.println("empController writeFormEmp Start...");
		// 관리자 사번 만 Get
		List<Emp> empList =  es.listManager();
		System.out.println("EmpController writeForm empList.size->"+empList.size());
		model.addAttribute("empMngList",empList); // emp Manager List
		
		// 부서(코드,부서명)
		List<Dept> deptList = es.deptSelect(); 
		model.addAttribute("deptList", deptList); // dept
		System.out.println("EmpController writeForm deptList.size->"+deptList.size());

		return "writeFormEmp";
	}

	@RequestMapping(value = "writeFormEmp3" )
	public String writeFormEmp3(Model model) {
		System.out.println("empController writeFormEmp3 Start...");
		// 관리자 사번 만 Get
		List<Emp> empList =  es.listManager();
		System.out.println("EmpController writeFormEmp3 empList.size->"+empList.size());
		model.addAttribute("empMngList",empList); // emp Manager List
		
		// 부서(코드,부서명)
		List<Dept> deptList = es.deptSelect(); 
		model.addAttribute("deptList", deptList); // dept
		System.out.println("EmpController writeFormEmp3 deptList.size->"+deptList.size());

		return "writeFormEmp3";
	}

	@PostMapping(value = "writeEmp")
	public String writeEmp(Emp emp , Model model) {
		System.out.println("EmpController Start writeEmp..." );
		
		// Service, Dao , Mapper명[insertEmp] 까지 -> insert
		int insertResult = es.insertEmp(emp);  
		if (insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeFormEmp";
		}
	}
	
	// Validation시 참조
	@PostMapping(value = "writeEmp3")
	public String writeEmp3(@ModelAttribute("emp") @Valid Emp emp 
			               , BindingResult result
			               , Model model) {
		System.out.println("EmpController Start writeEmp3..." );
		
		// Validation 오류시 Result
		if(result.hasErrors()) {
			System.out.println("EmpController writeEmp3 hasErrors... ");
			model.addAttribute("msg","BindingResult 입력 실패 확인해 보세요");
			return "forward:writeFormEmp3";
		}
		
		// Service, Dao , Mapper명[insertEmp] 까지 -> insert
		int insertResult = es.insertEmp(emp); 
		if (insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeFormEmp3";
		}
	}	
	
	
	
	
	
	
	@GetMapping(value="confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detailEmp(empno);
		model.addAttribute("empno", empno);
		if (emp !=null) {
			System.out.println("empController confirm 중복된 사번.. ");
			model.addAttribute("msg","중복된 사번입니다");			
			return "forward:writeFormEmp";
		} else {
			System.out.println("empController confirm 사용 가능한 사번.. ");
			model.addAttribute("msg","사용 가능한 사번입니다");
			return "forward:writeFormEmp";
		}
	}	
	

	// Controller -->  deleteEmp    1.parameter : empno
	// name -> Service, dao , mapper
	// return -> listEmp
	@RequestMapping(value="deleteEmp")
	public String deleteEmp(int empno, Model model) {
		System.out.println("EmpController Start delete..." );
		int result = es.deleteEmp(empno);
		return "redirect:listEmp";
	}

	
	@RequestMapping(value = "listSearch3")
	public String listSearch3(Emp emp , String currentPage, Model model) {
		System.out.println("EmpController Start listEmp..." );
		// Emp 전체 Count  25
		int totalEmp =  es.condTotalEmp(emp);
		System.out.println("EmpController listSearch3 totalEmp=>" + totalEmp);
		// Paging 작업
		Paging   page = new Paging(totalEmp, currentPage);
		// Parameter emp --> Page만 추가 Setting
		emp.setStart(page.getStart());   // 시작시 1
		emp.setEnd(page.getEnd());       // 시작시 10 

		List<Emp> listSearchEmp = es.listSearchEmp(emp);
		System.out.println("EmpController listSearch3 listSearchEmp.size()=>" + listSearchEmp.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp" , listSearchEmp);
		model.addAttribute("page"    , page);
		
		return "list";

	}	
	
	@GetMapping(value="listEmpDept")
	public String listEmpDept(Model model) {
		System.out.println("EmpController listEmpDept Start...");
		// Service ,DAO -> listEmpDept
		// Mapper만 ->tkListEmpDept
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept",listEmpDept);

		return "listEmpDept";

	}
	
	@RequestMapping(value="mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("mailSending...");
		String tomail = "ttaekwang3@naver.com";              // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "ttaekwang3@gmail.com";
		String title = "mailTransport 입니다";                 // 제목
	    try {
			//  Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true , "UTF-8");
			messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);       // 받는사람 이메일
			messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다 : " + tempPassword); // 메일 내용
			System.out.println("임시 비밀번호입니다 : " + tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\hwa.png");
			messageHelper.addAttachment(MimeUtility.encodeText("hwa3.png", "UTF-8", "B"),dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1);   // 정상 전달
			// DB Logic 구성 
		} catch (Exception e) {
			System.out.println("mailTransport e.getMessage()->"+e.getMessage());
			model.addAttribute("check", 2);  // 메일 전달 실패
		}
		
		return "mailResult";

	}

	//  Procedure Test 입력화면 
	@RequestMapping(value = "writeDeptIn")
	public String writeDeptIn(Model model) {
		 System.out.println("writeDeptIn Start..");
	     return "writeDept3";
	}
	
	// Procedure 통한 Dept 입력후 VO 전달 
	@PostMapping(value="writeDept")
	public String writeDept(DeptVO deptVO , Model model) {
		es.insertDept(deptVO);
		if (deptVO == null) {
			System.out.println("deptVO NULL");
		}else {
			System.out.println("deptVO.getOdeptno()->"+deptVO.getOdeptno());
			System.out.println("deptVO.getOdname()->"+deptVO.getOdname());
			System.out.println("deptVO.getOloc()->"+deptVO.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다 ^^");
			model.addAttribute("dept", deptVO);
			
		}
	
		return "writeDept3";

	}	

	// Map 적용
	@GetMapping(value = "writeDeptCursor")
	public String writeDeptCursor(Model model) {
	    System.out.println("EmpController writeDeptCursor Start...");
	    // 부서범위 조회
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 55);

        es.selListDept(map);
        List<Dept> deptLists = (List<Dept>) map.get("dept");
        for(Dept dept : deptLists) {
        	System.out.println("dept.getDname->"+dept.getDname());
			System.out.println("dept.getLoc->"+dept.getLoc());
        }
		System.out.println("deptList Size->"+ deptLists.size());
		model.addAttribute("deptList", deptLists);
		
		 return "writeDeptCursor";
	}
	
	// interCeptor 시작 화면 
	@RequestMapping(value = "interCeptorForm")
	public String interCeptorForm(Model model) {
		System.out.println("interCeptorForm Start");
	    return "interCeptorForm";
	}
	
	// 2.interCeptor Number 2
	@RequestMapping(value="interCeptor")
	public String interCeptor(String id, Model model) {
		System.out.println("EmpController  interCeptor  Test Start");
		System.out.println("EmpController  interCeptor id->"+id);
		// 존재 : 1  , 비존재 : 0
		int memCnt = es.memCount(id); 
		
		System.out.println("EmpController interCeptor memCnt ->"+ memCnt);

		model.addAttribute("id",id);
		model.addAttribute("memCnt",memCnt);
		System.out.println("interCeptor  Test End");
	
	
	
		return "interCeptor";   // User 존재하면  User 이용 조회 Page
	}
		
	
	 // SampleInterceptor 내용을 받아 처리 
	 @RequestMapping(value = "doMemberWrite", method = RequestMethod.GET)
	 public String doMemberWrite( Model model,  HttpServletRequest request) {
		 String ID =  (String) request.getSession().getAttribute("ID");
		 System.out.println("doMemberWrite 부터 하세요");
		 model.addAttribute("id",ID);
		 return "doMemberWrite";
	 }  

	 // interCeptor 진행 Test
	 @RequestMapping(value="doMemberList")
	 public String doMemberList(Model model, HttpServletRequest request){
		String ID =  (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberList  Test Start  ID ->"+ID);
		Member1 member1 = null;
		// Member1 List Get Service
		List<Member1> listMem = es.listMem(member1);
		model.addAttribute("ID",ID);
		model.addAttribute("listMem",listMem);
		return "doMemberList";   // User 존재하면  User 이용 조회 Page
	 }	
	
	
}
