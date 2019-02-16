package ru.goodibunakov.testforasd.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "channel", strict = false)
public class Channel {

//    @Element(name = "image")
//    private Image image;

    @ElementList(name = "item", inline = true)
    private List<Item> items = new ArrayList<>();

//    @Element(name = "link")
//    private String link;
//
//    @Element(name = "description")
//    private String description;
//
//    @Element(name = "language")
//    private String language;
//
//    @Element(name = "title")
//    private String title;

//    public Image getImage() {
//        return image;
//    }

//    public void setImage(Image image) {
//        this.image = image;
//    }

    public List<Item> getItems() {
        return items;
    }

//    public void setItem(Item[] item) {
//        this.item = item;
//    }

//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    @Override
//    public String toString() {
//        return "ClassPojo [image = " + image + ", items = " + items + ", link = " + link + ", description = " + description + ", language = " + language + ", title = " + title + "]";
//    }
}
