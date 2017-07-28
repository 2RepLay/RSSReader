package com.nikitayankov.rx.Retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;

@Root(name = "rss", strict = false)
public class Channel implements Serializable {
    @ElementList(inline = true, name="item")
    private ArrayList<FeedItem> mFeedItems;

    public Channel() {

    }

    public Channel(ArrayList<FeedItem> mFeedItems) {
        this.mFeedItems = mFeedItems;
    }

    public ArrayList<FeedItem> getFeedItems() {
        return mFeedItems;
    }
}
