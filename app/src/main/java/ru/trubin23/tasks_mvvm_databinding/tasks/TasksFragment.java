package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.databinding.TasksFragBinding;

/**
 * Created by Andrey on 11.03.2018.
 */

public class TasksFragment extends Fragment {

    private TasksViewModel mTasksViewModel;

    private TasksFragBinding mTasksFragBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTasksFragBinding = TasksFragBinding.inflate(inflater, container, false);

        mTasksFragBinding.setViewModel(mTasksViewModel);

        View root = mTasksFragBinding.getRoot();

        return root;
    }

    public void setViewModel(@NonNull TasksViewModel viewModel) {
        mTasksViewModel = viewModel;
    }
}
