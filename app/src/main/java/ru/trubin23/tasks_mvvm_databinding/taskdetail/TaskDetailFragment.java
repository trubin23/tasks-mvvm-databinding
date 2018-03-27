package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 20.03.2018.
 */

public class TaskDetailFragment extends Fragment {

    private static final String ARGUMENT_TASK_ID = "TASK_ID";

    private TaskDetailViewModel mViewModel;

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start(getArguments().getString(ARGUMENT_TASK_ID));
    }

    public void setViewModel(TaskDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
