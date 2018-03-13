package ru.trubin23.tasks_mvvm_databinding.data.source.local;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;

/**
 * Created by Andrey on 13.03.2018.
 */

public class TasksLocalRepository implements TasksLocalDataSource {

    private static TasksLocalRepository INSTANCE;

    private TasksDao mTasksDao;
    private final Executor mDiskIO;

    private TasksLocalRepository(@NonNull TasksDao tasksDao,
                                 @NonNull Executor diskIO) {
        mTasksDao = tasksDao;
        mDiskIO = diskIO;
    }

    @NonNull
    public static TasksLocalRepository getInstance(@NonNull TasksDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (TasksLocalRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksLocalRepository(tasksDao,
                            Executors.newSingleThreadExecutor());
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void updateTask(@NonNull Task task) {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void completedTask(@NonNull String taskId, boolean completed) {

    }

    @Override
    public void clearCompletedTask() {

    }
}
