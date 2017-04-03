package net.yuanmomo.springboot.util;

import java.util.List;

public class PaginationBean {
	private long pageNum;
	
	private long numPerPage;
	
	private long totalCount;
	
	private long totalPages;
	
	private List<?> result;

	/**
	 * totalPages.
	 *
	 * @return  the totalPages
	 * @since   JDK 1.6
	 */
	public long getTotalPages() {
		return totalPages;
	}

	/**
	 * totalPages.
	 *
	 * @param   totalPages    the totalPages to set
	 * @since   JDK 1.6
	 */
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * totalCount.
	 *
	 * @return  the totalCount
	 * @since   JDK 1.6
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * totalCount.
	 *
	 * @param   totalCount    the totalCount to set
	 * @since   JDK 1.6
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * pageNum.
	 *
	 * @return  the pageNum
	 * @since   JDK 1.6
	 */
	public long getPageNum() {
		return pageNum;
	}

	/**
	 * pageNum.
	 *
	 * @param   pageNum    the pageNum to set
	 * @since   JDK 1.6
	 */
	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * numPerPage.
	 *
	 * @return  the numPerPage
	 * @since   JDK 1.6
	 */
	public long getNumPerPage() {
		return numPerPage;
	}

	/**
	 * numPerPage.
	 *
	 * @param   numPerPage    the numPerPage to set
	 * @since   JDK 1.6
	 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	/**
	 * result.
	 *
	 * @return  the result
	 * @since   JDK 1.6
	 */
	public List<?> getResult() {
		return result;
	}

	/**
	 * result.
	 *
	 * @param   result    the result to set
	 * @since   JDK 1.6
	 */
	public void setResult(List<?> result) {
		this.result = result;
	}
}
