package com.projekt.justyna.magazyn.receiver;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.RemoteViews;
        import android.widget.RemoteViewsService;

        import java.util.List;

        import com.projekt.justyna.magazyn.R;
        import com.projekt.justyna.magazyn.data.beams.Article;
        import com.projekt.justyna.magazyn.data.beams.Category;
        import com.projekt.justyna.magazyn.data.stores.IStore;
        import com.projekt.justyna.magazyn.data.stores.StoreFactory;


public class SimpleViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<Article> list;

    private Context context;

    public SimpleViewsFactory(Context context) {
        this.context = context;

        IStore<Article> store = StoreFactory.createArticlesStore(context, StoreFactory.createCategoriesStore(context), StoreFactory.createProvidersStore(context));

        list = store.get();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int index) {
        RemoteViews row = new RemoteViews(context.getPackageName(), R.layout.simple_row);

        row.setTextViewText(R.id.textView, list.get(index).getName());

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

