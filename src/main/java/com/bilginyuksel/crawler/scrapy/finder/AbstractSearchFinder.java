package com.bilginyuksel.crawler.scrapy.finder;

import java.util.List;

public abstract class AbstractSearchFinder {
    private static AbstractSearchFinder instanceHB = null;
    private static AbstractSearchFinder instanceGG = null;

    public static <T extends AbstractSearchFinder> AbstractSearchFinder getInstance(Class<T> clazz){
        if(clazz.getSimpleName().equals(HepsiburadaSearchFinder.class.getSimpleName())){
            if (instanceHB == null) instanceHB = new HepsiburadaSearchFinder();
            return instanceHB;
        } else if(clazz.getSimpleName().equals(GittigidiyorSearchFinder.class.getSimpleName())){
            if(instanceGG == null) instanceGG = new GittigidiyorSearchFinder();
            return instanceGG;
        }

        return null;
    }

    public abstract List<String> links(String url, int limit);
}
