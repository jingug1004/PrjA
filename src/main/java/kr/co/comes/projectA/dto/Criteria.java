package kr.co.comes.projectA.dto;

public class Criteria {

	private int page; // 현재 페이지수 
	private int perPageNum; // 페이지에 보여지는 게시글 수

	public Criteria() {
		this.page = 1;
		this.perPageNum = 7;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {

		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public int getPageStart() { 
		return (this.page -1) * perPageNum;
	}

	public void setPerPageNum(int perPageNum) { 

		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + "," + "perPageNum=" + perPageNum + "]";
	}
}
