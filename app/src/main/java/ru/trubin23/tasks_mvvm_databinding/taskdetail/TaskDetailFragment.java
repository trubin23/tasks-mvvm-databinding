package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;

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

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start(getArguments().getString(ARGUMENT_TASK_ID));
    }

    public void setViewModel(TaskDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
