package com.projekt.justyna.magazyn.views.adapters;

        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;

        import java.util.List;

        import com.projekt.justyna.magazyn.activities.UpdatableActivity;
        import com.projekt.justyna.magazyn.data.beams.Article;
        import com.projekt.justyna.magazyn.data.beams.Category;
        import com.projekt.justyna.magazyn.views.ArticleItem;
        import com.projekt.justyna.magazyn.views.CategoryItem;

public class ArticlesAdapter extends ArrayAdapter<Article> {
    private UpdatableActivity activity;

    public ArticlesAdapter(Context context, UpdatableActivity activity) {
        super(context, 0);
        this.activity = activity;
    }

    public void update(List<Article> articles) {
        clear();

        addAll(articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);

        return new ArticleItem(getContext(), article, this.activity);
    }
}

