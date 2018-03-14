package ru.trubin23.tasks_mvvm_databinding.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.data.Task;

/**
 * Created by Andrey on 12.03.2018.
 */

public interface TasksDataSource {

    interface LoadTasksCallback {

        void onTasksLoaded(@NonNull List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(@NonNull Task task);

        void onDataNotAvailable();
    }

    void getTasks(@NonNull LoadTasksCallback callback);

    void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void updateTask(@NonNull Task task);

    void deleteTask(@NonNull String taskId);

    void completedTask(@NonNull String taskId, boolean completed);

    void clearCompletedTask();
}
