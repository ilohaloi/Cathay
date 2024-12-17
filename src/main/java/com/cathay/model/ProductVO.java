package com.cathay.model;

public class ProductVO {
	private int product_id;
	private String product_name;
	private String product_short_name;
	private Boolean product_group;

	public int getProdId() {
		return product_id;
	}

	public void setProdId(int product_id) {
		this.product_id = product_id;
	}

	public String getProdName() {
		return product_name;
	}

	public void setProdName(String product_name) {
		this.product_name = product_name;
	}

	public String getProdShortName() {
		return product_short_name;
	}

	public void setProdShortName(String product_short_name) {
		this.product_short_name = product_short_name;
	}

	public Boolean getProdGroup() {
		return product_group;
	}

	public void setProdGroup(Boolean product_group) {
		this.product_group = product_group;
	}
}
