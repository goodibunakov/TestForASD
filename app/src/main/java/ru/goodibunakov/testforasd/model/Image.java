package ru.goodibunakov.testforasd.model;

public class Image {
    private String link;

    private String width;

    private String title;

    private String url;

    private String height;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ClassPojo [link = " + link + ", width = " + width + ", title = " + title + ", url = " + url + ", height = " + height + "]";
    }
}
