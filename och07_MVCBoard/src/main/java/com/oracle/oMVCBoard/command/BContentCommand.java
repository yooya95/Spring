package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	           //model 객체를 매개변수로 받아서 특정 게시물을 가져오고 모델에 데이터를 추가하는 역할
	public void execute(Model model) {
		//  model.asMap()  model 객체를 map<String, Object>형태로 변환하는 메서드 
		Map<String, Object> map = model.asMap();
		HttpServletRequest request =  (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");

		BDao dao = new BDao();
		BDto board = dao.contentView(bId);
		
		model.addAttribute("mvc_board", board);

	}

	
}
