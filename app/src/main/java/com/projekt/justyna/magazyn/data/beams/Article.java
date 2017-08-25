package com.projekt.justyna.magazyn.data.beams;

import java.util.List;

/**
 * Created by Justyna on 25.08.2017.
 */

public class Article extends Beam{
    private float price;
    private Category category;
    private Provider provider;

    public Article(int id, String name, float price, Category category, Provider provider) {
        super(id, name);
        this.price = price;
        this.category = category;
        this.provider = provider;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public static int getPositionById(int id, List<Article> list) {
        int position = -1;

        for(int i = 0;i < list.size();++i) {
            Article article = list.get(i);
            if(article.getId() == id) {
                position = i;
                break;
            }
        }

        return position;
    }
}
