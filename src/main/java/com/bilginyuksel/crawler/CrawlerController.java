package com.bilginyuksel.crawler;

import com.bilginyuksel.crawler.scrapy.CrawlerFactory;
import com.bilginyuksel.crawler.scrapy.CrawlerFactoryProducer;
import com.bilginyuksel.crawler.scrapy.model.Item;
import com.bilginyuksel.crawler.scrapy.model.ProductType;
import com.bilginyuksel.crawler.scrapy.utils.CrawlerFactoryConfiguration;
import com.bilginyuksel.crawler.scrapy.utils.CrawlerType;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CrawlerController {

    CrawlerFactoryConfiguration factoryConfiguration = new CrawlerFactoryConfiguration();
    CrawlerFactory hepsiburada = CrawlerFactoryProducer.getCrawlerFactory(CrawlerType.HEPSIBURADA);
    CrawlerFactory gittigidiyor = CrawlerFactoryProducer.getCrawlerFactory(CrawlerType.GITTIGIDIYOR);

    @GetMapping("/")
    public Pair<Item, Item> getItem(
            @RequestParam String item){

        hepsiburada.setFactoryConfiguration(factoryConfiguration);
        gittigidiyor.setFactoryConfiguration(factoryConfiguration);

        factoryConfiguration.setSearchedItem(item);
        Item hp = (Item)hepsiburada.getProduct(ProductType.MOBILE_PHONE);
        Item gg = (Item)gittigidiyor.getProduct(ProductType.MOBILE_PHONE);

        return new Pair<>(hp, gg);
    }
}
