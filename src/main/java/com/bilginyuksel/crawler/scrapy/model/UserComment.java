package com.bilginyuksel.crawler.scrapy.model;

import java.io.Serializable;
import java.util.Date;

public class UserComment implements Serializable {
  protected String title, description;
  private String author;
  private Date publishedDate;
  protected String formattedDate;
  private boolean isBought;
  protected String isBoughtText;
  protected int rating;
  private String productName;
  private String productCategory;
  private String url;


  private UserComment(){ }

  public static class Builder{
    private UserComment c;

    public Builder(String title, String description){
      c = new UserComment();
      c.title = title;
      c.description = description;
    }

    public Builder publishedDate(Date date, String formatted){
      c.formattedDate = formatted;
      c.publishedDate = date;
      return this;
    }

    public Builder Isbought(String boughtText){
      c.isBoughtText = boughtText;
      //c.isBought = false;
      return this;
    }

    public Builder rating(int rating){
      c.rating = rating;
      return this;
    }

    public Builder author(String author){
      c.author = author;
      return this;
    }

    public Builder productName(String product){
      c.productName = product;
      return this;
    }

    public Builder productCategory(String category){
      c.productCategory = category;
      return this;
    }

    public UserComment build(){
      return c;
    }
  }

  @Override
  public String toString() {
    return "UserComment{" +
            "\ntitle='" + title + '\'' +
            "\n, description='" + description + '\'' +
            "\n, author='" + author + '\'' +
            "\n, publishedDate=" + publishedDate +
            "\n, formattedDate='" + formattedDate + '\'' +
            "\n, isBought=" + isBought +
            "\n, isBoughtText='" + isBoughtText + '\'' +
            "\n, rating=" + rating +
            "\n, productName='" + productName + '\'' +
            "\n, productCategory='" + productCategory + '\'' +
            "\n, url='" + url + '\'' +
            '}';
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getAuthor() {
    return author;
  }

  public Date getPublishedDate() {
    return publishedDate;
  }

  public String getFormattedDate() {
    return formattedDate;
  }

  public boolean isBought() {
    return isBought;
  }

  public String getIsBoughtText() {
    return isBoughtText;
  }

  public int getRating() {
    return rating;
  }

  public String getProductName() {
    return productName;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public String getUrl() {
    return url;
  }
}
