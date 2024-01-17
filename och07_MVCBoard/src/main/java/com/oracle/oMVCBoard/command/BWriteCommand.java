package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
//		  1) model이용 , map 선언
//		  2) request 이용 ->  bName  ,bTitle  , bContent  추출
//		  3) dao  instance 선언
//		  4) write method 이용하여 저장(bName, bTitle, bContent)

			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");
			
			try {
				// 시기 --> request Parameter 받기전 Encoding 적용
				request.setCharacterEncoding("utf-8");

				String bName = request.getParameter("bName");
				String bTitle = request.getParameter("bTitle");
				String bContent = request.getParameter("bContent");
				
				BDao dao = new BDao();
				dao.write(bName, bTitle, bContent);
				
			} catch (Exception e) {
				System.out.println("BModifyCommand Exception->"+e.getMessage());
			}
		
	}

}
