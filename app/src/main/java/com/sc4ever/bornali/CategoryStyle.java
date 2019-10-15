package com.sc4ever.bornali;

public class CategoryStyle {
    private int _id;
    private String cats;

    public CategoryStyle(int _id, String cats) {
        this._id = _id;
        this.cats = cats;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCats() {
        return cats;
    }

    public void setCats(String cats) {
        this.cats = cats;
    }
}
