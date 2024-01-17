package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// 1. model Map선언
		Map<String, Object> map = model.asMap();

		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		try {
			// 시기 --> request Parameter 받기전 Encoding 적용
			request.setCharacterEncoding("utf-8");

			// 2. parameter ->  bId, bName , bTitle , bContent
			String bId = request.getParameter("bId");
			String bName = request.getParameter("bName");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");

			BDao  dao = new BDao();
			dao.modify(bId, bName, bTitle, bContent); 		
		} catch (Exception e) {
			System.out.println("BModifyCommand Exception->"+e.getMessage());
		}
	
	}

}
