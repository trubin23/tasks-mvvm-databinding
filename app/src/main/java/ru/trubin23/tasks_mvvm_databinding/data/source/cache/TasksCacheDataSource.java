package ru.trubin23.tasks_mvvm_databinding.data.source.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.data.Task;

/**
 * Created by Andrey on 14.03.2018.
 */

public interface TasksCacheDataSource {

    boolean isDirty();

    @Nullable
    List<Task> getTasks();

    void refresh(@NonNull List<Task> tasks);

    @Nullable
    Task getTaskById(@NonNull String taskId);
}
