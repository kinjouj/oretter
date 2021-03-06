package kinjouj.app.oretter.view.manager;

import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import kinjouj.app.oretter.MainActivity;
import kinjouj.app.oretter.R;

public class DrawerLayoutManager extends ViewManager<DrawerLayout> {

    public DrawerLayoutManager(View view, Toolbar toolbar) {
        super(view);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
            (MainActivity) getContext(),
            getView(),
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        );
        getView().setDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
    }

    public boolean isOpen() {
        return getView().isDrawerOpen(GravityCompat.START);
    }

    public void close() {
        getView().closeDrawer(GravityCompat.START);
    }

    @Override
    public void unbind() {
    }
}
