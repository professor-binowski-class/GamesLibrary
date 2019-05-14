package com.example.mockuppage;

public class ExampleItem {
    private int imageResource;
    private String text1;
    private String text2;
    private int ItemId;

    public ExampleItem(int imageResource, String text1, String text2, int id) {
        this.imageResource = imageResource;
        this.text1 = text1;
        this.text2 = text2;
        ItemId = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getmItemId() {
        return ItemId;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
