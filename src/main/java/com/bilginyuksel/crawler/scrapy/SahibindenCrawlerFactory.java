package com.bilginyuksel.crawler.scrapy;


import com.bilginyuksel.crawler.scrapy.model.Product;
import com.bilginyuksel.crawler.scrapy.model.ProductType;

import java.util.List;

class SahibindenCrawlerFactory extends CrawlerFactory {

  private final String searchUrl = "https://www.sahibinden.com/";
  private final String searchExtension = "kelime-ile-arama?query_text=";
  private final String testHREF = "/ilan/ikinci-el-ve-sifir-alisveris-bilgisayar-dizustu-notebook-asus-tuf-fx505-gtx-1650-ryzen-7-831510580/detay";

  @Override
  public Product getProduct(ProductType productType) {

    return null;
  }

  public List<Product> getProductList(ProductType productType) {
    if(factoryConfiguration.getSearchedItem() == null)
      return null;


    try {
      String url = new StringBuilder()
              .append(searchUrl)
              .append(searchExtension)
              .append(factoryConfiguration.getSearchedItem())
              .toString();
      System.out.println("["+ this.getClass().getSimpleName() +"] -> URL:" + url);


    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
