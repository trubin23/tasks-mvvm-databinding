package ru.trubin23.tasks_mvvm_databinding.data.source.remote;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;

/**
 * Created by Andrey on 13.03.2018.
 */

public class TasksRemoteRepository implements TasksDataSource {

    private static TasksRemoteRepository INSTANCE;

    private static final int THREAD_COUNT = 3;

    private Executor mNetworkIO;

    private TasksRemoteRepository(@NonNull Executor networkIO) {
        mNetworkIO = networkIO;
    }

    public static TasksRemoteRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksRemoteRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksRemoteRepository(
                            Executors.newFixedThreadPool(THREAD_COUNT));
                }
            }
        }
        return INSTANCE;
    }
}
