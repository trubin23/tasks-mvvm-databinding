package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 27.03.2018.
 */

public class TaskDetailViewModel extends BaseObservable {

    private final TasksRepository mTasksRepository;

    private final ObservableField<Task> mTaskObservable = new ObservableField<>();

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> description = new ObservableField<>();

    private final ObservableField<String> mSnackbarText = new ObservableField<>();

    public final ObservableBoolean mDataLoading = new ObservableBoolean(false);

    private final Context mContext;

    private TaskDetailNavigator mNavigator;

    TaskDetailViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context;
    }

    void start(@Nullable String taskId) {
        if (taskId != null) {
            mDataLoading.set(true);

            mTasksRepository.getTask(taskId, new TasksDataSource.GetTaskCallback() {
                @Override
                public void onTaskLoaded(@NonNull Task task) {
                    mTaskObservable.set(task);
                    mDataLoading.set(true);
                    notifyChange();
                }

                @Override
                public void onDataNotAvailable() {
                    mTaskObservable.set(null);
                    mDataLoading.set(false);
                }
            });
        }
    }

    @Bindable
    public boolean getCompleted() {
        Task task = mTaskObservable.get();
        return task != null && task.isCompleted();
    }

    public void setCompleted(boolean completed) {
        Task task = mTaskObservable.get();
        if (task == null) {
            return;
        }

        if (completed) {
            mTasksRepository.completedTask(task.getTaskId(), true);
            mSnackbarText.set(mContext.getResources().getString(R.string.task_marked_complete));
        } else {
            mTasksRepository.completedTask(task.getTaskId(), false);
            mSnackbarText.set(mContext.getResources().getString(R.string.task_marked_active));
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

    @Bindable
    public boolean isDataAvailable() {
        return mTaskObservable.get() != null;
    }

    public void onRefresh() {
        Task task = mTaskObservable.get();
        if (task != null){
            start(task.getTaskId());
        }
    }
}
