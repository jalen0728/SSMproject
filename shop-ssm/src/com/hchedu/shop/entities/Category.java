package com.hchedu.shop.entities;

import java.util.List;

public class Category {
    private Integer cid;

    private String cname;
    
    private List<CategorySecond> categorySeconds;
    
    public List<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(List<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

	
    
}