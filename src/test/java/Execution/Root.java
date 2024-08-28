package Execution;

import java.util.ArrayList;

public class Root {
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private ArrayList<Datum> data;
	private Support support;
	public int getPage() {
		return page;
	}
	public int getPer_page() {
		return per_page;
	}
	public int getTotal() {
		return total;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public ArrayList<Datum> getData() {
		return data;
	}
	public Support getSupport() {
		return support;
	}
    

}
