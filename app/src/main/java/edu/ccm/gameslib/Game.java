package edu.ccm.gameslib;

import android.net.Uri;

import java.security.SecureRandom;
import java.util.Date;

public class Game {

    private String mItem;

    private Uri mCoverArtLink;

    private String mPlatform;
    private Boolean mDonated;
    private String[] mGenre;
    private String[] mDeveloper;
    private String mPublisher;

    private Date mReleseDate;

    private Uri mPlaystoreLink;
    private Uri mIosLink;
    private Uri mItchIoLink;

    private String mDescription;


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String item) {
        mItem = item;
    }

    public Uri getCoverArtLink() {
        return mCoverArtLink;
    }

    public void setCoverArtLink(Uri coverArtLink) {
        mCoverArtLink = coverArtLink;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String platform) {
        mPlatform = platform;
    }

    public Boolean getDonated() {
        return mDonated;
    }

    public void setDonated(Boolean donated) {
        mDonated = donated;
    }

    public String[] getGenre() {
        return mGenre;
    }

    public void setGenre(String[] genre) {
        mGenre = genre;
    }

    public String[] getDeveloper() {
        return mDeveloper;
    }

    public void setDeveloper(String[] developer) {
        mDeveloper = developer;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public Date getReleseDate() {
        return mReleseDate;
    }

    public void setReleseDate(Date releseDate) {
        mReleseDate = releseDate;
    }

    public Uri getPlaystoreLink() {
        return mPlaystoreLink;
    }

    public void setPlaystoreLink(Uri playstoreLink) {
        mPlaystoreLink = playstoreLink;
    }

    public Uri getIosLink() {
        return mIosLink;
    }

    public void setIosLink(Uri iosLink) {
        mIosLink = iosLink;
    }

    public Uri getItchIoLink() {
        return mItchIoLink;
    }

    public void setItchIoLink(Uri itchIoLink) {
        mItchIoLink = itchIoLink;
    }
}
