package com.vuquynhnhu.models;

import java.util.ArrayList;
import java.util.Random;

public class ListCategory {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories=new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public void addCategories(Category c)
    {
        categories.add(c);
    }
    public void generate_sample_dataset(){
        for (int i=1; i<=5;i++) // GS có 5 danh mục
        {
            int id=i;
            String name="Category"+i;
            Category c=new Category(id,name);
            addCategories(c);
        }
    }
}
