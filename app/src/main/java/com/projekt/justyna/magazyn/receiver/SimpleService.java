package com.projekt.justyna.magazyn.receiver;

        import android.content.Intent;
        import android.widget.RemoteViewsService;


public class SimpleService extends RemoteViewsService {

    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new SimpleViewsFactory(this.getApplicationContext());

    }

}
