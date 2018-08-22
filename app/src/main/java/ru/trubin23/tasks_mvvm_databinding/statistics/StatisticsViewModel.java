package ru.trubin23.tasks_mvvm_databinding.statistics;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

public class StatisticsViewModel extends BaseObservable {

    public final ObservableBoolean mDataLoading = new ObservableBoolean(false);

    private int mNumberOfActiveTasks = 0;

    private int mNumberOfCompletedTasks = 0;

    private Context mContext;

    private final TasksRepository mTasksRepository;

    public StatisticsViewModel(Context context, TasksRepository tasksRepository) {
        mContext = context;
        mTasksRepository = tasksRepository;
    }

    public void start() {
        mDataLoading.set(true);

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(@NonNull List<Task> tasks) {
                int active = 0;
                int completed = 0;

                for (Task task : tasks) {
                    if (task.isCompleted()) {
                        completed++;
                    } else {
                        active++;
                    }
                }

                mNumberOfActiveTasks = active;
                mNumberOfCompletedTasks = completed;

                mDataLoading.set(false);
            }

            @Override
            public void onDataNotAvailable() {
                mDataLoading.set(false);
            }
        });
    }

    @Bindable
    public boolean isEmpty() {
        return mNumberOfActiveTasks + mNumberOfCompletedTasks == 0;
    }

    @Bindable
    public String getNumberOfActiveTasks() {
        return mContext.getString(R.string.statistics_active_tasks, mNumberOfActiveTasks);
    }

    @Bindable
    public String getNumberOfCompletedTasks() {
        return mContext.getString(R.string.statistics_completed_tasks, mNumberOfCompletedTasks);
    }
}
