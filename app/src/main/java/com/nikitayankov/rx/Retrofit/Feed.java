package com.nikitayankov.rx.Retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(strict = false)
@NamespaceList({
        @Namespace(reference = "http://purl.org/dc/elements/1.1/", prefix = "dc"),
        @Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom"),
        @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "media"),
        @Namespace(reference = "http://purl.org/rss/1.0/modules/content/", prefix = "content"),
        @Namespace(reference = "http://www.georss.org/georss", prefix = "georss")
})
public class Feed implements Serializable {
    @Element(name = "channel")
    private Channel mChannel;

    public Feed(){}

    public Feed(Channel channel) {
        this.mChannel = channel;
    }

    public Channel getChannel() {
        return  mChannel;
    }
}
