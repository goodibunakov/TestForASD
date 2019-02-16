package ru.goodibunakov.testforasd.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Item {
    @Element(name = "enclosure", required = false)
    private Enclosure enclosure;

    @Element(name = "link", required = false)
    private String link;

    @Element(name = "guid", required = false)
    private String guid;

    @Element(name = "description", required = false)
    private String description;

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "category", required = false)
    private String category;

    @Element(name = "pubDate", required = false)
    private String pubDate;

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "ClassPojo [enclosure = " + enclosure + ", link = " + link + ", guid = " + guid + ", description = " + description + ", title = " + title + ", category = " + category + ", pubDate = " + pubDate + "]";
    }
}
