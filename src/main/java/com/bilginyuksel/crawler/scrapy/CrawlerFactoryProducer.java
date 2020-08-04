package com.bilginyuksel.crawler.scrapy;


import com.bilginyuksel.crawler.scrapy.utils.CrawlerType;

public final class CrawlerFactoryProducer {

  private CrawlerFactoryProducer(){
    throw new AssertionError();
  }

  public static CrawlerFactory getCrawlerFactory(CrawlerType crawlerType){


    if(crawlerType == CrawlerType.GITTIGIDIYOR)
      return new GittigidiyorCrawlerFactory();
    else if(crawlerType == CrawlerType.HEPSIBURADA)
      return new HepsiburadaCrawlerFactory();
    else if(crawlerType == CrawlerType.SAHIBINDEN)
      return new SahibindenCrawlerFactory();

    return null;
  }
}
