package ru.trubin23.tasks_mvvm_databinding.data.source;

import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.source.local.TasksLocalDataSource;

/**
 * Created by Andrey on 12.03.2018.
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE;

    private final TasksDataSource mTasksRemoteDataSource;
    private final TasksLocalDataSource mTasksLocalDataSource;

    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksLocalDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    public static TasksRepository getInstance(@NonNull TasksDataSource tasksRemoteDataSource,
                                        @NonNull TasksLocalDataSource tasksLocalDataSource) {
        if (INSTANCE == null){
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }
}
