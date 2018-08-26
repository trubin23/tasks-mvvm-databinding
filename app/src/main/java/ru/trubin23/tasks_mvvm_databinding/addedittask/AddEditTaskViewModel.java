package ru.trubin23.tasks_mvvm_databinding.addedittask;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

public class AddEditTaskViewModel extends BaseObservable {

    public final ObservableField<String> mTitle = new ObservableField<>();

    public final ObservableField<String> mDescription = new ObservableField<>();

    public final ObservableBoolean mDataLoading = new ObservableBoolean(false);

    public final ObservableField<String> mSnackbarText = new ObservableField<>();

    private AddEditTaskNavigator mNavigator;

    @Nullable
    private String mTaskId;

    private boolean mIsNewTask;

    private boolean mIsDataLoaded = false;

    private final TasksRepository mTasksRepository;

    private final Context mContext;

    AddEditTaskViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context;
    }

    public void setNavigator(AddEditTaskNavigator navigator) {
        mNavigator = navigator;
    }

    public void onActivityDestroyed() {
        mNavigator = null;
    }

    public void start(String taskId) {
        if (mDataLoading.get()) {
            return;
        }
        mTaskId = taskId;
        if (taskId == null){
            mIsNewTask = true;
            return;
        }
        if (mIsDataLoaded){
            return;
        }
        mIsNewTask = false;
        mDataLoading.set(true);
        mTasksRepository.getTask(mTaskId, new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(@NonNull Task task) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Nullable
    public String getSnackbarText() {
        return mSnackbarText.get();
    }

    public void saveTask() {
        if (mIsNewTask) {
            createTask(mTitle.get(), mDescription.get());
        } else {
            updateTask(mTitle.get(), mDescription.get());
        }
    }

    private void createTask(String title, String description) {
        Task task = new Task(title, description);
        if (task.isEmpty()){
            mSnackbarText.set(mContext.getString(R.string.empty_task_message));
        } else {
            mTasksRepository.saveTask(task);
            navigateOnTaskSaved();
        }
    }

    private void updateTask(String title, String description) {
        if (!mIsNewTask) {
            mTasksRepository.saveTask(new Task(title, description, mTaskId));
            navigateOnTaskSaved();
        }
    }

    private void navigateOnTaskSaved() {
        if (mNavigator != null) {
            mNavigator.onTaskSaved();
        }
    }
}
