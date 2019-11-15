package com.newlecture.web.controller.member;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.jdbc.JdbcMemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

public class NewlecMemberService implements MemberService {

	private MemberDao memberDao;
	
	public NewlecMemberService() {
		memberDao = new JdbcMemberDao();
	}
	
	@Override
	public boolean isValidMember(String id, String pwd) {
		Member member = memberDao.get(id);
		
		System.out.println(member.toString());
				
		if(member == null) // 사용자가 없다면?
			return false;
		else if(!member.getPwd().equals(pwd)) // 비밀번호가 일치하지 않는다면
			return false;
		
		return true;
	}

	@Override
	public boolean isDuplicatedId(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

}
