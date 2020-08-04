package com.bilginyuksel.crawler.scrapy.finder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HepsiburadaSearchFinder extends AbstractSearchFinder {

    HepsiburadaSearchFinder(){}
    public List<String> links(String url, int limit) {
        List<String> linkList = new ArrayList<String>();
        // class="_3yPhD", attr=href
        System.out.println("Hepsi Burada Search Finder...");
        try{
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("a");
            System.out.println("Hepsiburada found elements size: "+ elements.size());
            int count = 0;
            for(int i=0;i<elements.size(); ++i){
                //System.out.println("Element -> " + elements.get(i).text());
                if(elements.get(i).hasAttr("data-productid")){
                    String hrefLink = elements.get(i).attr("href");
                    System.out.println("Link ->" +hrefLink);
                    linkList.add(hrefLink);
                    if(++count == limit) break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return linkList;
    }
}
