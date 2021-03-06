package kinjouj.app.oretter.view.manager;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.view.View;

import kinjouj.app.oretter.AppInterfaces;
import kinjouj.app.oretter.EventManager;
import kinjouj.app.oretter.MainActivity;
import kinjouj.app.oretter.R;
import kinjouj.app.oretter.fragments.list.status.SearchFragment;

public class SearchViewManager extends ViewManager<SearchView> {

    SearchView.OnQueryTextListener listener;

    public SearchViewManager(View view) {
        super(view);
        registerListener();
    }

    @Override
    public void unbind() {
        getView().setOnQueryTextListener(null);
        listener = null;
        destroyView();
    }

    public void search(final String query) {
        EventManager.getInstance().post(new AppInterfaces.AppEvent() {
            @Override
            public void run(Context context) {
                TabLayoutManager tm = ((MainActivity) context).getTabLayoutManager();
                tm.select(
                    tm.addTab("検索: " + query, R.drawable.ic_search, SearchFragment.build(query)),
                    300
                );
            }
        });
    }

    public boolean isIconified() {
        return getView().isIconified();
    }

    public void expand() {
        if (isIconified()) {
            getView().onActionViewExpanded();
        }
    }

    public void collapse() {
        if (!isIconified()) {
            getView().clearFocus();
            getView().onActionViewCollapsed();
        }
    }

    private class OnQueryTextListener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            collapse();
            search(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }

    void registerListener() {
        listener = new OnQueryTextListener();
        getView().setOnQueryTextListener(listener);
    }
}
