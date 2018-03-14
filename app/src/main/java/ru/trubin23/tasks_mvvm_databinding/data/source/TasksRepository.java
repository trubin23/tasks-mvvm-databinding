package ru.trubin23.tasks_mvvm_databinding.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.cache.TasksCacheDataSource;
import ru.trubin23.tasks_mvvm_databinding.data.source.local.TasksLocalDataSource;

/**
 * Created by Andrey on 12.03.2018.
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE;

    private final TasksDataSource mTasksRemoteDataSource;
    private final TasksLocalDataSource mTasksLocalDataSource;
    private final TasksCacheDataSource mTasksCacheDataSource;

    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksLocalDataSource tasksLocalDataSource,
                            @NonNull TasksCacheDataSource tasksCacheDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
        mTasksCacheDataSource = tasksCacheDataSource;
    }

    public static TasksRepository getInstance(@NonNull TasksDataSource tasksRemoteDataSource,
                                              @NonNull TasksLocalDataSource tasksLocalDataSource,
                                              @NonNull TasksCacheDataSource tasksCacheDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource,
                    tasksLocalDataSource,tasksCacheDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        List<Task> tasks = mTasksCacheDataSource.getTasks();
        if (tasks != null){
            callback.onTasksLoaded(tasks);
        }

        if (mTasksCacheDataSource.isDirty()){
            getTasksFromRemoteDataSource(callback);
        } else {
            getTasksFromLocalDataSource(callback, true);
        }
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
    public void completedTask(@NonNull String taskId, boolean completed) {

    }

    @Override
    public void clearCompletedTask() {

    }

    private void getTasksFromLocalDataSource(@NonNull LoadTasksCallback callback,
                                             boolean handleErrors) {

    }

    private void getTasksFromRemoteDataSource(@NonNull LoadTasksCallback callback) {

    }
}
