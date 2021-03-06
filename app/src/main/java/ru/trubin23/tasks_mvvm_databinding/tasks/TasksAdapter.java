package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.Injection;
import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.databinding.TaskItemBinding;

/**
 * Created by Andrey on 16.03.2018.
 */

public class TasksAdapter extends BaseAdapter {

    private WeakReference<TaskItemNavigator> mTaskItemNavigator;

    private TasksViewModel mTasksViewModel;

    private List<Task> mTasks;

    TasksAdapter(@NonNull TaskItemNavigator taskItemNavigator,
                 TasksViewModel tasksViewModel) {
        mTaskItemNavigator = new WeakReference<>(taskItemNavigator);
        mTasksViewModel = tasksViewModel;
        mTasks = new ArrayList<>();
        setTasks(mTasks);
    }

    void destroy() {
        mTaskItemNavigator = null;
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Task getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Task task = getItem(position);
        TaskItemBinding taskItemBinding;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            taskItemBinding = TaskItemBinding.inflate(inflater, viewGroup, false);
        } else {
            taskItemBinding = DataBindingUtil.getBinding(convertView);
        }

        TaskItemViewModel taskItemViewModel = new TaskItemViewModel(
                Injection.provideTasksRepository(
                        viewGroup.getContext().getApplicationContext()),
                viewGroup.getContext().getApplicationContext(),
                mTaskItemNavigator
        );

        taskItemBinding.setViewModel(taskItemViewModel);

        taskItemViewModel.setTask(task);

        taskItemViewModel.mSnackbarText.addOnPropertyChangedCallback(
                new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {
                        mTasksViewModel.mSnackbarText.set(taskItemViewModel.getSnackbarText());
                    }
                }
        );

        return taskItemBinding.getRoot();
    }

    public void setTasks(@NonNull List<Task> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }
}
