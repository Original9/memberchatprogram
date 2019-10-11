package user.dto;

import java.sql.Date;

public class BoarderDTO {
	
	private int bNum; // 글 번호
	private String bName; // 작성자 이름
	private String bTitle; // 제목
	private String bContent; // 내용
	private Date bWriteDate; //디비랑쓸땐 sql.date, 작성날짜
	private int bHit; // 조회수
	private String bId; // 작성자 아이디
	private String bfileName; // 첨부파일 \
	private String bsearch;
	
	

	public BoarderDTO() {
	}

	public BoarderDTO(String name, String title, String content, String id) {
		this.bName = name;
		this.bTitle = title;
		this.bContent = content;
		this.bId= id;	
	}
	
	
	

	public String getBsearch() {
		return bsearch;
	}

	public void setBsearch(String bsearch) {
		this.bsearch = bsearch;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Date getbWriteDate() {
		return bWriteDate;
	}

	public void setbWriteDate(Date bWriteDate) {
		this.bWriteDate = bWriteDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public String getBfileName() {
		return bfileName;
	}

	public void setBfileName(String bfileName) {
		this.bfileName = bfileName;
	}

}
