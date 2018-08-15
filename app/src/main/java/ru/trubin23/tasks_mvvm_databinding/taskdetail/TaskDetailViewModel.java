package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
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

    private final ObservableField<Task> mTaskObservable = new ObservableField<>();

    private final Context mContext;

    private TaskDetailNavigator mNavigator;

    TaskDetailViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context;
    }

    void start(@Nullable String taskId) {
        if (taskId != null) {
            mTasksRepository.getTask(taskId, new TasksDataSource.GetTaskCallback() {
                @Override
                public void onTaskLoaded(@NonNull Task task) {
                    mTaskObservable.set(task);
                    notifyChange();
                }

                @Override
                public void onDataNotAvailable() {
                    mTaskObservable.set(null);
                }
            });
        }
    }

    void setNavigator(TaskDetailNavigator navigator) {
        mNavigator = navigator;
    }

    public void onActivityDestroyed() {
        mNavigator = null;
    }

    public void startEditTask() {
        if (mNavigator != null) {
            mNavigator.onStartEditTask();
        }
    }

    public void deleteTask() {
        if (mNavigator != null) {
            mNavigator.onTaskDeleted();
        }
    }
}
