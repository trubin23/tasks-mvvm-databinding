package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 11.03.2018.
 */

public class TasksFragment extends Fragment {

    private TasksViewModel mTasksViewModel;

    public void setViewModel(@NonNull TasksViewModel viewModel) {
        mTasksViewModel = viewModel;
    }
}
