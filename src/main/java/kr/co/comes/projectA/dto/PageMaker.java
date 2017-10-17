package kr.co.comes.projectA.dto;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount; // 목록 갯수
	private int startPage; // 첫페이지
	private int endPage; // 마지막페이지
	private boolean prev; // 이전페이지
	private boolean next; // 다음페이지

	private int displayPageNum = 10; // 화면 밑 페이징 부분에 보이는 숫자 제한(?)
										// ex (10패이지 이상 발생시) 1 3 4 5 6 7 8 9 10
										// >

	private Criteria cri;

	// project/list 화면에서 사용할 url 생성
	public String makeListSearch(int page) {
		UriComponents uriComponents;
		if (((ListCriteria) cri).getKeyword() == null && ((ListCriteria) cri).getCategory() == null
				&& ((ListCriteria) cri).getSort() == null) {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum()).build();
		} else {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("keyword", ((ListCriteria) cri).getKeyword())
					.queryParam("sort", ((ListCriteria) cri).getSort()).build();
		}
		return uriComponents.toUriString();
	}

	// project/issue/list 화면에서 사용할 url 생성
	public String makeIssueSearch(int page) {
		UriComponents uriComponents;
		if (((ListCriteria) cri).getKeyword() == null && ((ListCriteria) cri).getCategory() == null
				&& ((ListCriteria) cri).getSort() == null) {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("projid", ((ListCriteria) cri).getProjid()).build();
		} else {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("projid", ((ListCriteria) cri).getProjid())
					.queryParam("ph_keyword", ((ListCriteria) cri).getPh_keyword())
					.queryParam("n_keyword", ((ListCriteria) cri).getN_keyword())
					.queryParam("category", ((ListCriteria) cri).getCategory())
					.queryParam("seriousness", ((ListCriteria) cri).getSeriousness())
					.queryParam("status", ((ListCriteria) cri).getStatus())
					.queryParam("sort", ((ListCriteria) cri).getSort()).build();
		}
		return uriComponents.toUriString();
	}

	// project/phase/list 화면에서 사용할 url 생성
	public String makePhaseSearch(int page) {
		UriComponents uriComponents;
		if (((ListCriteria) cri).getKeyword() == null) {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("projid", ((ListCriteria) cri).getProjid()).build();
		} else {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("keyword", ((ListCriteria) cri).getKeyword())
					.queryParam("projid", ((ListCriteria) cri).getProjid()).build();
		}
		return uriComponents.toUriString();
	}
	
	// /user/list 화면에서 사용할 url 생성
	public String makeUserSearch(int page) {
		UriComponents uriComponents;
		if (((ListCriteria) cri).getKeyword() == null) {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum()).build();
		} else {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("keyword", ((ListCriteria) cri).getKeyword()).build();
		}
		return uriComponents.toUriString();
	}
	
	// /app/appmain 화면에서 사용할 url 생성
		public String makeAppSearch(int page) {
			UriComponents uriComponents;
			if (((ListCriteria) cri).getKeyword() == null) {
				uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
						.queryParam("perPageNum", cri.getPerPageNum()).build();
			} else {
				uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
						.queryParam("perPageNum", cri.getPerPageNum())
						.queryParam("keyword", ((ListCriteria) cri).getKeyword()).build();
			}
			return uriComponents.toUriString();
		}
		
		// /device/devmain 화면에서 사용할 url 생성
		public String makeDeviceSearch(int page) {
			UriComponents uriComponents;
			if (((ListCriteria) cri).getKeyword() == null) {
				uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
						.queryParam("perPageNum", cri.getPerPageNum()).build();
			} else {
				uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
						.queryParam("perPageNum", cri.getPerPageNum())
						.queryParam("keyword", ((ListCriteria) cri).getKeyword()).build();
			}
			return uriComponents.toUriString();
		}


	// project/case/list 화면에서 사용할 url 생성
	public String makeCaseSearch(int page) {
		UriComponents uriComponents;
		if (((ListCriteria) cri).getKeyword() == null) {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("projid", ((ListCriteria) cri).getProjid())
					.queryParam("phid", ((ListCriteria) cri).getPhid()).build();
		} else {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("keyword", ((ListCriteria) cri).getKeyword())
					.queryParam("projid", ((ListCriteria) cri).getProjid())
					.queryParam("phid", ((ListCriteria) cri).getPhid())
					.queryParam("sort", ((ListCriteria) cri).getSort()).build();
		}
		return uriComponents.toUriString();
	}

	// project/result/list 화면에서 사용할 url 생성
	public String makeResultSearch(int page) {
		UriComponents uriComponents;
		if (((ListCriteria) cri).getKeyword() == null) {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("projid", ((ListCriteria) cri).getProjid())
					.queryParam("phid", ((ListCriteria) cri).getPhid())
					.queryParam("senid", ((ListCriteria) cri).getSenid()).build();
		} else {
			uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum())
					.queryParam("keyword", ((ListCriteria) cri).getKeyword())
					.queryParam("fromdate", ((ListCriteria) cri).getFromdate())
					.queryParam("todate", ((ListCriteria) cri).getTodate())
					.queryParam("result", ((ListCriteria) cri).getResult())
					.queryParam("sort", ((ListCriteria) cri).getSort())
					.queryParam("projid", ((ListCriteria) cri).getProjid())
					.queryParam("phid", ((ListCriteria) cri).getPhid())
					.queryParam("senid", ((ListCriteria) cri).getSenid())
					.build();
		}
		return uriComponents.toUriString();
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		calcData();
	}

	private void calcData() {

		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;

		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Criteria getCri() {
		return cri;
	}

}
