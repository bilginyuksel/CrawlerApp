package com.bilginyuksel.crawler.scrapy;



import com.bilginyuksel.crawler.scrapy.model.Product;
import com.bilginyuksel.crawler.scrapy.model.ProductType;

import java.util.List;

public class VatanComputerCrawlerFactory extends CrawlerFactory {

  private String url = "vatan";
  private String searchExtension = "q=";


  public Product getProduct(ProductType productType) {
    return null;
  }

  public List getProductList(ProductType productType) {
    return null;
  }
}
