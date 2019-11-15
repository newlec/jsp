package com.newlecture.web.service.newlec;

import java.util.List;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.jdbc.JdbcNoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

public class NewlecNoticeService implements NoticeService {

	private NoticeDao noticeDao; // Entity와 일치 시킨다.
	//private CommentDao commentDao;
	// 데이터베이스를 사용하는 경우는 Table과 이름을 일치시킨다.
	
	public NewlecNoticeService() {
		noticeDao = new JdbcNoticeDao();
	}
	
	@Override
	public int insertNotice(Notice notice) {
		
		return noticeDao.insert(notice);
		
		// String sql = "insert into "; 
		// 순수 자바를 이용한 업무처리를 하는 것이지
		// 데이터베이스 프로그램 있는 곳이 아니다.
		// 데이터 소스를 숨기는 역할자 Dao를 쓰기로 했으니..
		// 여기서 데이터 소스에 대한 정체를 아는 것은 잘 못 된 정리
	}

	@Override
	public List<NoticeView> getNoticeList() {
		// TODO Auto-generated method stub
		return getNoticeList(1, "title", "");
	}

	@Override
	public List<NoticeView> getNoticeList(int page) {
		// TODO Auto-generated method stub
		return getNoticeList(page, "title", "");
	}

	@Override
	public List<NoticeView> getNoticeList(int page, String field, String query) {
		// TODO Auto-generated method stub
		return noticeDao.getList(page, field, query);
	}

	@Override
	public int openNoticeList(int[] ids) {
		
		//UPDATE NOTICE SET OPEN=1 WHERE ID in (1,2,3);
		
		int i=0;
		
		i = noticeDao.openList(ids);
		/*
		for(; i<ids.length; i++) {
			Notice notice = noticeDao.get(ids[i]);
			notice.setOpen(true);
			noticeDao.update(notice);
		}*/
		
		return i;
	}

	@Override
	public int deleteNoticeList(int[] ids) {
		int i = 0;
		
		for(; i<ids.length; i++) 		
			noticeDao.delete(ids[i]);
		
		return i;
	}

	@Override
	public Notice getNotice(int id) {
		
		return noticeDao.get(id);
	}

	@Override
	public Notice getPrevNoticeByCurrentId(int id) {
		
		return noticeDao.getPrevById(id);
	}

	@Override
	public Notice getNextNoticeByCurrentId(int id) {
		// TODO Auto-generated method stub
		return null;//noticeDao.getNextById(id);
	}

	@Override
	public int updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.update(notice);
	}

	@Override
	public int deleteNotice(int id) {
		// TODO Auto-generated method stub
		return noticeDao.delete(id);
	}

	@Override
	public int getNoticeListCount(String field, String query) {
		
		return noticeDao.getListCount(field, query);
	}

}
