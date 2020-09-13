package com.bilginyuksel.crawler.scrapy.finder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GittigidiyorSearchFinder extends AbstractSearchFinder {

    GittigidiyorSearchFinder(){}
    public List<String> links(String url, int limit) {
        List<String> linkList = new ArrayList<String>();
        // class = 'gg-w-4 gg-d-7 gg-t-6 gg-m-8 padding-none list-product-image'
        // tag=a, attr='href'
        System.out.println("Gittigidiyor finder...");
        try{
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select( "li[product-id]");
            System.out.println("Elements found: "+elements.size());
            for(int i=0;i<Math.min(limit, elements.size()); ++i){
                String hrefLink = elements.get(i).select("a[href]").attr("href");
                linkList.add(hrefLink);
                System.out.println("Link:" + hrefLink);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return linkList;
    }
}
