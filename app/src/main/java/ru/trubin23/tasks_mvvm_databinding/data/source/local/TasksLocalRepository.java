package ru.trubin23.tasks_mvvm_databinding.data.source.local;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

}
