package user.dto;

import java.sql.Date;

public class MainBoardDTO {
	
	private int mbNum; // 글 번호
	private String mbName; // 작성자 이름
	private String mbTitle; // 제목
	private String mbContent; // 내용
	private Date mbWriteDate; //디비랑쓸땐 sql.date, 작성날짜
	private int mbHit; // 조회수
	private String mbId; // 작성자 아이디
	private String mbfileName; // 첨부파일 
	
	public MainBoardDTO() {
		
	}
	
	public MainBoardDTO(String mbname, String mbtitle, String mbcontent, String mbid) {
		this.mbName = mbname;
		this.mbTitle = mbtitle;
		this.mbContent = mbcontent;
		this.mbId= mbid;	

	}
	
	
	public int getMbNum() {
		return mbNum;
	}
	public void setMbNum(int mbNum) {
		this.mbNum = mbNum;
	}
	public String getMbName() {
		return mbName;
	}
	public void setMbName(String mbName) {
		this.mbName = mbName;
	}
	public String getMbTitle() {
		return mbTitle;
	}
	public void setMbTitle(String mbTitle) {
		this.mbTitle = mbTitle;
	}
	public String getMbContent() {
		return mbContent;
	}
	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}
	public Date getMbWriteDate() {
		return mbWriteDate;
	}
	public void setMbWriteDate(Date mbWriteDate) {
		this.mbWriteDate = mbWriteDate;
	}
	public int getMbHit() {
		return mbHit;
	}
	public void setMbHit(int mbHit) {
		this.mbHit = mbHit;
	}
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getMbfileName() {
		return mbfileName;
	}
	public void setMbfileName(String mbfileName) {
		this.mbfileName = mbfileName;
	}
}
