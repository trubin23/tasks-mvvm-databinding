package ru.trubin23.tasks_mvvm_databinding.statistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.trubin23.tasks_mvvm_databinding.Injection;
import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.ViewModelHolder;
import ru.trubin23.tasks_mvvm_databinding.tasks.TasksFragment;
import ru.trubin23.tasks_mvvm_databinding.tasks.TasksViewModel;
import ru.trubin23.tasks_mvvm_databinding.util.ActivityUtils;

public class StatisticsActivity extends AppCompatActivity {

    private static final String STATS_VIEWMODEL_TAG = "STATS_VIEWMODEL_TAG";

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_act);

        setupToolbar();

        setupNavigationDrawer();

        StatisticsFragment statisticsFragment = findOrCreateFragment();

        StatisticsViewModel viewModel = findOrCreateViewModel();

        statisticsFragment.setViewModel(viewModel);
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

    @NonNull
    private StatisticsFragment findOrCreateFragment() {
        StatisticsFragment statisticsFragment = (StatisticsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (statisticsFragment == null) {
            statisticsFragment = StatisticsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.content_frame);
        }
        return statisticsFragment;
    }

    @NonNull
    private StatisticsViewModel findOrCreateViewModel() {
        //noinspection unchecked
        ViewModelHolder<StatisticsViewModel> retainedViewModel =
                (ViewModelHolder<StatisticsViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(STATS_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            return retainedViewModel.getViewModel();
        } else {
            StatisticsViewModel viewModel = new StatisticsViewModel(
                    getApplicationContext(),
                    Injection.provideTasksRepository(getApplicationContext()));

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    STATS_VIEWMODEL_TAG);

            return viewModel;
        }
    }
}
