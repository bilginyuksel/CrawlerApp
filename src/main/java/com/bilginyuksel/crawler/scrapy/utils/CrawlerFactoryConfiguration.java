package com.bilginyuksel.crawler.scrapy.utils;

public class CrawlerFactoryConfiguration {
    private int itemLimit;
    private int commentLimit;
    private int colorLimit;
    private int propLimit;
    private String searchedItem;

    public CrawlerFactoryConfiguration(){
        itemLimit = 1;
        commentLimit = 1;
        colorLimit = 1;
        propLimit = 1;
        this.searchedItem = "";
    }

    public void setSearchedItem(String searchedItem) {
        // It changes string for http triggering
        // Huawei p30 lite -> Huawei+p30+lite
        this.searchedItem = searchedItem.replace(' ', '+'); // Change it the way they use
    }

    public void setColorLimit(int colorLimit) {
        this.colorLimit = colorLimit;
    }

    public void setCommentLimit(int commentLimit) {
        this.commentLimit = commentLimit;
    }

    public void setItemLimit(int itemLimit) {
        this.itemLimit = itemLimit;
    }

    public void setPropLimit(int propLimit) {
        this.propLimit = propLimit;
    }

    public int getColorLimit() {
        return colorLimit;
    }

    public String getSearchedItem() {
        return searchedItem;
    }

    public int getCommentLimit() {
        return commentLimit;
    }

    public int getItemLimit() {
        return itemLimit;
    }

    public int getPropLimit() {
        return propLimit;
    }
}
