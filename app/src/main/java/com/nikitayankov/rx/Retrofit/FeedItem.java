package com.nikitayankov.rx.Retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class FeedItem {
    @Element(name = "title")
    private String mTitleString;

    @Element(name = "link")
    private String mLinkString;

    @Element(name = "description")
    private String mDescriptionString;

    @Element(name = "author")
    private String mAuthorString;

    @Element(name = "pubDate")
    private String mDateString;

    @Element(name = "category")
    private String mCategoryString;

    public FeedItem(){

    }

    public FeedItem(String titleString, String linkString, String descriptionString, String authorString, String dateString, String categoryString) {
        mTitleString = titleString;
        mLinkString = linkString;
        mDescriptionString = descriptionString;
        mAuthorString = authorString;
        mDateString = dateString;
        mCategoryString = categoryString;
    }

    public String getTitleString() {
        return mTitleString;
    }

    public void setTitleString(String titleString) {
        mTitleString = titleString;
    }

    public String getLinkString() {
        return mLinkString;
    }

    public void setLinkString(String linkString) {
        mLinkString = linkString;
    }

    public String getDescriptionString() {
        return mDescriptionString;
    }

    public void setDescriptionString(String descriptionString) {
        mDescriptionString = descriptionString;
    }

    public String getAuthorString() {
        return mAuthorString;
    }

    public void setAuthorString(String authorString) {
        mAuthorString = authorString;
    }

    public String getDateString() {
        return mDateString;
    }

    public void setDateString(String dateString) {
        mDateString = dateString;
    }

    public String getCategoryString() {
        return mCategoryString;
    }

    public void setCategoryString(String categoryString) {
        mCategoryString = categoryString;
    }
}
