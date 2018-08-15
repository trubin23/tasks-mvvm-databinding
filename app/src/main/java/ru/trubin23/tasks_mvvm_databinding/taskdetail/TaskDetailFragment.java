package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.databinding.TaskDetailFragBinding;

/**
 * Created by Andrey on 20.03.2018.
 */

public class TaskDetailFragment extends Fragment {

    private static final String ARGUMENT_TASK_ID = "TASK_ID";

    private TaskDetailViewModel mViewModel;

    public TaskDetailFragment() {
    }

    public static TaskDetailFragment newInstance(String taskId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TASK_ID, taskId);
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        taskDetailFragment.setArguments(arguments);
        return taskDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_detail_frag, container, false);

        TaskDetailFragBinding viewDataBinding = TaskDetailFragBinding.bind(view);
        viewDataBinding.setViewModel(mViewModel);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();
    }

    private void setupFab() {
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab_edit_task);
        floatingActionButton.setOnClickListener(view -> mViewModel.startEditTask());
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start(getArguments().getString(ARGUMENT_TASK_ID));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.taskdetail_frag_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                return true;
        }
        return false;
    }

    public void setViewModel(TaskDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
