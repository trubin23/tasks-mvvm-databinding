package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 16.03.2018.
 */

public class TaskItemViewModel extends BaseObservable {

    private TasksRepository mTasksRepository;

    private final Context mContext;

    private final ObservableField<Task> mTaskObservable = new ObservableField<>();

    private WeakReference<TaskItemNavigator> mTaskItemNavigator;

    TaskItemViewModel(@NonNull TasksRepository tasksRepository,
                      @NonNull Context context,
                      @Nullable WeakReference<TaskItemNavigator> taskItemNavigator) {
        mTasksRepository = tasksRepository;
        mContext = context;
        mTaskItemNavigator = taskItemNavigator;
    }

    void setTask(@NonNull Task task) {
        mTaskObservable.set(task);
    }

    public void taskClicked() {
        if (mTaskItemNavigator != null && mTaskItemNavigator.get()!=null){
            String taskId = getTaskId();
            mTaskItemNavigator.get().showTaskDetail(taskId);
        }
    }

    @Bindable
    public boolean getCompleted() {
        Task task = mTaskObservable.get();
        return task != null && task.isCompleted();
    }

    public void setCompleted(boolean completed) {
        String taskId = getTaskId();
        mTasksRepository.completedTask(taskId, completed);
    }

    @NonNull
    private String getTaskId(){
        Task task = mTaskObservable.get();
        return task.getTaskId();
    }

    @Bindable
    public String getTitleForItem() {
        Task task = mTaskObservable.get();
        if (task == null) {
            return mContext.getString(R.string.no_data);
        }
        return task.getTitle();
    }
}
