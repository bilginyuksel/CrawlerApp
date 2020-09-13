package com.bilginyuksel.crawler.scrapy;

import com.bilginyuksel.crawler.scrapy.model.Product;
import com.bilginyuksel.crawler.scrapy.model.ProductType;
import com.bilginyuksel.crawler.scrapy.utils.CrawlerFactoryConfiguration;

import java.util.List;

public abstract class CrawlerFactory {

  protected CrawlerFactoryConfiguration factoryConfiguration;

  public abstract Product getProduct(ProductType productType);
  public abstract List getProductList(ProductType productType);

  public void setFactoryConfiguration(CrawlerFactoryConfiguration factoryConfiguration) {
    this.factoryConfiguration = factoryConfiguration;
  }
}
