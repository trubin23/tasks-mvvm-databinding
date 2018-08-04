package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupMenu;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.databinding.TasksFragBinding;

/**
 * Created by Andrey on 11.03.2018.
 */

public class TasksFragment extends Fragment {

    private TasksViewModel mViewModel;

    private TasksFragBinding mTasksFragBinding;

    private TasksAdapter mTasksAdapter;

    public TasksFragment() {
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTasksFragBinding = TasksFragBinding.inflate(inflater, container, false);

        mTasksFragBinding.setViewModel(mViewModel);

        setHasOptionsMenu(true);

        return mTasksFragBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupListAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.loadTasks(true);
    }

    @Override
    public void onDestroy() {
        mTasksAdapter.destroy();
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tasks_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                showFilteringPopupMenu();
                break;
            case R.id.menu_clear:
                mViewModel.clearCompletedTasks();
                break;
            case R.id.menu_refresh:
                mViewModel.loadTasks(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilteringPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(
                getContext(), getActivity().findViewById(R.id.menu_filter));
        popupMenu.getMenuInflater().inflate(R.menu.filter_tasks, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.tasks_active:
                    mViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS);
                    break;
                case R.id.tasks_completed:
                    mViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS);
                    break;
                case R.id.tasks_all:
                    mViewModel.setFiltering(TasksFilterType.ALL_TASKS);
                    break;
                default:
                    return false;
            }

            mViewModel.loadTasks(false);
            return true;
        });

        popupMenu.show();
    }

    public void setViewModel(@NonNull TasksViewModel viewModel) {
        mViewModel = viewModel;
    }

    private void setupFab() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_task);

        fab.setOnClickListener(v -> mViewModel.addNewTask());
    }

    private void setupListAdapter() {
        ListView listView = mTasksFragBinding.tasksList;

        mTasksAdapter = new TasksAdapter((TasksActivity) getActivity());

        listView.setAdapter(mTasksAdapter);
    }
}
