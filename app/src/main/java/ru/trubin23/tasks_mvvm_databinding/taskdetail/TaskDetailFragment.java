package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 20.03.2018.
 */

public class TaskDetailFragment extends Fragment {

    private TaskDetailViewModel mViewModel;

    public void setViewModel(TaskDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
