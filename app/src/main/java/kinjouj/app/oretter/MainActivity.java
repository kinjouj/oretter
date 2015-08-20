package kinjouj.app.oretter;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.Menu;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

import kinjouj.app.oretter.fragment.HomeStatusListFragment;
import kinjouj.app.oretter.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity
    implements TabLayout.OnTabSelectedListener, SearchView.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getName();

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.appbar_layout)
    AppBarLayout appBarLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @BindString(R.string.nav_menu_home)
    String navHomeTitle;

    @BindString(R.string.nav_menu_mention)
    String navMentionTitle;

    @BindString(R.string.nav_menu_favorite)
    String navFavoriteTitle;

    @BindString(R.string.nav_menu_follow)
    String navFollowTitle;

    @BindString(R.string.nav_menu_follower)
    String navFollowerTitle;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initTabLayout();
        setContentFragment(new HomeStatusListFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        searchView = (SearchView)MenuItemCompat.getActionView(menu.findItem(R.id.tb_menu_search));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            Log.v(TAG, "onBackPressed: closeDrawer");
            closeDrawer();
        } else {
            if (searchView != null && !searchView.isIconified()) {
                Log.v(TAG, "onBackPressed: SearchView.onActionViewCollapsed");
                collapseSearchView();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onSearchRequested() {
        if (searchView != null && searchView.isIconified()) {
            Log.v(TAG, "onSearchRequested: onActionViewExpanded");
            searchView.onActionViewExpanded();
        }

        return false;
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public boolean onQueryTextChange(String newString) {
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        collapseSearchView();
        TabLayout.Tab tab = tabLayout.newTab().setText("検索: " + query).setIcon(R.drawable.ic_search);
        tabLayout.addTab(tab, true);
        //tab.select();
        setContentFragment(SearchFragment.newInstance(query));

        return false;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setOnMenuItemClickListener(new ToolbarOnItemClickListener(this));

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.drawable.ic_drawer,
            R.drawable.ic_drawer
        );
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
    }

    private void initTabLayout() {
        tabLayout.setOnTabSelectedListener(this);

        tabLayout.addTab(
            tabLayout.newTab()
                    .setText(navHomeTitle)
                    .setIcon(R.drawable.ic_home)
        );

        tabLayout.addTab(
            tabLayout.newTab()
                    .setText(navMentionTitle)
                    .setIcon(R.drawable.ic_reply)
        );

        tabLayout.addTab(
            tabLayout.newTab()
                    .setText(navFavoriteTitle)
                    .setIcon(R.drawable.ic_grade)
        );

        tabLayout.addTab(
            tabLayout.newTab()
                    .setText(navFollowTitle)
                    .setIcon(R.drawable.ic_follow)
        );

        tabLayout.addTab(
            tabLayout.newTab()
                    .setText(navFollowerTitle)
                    .setIcon(R.drawable.ic_follower)
        );
    }

    private void collapseSearchView() {
        if (searchView != null && !searchView.isIconified()) {
            searchView.clearFocus();
            searchView.onActionViewCollapsed();
        }
    }

    public void setContentFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content, fragment);
        tx.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tx.commit();
    }

    public void addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener) {
        appBarLayout.addOnOffsetChangedListener(listener);
    }

    public void removeOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener) {
        appBarLayout.removeOnOffsetChangedListener(listener);
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
