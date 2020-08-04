package com.bilginyuksel.crawler.scrapy;

import com.bilginyuksel.crawler.scrapy.finder.AbstractSearchFinder;
import com.bilginyuksel.crawler.scrapy.finder.GittigidiyorSearchFinder;
import com.bilginyuksel.crawler.scrapy.model.Item;
import com.bilginyuksel.crawler.scrapy.model.Product;
import com.bilginyuksel.crawler.scrapy.model.ProductType;
import com.bilginyuksel.crawler.scrapy.model.UserComment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class GittigidiyorCrawlerFactory extends CrawlerFactory {

    private final String searchUrl = "https://www.gittigidiyor.com/";
    private final String searchExtension = "arama/?k=";
    private final String testComments = "https://www.gittigidiyor.com/huawei-p30-p30-lite/yorumlari";

    private AbstractSearchFinder finder = AbstractSearchFinder.getInstance(GittigidiyorSearchFinder.class);

    public Product getProduct(ProductType productType) {
        if(factoryConfiguration.getSearchedItem() == null)
            return null;


        Item item = null;
        try {
            String url = new StringBuilder()
                    .append(searchUrl)
                    .append(searchExtension)
                    .append(factoryConfiguration.getSearchedItem())
                    .toString();
            System.out.println("["+ this.getClass().getSimpleName() +"] -> URL:" + url);

            List<String> links = finder.links(url, 1);

            String fixedUrl = links.get(0).split("_")[0] + "/yorumlari";
            System.out.println("Fixed URL: " + fixedUrl);
            Document doc = Jsoup.connect(fixedUrl).get();

            Elements commentComponentList = doc.getElementsByClass("user-catalog-review clearfix");

            List<UserComment> commentList = new ArrayList<UserComment>();
            for(Element elem: commentComponentList){
                String pubDate = elem.getElementsByClass("user-catalog-review-date").get(0).text(); // dd/MM/yyyy
                String nickname = elem.getElementsByClass("user-nick-name").get(0).text();
                String title = elem.getElementsByClass("user-catalog-review-header clearfix").tagName("h3").text();
                double rating = Double.parseDouble(elem.getElementsByClass("catalog-review-point").text());
                String description = elem.getElementsByClass("user-catalog-review-comment-detail gg-d-23 gg-m-22 pl0")
                        .tagName("p").text();
                Elements isConfirmed = elem.getElementsByClass("confirmedSelling");
                String confirmedText = "Onaylanmamış alışveriş.";
                if(isConfirmed.size()!=0) {
                    confirmedText = isConfirmed.tagName("p").text();
                }

                UserComment userComment = new UserComment.Builder(title, description)
                        .publishedDate(new SimpleDateFormat("dd/MM/yyyy").parse(pubDate), pubDate)
                        .rating((int)rating) // fix it to double
                        .author(nickname)
                        .Isbought(confirmedText)
                        .productCategory(productType.toString())
                        .build();

                commentList.add(userComment);
            }

            // Empty properties comments are the most important part here.
            item = new Item(new HashMap<String, Object>());
            item.setCommentList(commentList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }

    public List<Product> getProductList(ProductType productType) {

        return null;
    }
}
