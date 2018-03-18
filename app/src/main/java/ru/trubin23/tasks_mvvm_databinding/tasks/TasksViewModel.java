package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.BR;
import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 11.03.2018.
 */

public class TasksViewModel extends BaseObservable {

    private final TasksRepository mTasksRepository;

    private final Context mContext;

    private TasksFilterType mCurrentFilterType = TasksFilterType.ALL_TASKS;

    public final ObservableList<Task> mTasks = new ObservableArrayList<>();

    public final ObservableField<String> mCurrentFilteringLabel = new ObservableField<>();

    public final ObservableField<Drawable> mNoTaskIconRes = new ObservableField<>();

    public final ObservableField<String> mNoTasksLabel = new ObservableField<>();

    TasksViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context.getApplicationContext();

        setFiltering(TasksFilterType.ALL_TASKS);
    }

    @Bindable
    public boolean isEmpty() {
        return mTasks.isEmpty();
    }

    void loadTasks(boolean forceUpdate) {
        if (forceUpdate) {
            mTasksRepository.refreshTasks();
        }

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(@NonNull List<Task> tasks) {
                List<Task> tasksToShow = new ArrayList<>();

                for (Task task : tasks) {
                    switch (mCurrentFilterType) {
                        case ACTIVE_TASKS:
                            if (!task.isCompleted()) {
                                tasksToShow.add(task);
                            }
                            break;
                        case COMPLETED_TASKS:
                            if (task.isCompleted()) {
                                tasksToShow.add(task);
                            }
                            break;
                        case ALL_TASKS:
                            tasksToShow.add(task);
                            break;
                        default:
                            tasksToShow.add(task);
                            break;
                    }
                }

                mTasks.clear();
                mTasks.addAll(tasksToShow);
                notifyPropertyChanged(BR.empty);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void setFiltering(@NonNull TasksFilterType filterType) {
        mCurrentFilterType = filterType;

        switch (filterType) {
            case ACTIVE_TASKS:
                mCurrentFilteringLabel.set(mContext.getString(R.string.label_active));
                mNoTasksLabel.set(mContext.getString(R.string.no_tasks_active));
                mNoTaskIconRes.set(mContext.getDrawable(R.drawable.ic_check_circle));
                break;
            case COMPLETED_TASKS:
                mCurrentFilteringLabel.set(mContext.getString(R.string.label_completed));
                mNoTasksLabel.set(mContext.getString(R.string.no_tasks_completed));
                mNoTaskIconRes.set(mContext.getDrawable(R.drawable.ic_check_box));
                break;
            case ALL_TASKS:
                mCurrentFilteringLabel.set(mContext.getString(R.string.label_all));
                mNoTasksLabel.set(mContext.getString(R.string.no_tasks_all));
                mNoTaskIconRes.set(mContext.getDrawable(R.drawable.ic_verified));
                break;
        }
    }
}
