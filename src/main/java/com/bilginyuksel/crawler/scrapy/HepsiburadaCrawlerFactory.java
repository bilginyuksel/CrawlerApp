package com.bilginyuksel.crawler.scrapy;

import com.bilginyuksel.crawler.scrapy.finder.AbstractSearchFinder;
import com.bilginyuksel.crawler.scrapy.finder.HepsiburadaSearchFinder;
import com.bilginyuksel.crawler.scrapy.model.Item;
import com.bilginyuksel.crawler.scrapy.model.Product;
import com.bilginyuksel.crawler.scrapy.model.ProductType;
import com.bilginyuksel.crawler.scrapy.model.UserComment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.text.SimpleDateFormat;
import java.util.*;

class HepsiburadaCrawlerFactory extends CrawlerFactory {

    private final String searchUrl = "https://www.hepsiburada.com";
    private final String searchExtension = "ara?q=";

    private AbstractSearchFinder finder = AbstractSearchFinder.getInstance(HepsiburadaSearchFinder.class);
    private final String testComments = "https://www.hepsiburada.com/huawei-p30-lite-128-gb-huawei-turkiye-garantili-p-HBV00000KJE2C";


    public Product getProduct(ProductType productType) {
        if(factoryConfiguration.getSearchedItem() == null)
            return null;

        Item item = null;
        try {
            String url = new StringBuilder()
                    .append(searchUrl)
                    .append("/")
                    .append(searchExtension)
                    .append(factoryConfiguration.getSearchedItem())
                    .toString();
            System.out.println("[" + this.getClass().getSimpleName() + "] -> URL:" + url);

            // Check here!
            List<String> links = finder.links(url, 1);
            Document doc = Jsoup.connect(searchUrl + links.get(0)).get();

            // ReviewCard-module-34AJ_
            // ReviewCard-module-3fj8Y or itemprop="datePublished"
            // ReviewCard-module-2dVP9, itemprop=name, itemprop=description
            // itemprop="reviewRating", itemprop="ratingValue"
            // ReviewCard-module-23NgY, buyer or looker.
            // class="RatingPointer-module-1OKF3" // xmlns="http://www.w3.org/2000/svg" // fill=#f28b00, #ccc-> YES, NO
            // Load comments here.
            List<UserComment> userCommentList = new ArrayList<UserComment>();
            Elements commentElements = doc.getElementsByClass("ReviewCard-module-34AJ_");
            for(Element elem: commentElements){
                String publishedDate = elem.getElementsByAttributeValue("itemprop", "datePublished").text();
                String pubDate = elem.getElementsByAttributeValue("itemprop", "datePublished").attr("content");
                Date pDate = new SimpleDateFormat("yyyy-MM-dd").parse(pubDate);
                String title = elem.getElementsByAttributeValue("itemprop", "name").text();
                String description = elem.getElementsByAttributeValue("itemprop", "description").text();
                String isBoughtText = elem.getElementsByClass("ReviewCard-module-23NgY").text();
                Elements goldStars = elem.getElementsByAttributeValue("fill","#f28b00");

                //Elements nonGoldStars = elem.getElementsByAttributeValue("fill","#ccc");
                int rate = goldStars.size();

                UserComment comment = new UserComment.Builder(title, description)
                        .Isbought(isBoughtText)
                        .publishedDate(pDate, publishedDate)
                        .rating(rate)
                        .build();

                userCommentList.add(comment);
            }

            Elements elements = doc.select("#productTechSpecContainer > table:nth-child(2)");
            Element e = elements.get(0);

            Map<String, Object> properties = new HashMap<String, Object>();
            Elements props = e.getElementsByTag("th");
            Elements value = e.getElementsByTag("td");
            for(int i=0;i<props.size();++i){
                properties.put(props.get(i).text(), value.get(i).text());
            }
            item = new Item(properties);
            item.setCommentList(userCommentList);

        }catch (Exception e){
            e.printStackTrace();
        }

        return item;
    }

    public List<Product> getProductList(ProductType productType) {
        return null;
    }
}
