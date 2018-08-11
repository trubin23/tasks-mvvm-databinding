package ru.trubin23.tasks_mvvm_databinding.statistics;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.trubin23.tasks_mvvm_databinding.R;

public class StatisticsActivity extends AppCompatActivity {

    private static final String STATS_VIEWMODEL_TAG = "STATS_VIEWMODEL_TAG";

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_act);

        setupToolbar();

        setupNavigationDrawer();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.statistics);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView == null) {
            return;
        }
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.list_nav_menu_item:
                    NavUtils.navigateUpFromSameTask(StatisticsActivity.this);
                    break;
                case R.id.statistics_nav_menu_item:
                    break;
            }

            item.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
