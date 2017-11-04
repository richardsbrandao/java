package com.richard.mongo.domain;

public class BookDetails {
	
	private String title;
	private Integer pubYear;
	private Integer seriesNumber;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPubYear() {
		return pubYear;
	}
	public void setPubYear(Integer pubYear) {
		this.pubYear = pubYear;
	}
	public Integer getSeriesNumber() {
		return seriesNumber;
	}
	public void setSeriesNumber(Integer seriesNumber) {
		this.seriesNumber = seriesNumber;
	}
	@Override
	public String toString() {
		return "BookDetails [title=" + title + ", pubYear=" + pubYear
				+ ", seriesNumber=" + seriesNumber + "]";
	}
}
