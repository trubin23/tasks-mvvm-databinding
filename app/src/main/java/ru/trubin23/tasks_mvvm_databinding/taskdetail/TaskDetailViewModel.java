package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 27.03.2018.
 */

public class TaskDetailViewModel extends BaseObservable {

    private final TasksRepository mTasksRepository;

    private final Context mContext;
    private TaskDetailNavigator mNavigator;

    TaskDetailViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context;
    }

    void start(@Nullable String taskId) {
        if (taskId != null){
            mTasksRepository.getTask(taskId, new TasksDataSource.GetTaskCallback() {
                @Override
                public void onTaskLoaded(@NonNull Task task) {

                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }
    }

    void setNavigator(TaskDetailNavigator navigator) {
        mNavigator = navigator;
    }
}