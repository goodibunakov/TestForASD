package ru.goodibunakov.testforasd.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
@Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom")
public class Rss {

    @Element(name = "channel")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "ClassPojo [channel = " + channel;
    }
}
