package com.bilginyuksel.crawler.scrapy.model;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class Product implements Serializable {

  private Map<String, Object> properties;
  private List<UserComment> commentList;

  public Product(Map<String, Object> properties){
    // Code goes here if you want to run for all extended classes
    this.properties = properties;
  }

  public void setCommentList(List<UserComment> commentList) {
    this.commentList = commentList;
  }

  public List<UserComment> getCommentList() {
    return commentList;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  @Override
  public String toString() {
    return "Product{" +
            ", properties=" + properties +
            ", commentList=" + commentList +
            '}';
  }
}
