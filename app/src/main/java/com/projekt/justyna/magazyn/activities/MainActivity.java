package com.projekt.justyna.magazyn.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projekt.justyna.magazyn.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projekt.justyna.magazyn.R;
import com.projekt.justyna.magazyn.data.beams.Article;
import com.projekt.justyna.magazyn.data.stores.IStore;
import com.projekt.justyna.magazyn.data.stores.StoreFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button categories = (Button) findViewById(R.id.categories);
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CategoriesActivity.class));
            }
        });
        Button providers = (Button) findViewById(R.id.providers);
        providers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProvidersActivity.class));
            }
        });

        Button articles = (Button) findViewById(R.id.articles);
        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ArticlesActivity.class));
            }
        });

        Button note = (Button) findViewById(R.id.note);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NoteActivity.class));
            }
        });

        Button settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sp = getSharedPreferences(SettingsActivity.SETTINGS_PREFERENCES, 0);

        String backgroundColor = sp.getString(SettingsActivity.BACKGROUND_COLOR, SettingsActivity.DEFAULT_BACKGROUND_COLOR);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(backgroundColor));

        String buttonsColor = sp.getString(SettingsActivity.BUTTONS_COLOR, SettingsActivity.DEFAULT_BUTTONS_COLOR);
        findViewById(R.id.categories).setBackgroundColor(Color.parseColor(buttonsColor));
        findViewById(R.id.providers).setBackgroundColor(Color.parseColor(buttonsColor));
        findViewById(R.id.articles).setBackgroundColor(Color.parseColor(buttonsColor));
        findViewById(R.id.note).setBackgroundColor(Color.parseColor(buttonsColor));
        findViewById(R.id.settings).setBackgroundColor(Color.parseColor(buttonsColor));

        int id = sp.getInt(SettingsActivity.BEST_ARTICLE, 0);

        TextView bestArticle = (TextView) findViewById(R.id.best_article);

        if(id == 0) {
            bestArticle.setText(R.string.no_best_article);
        } else {
            IStore<Article> store = StoreFactory.createArticlesStore(StoreFactory.createCategoriesStore(), StoreFactory.createProvidersStore());
            Article article = store.getById(id);
            bestArticle.setText(article.getName());
        }
    }
}

