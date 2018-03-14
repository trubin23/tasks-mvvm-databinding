package ru.trubin23.tasks_mvvm_databinding.data.source.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ru.trubin23.tasks_mvvm_databinding.data.Task;

/**
 * Created by Andrey on 14.03.2018.
 */

public class TasksCacheRepository implements TasksCacheDataSource {

    private static TasksCacheRepository INSTANCE;

    private Map<String, Task> mCachedTask;

    private boolean mCacheIsDirty = false;

    private TasksCacheRepository() {
        mCachedTask = new LinkedHashMap<>();
    }

    public static TasksCacheRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksCacheRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksCacheRepository();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public boolean isDirty() {
        return mCacheIsDirty;
    }

    private boolean cacheNotAvailable() {
        return mCacheIsDirty || mCachedTask == null || mCachedTask.isEmpty();
    }

    @Nullable
    @Override
    public List<Task> getTasks() {
        if (cacheNotAvailable()) {
            return null;
        } else {
            return new ArrayList<>(mCachedTask.values());
        }
    }

    @Override
    public void refresh(@NonNull List<Task> tasks) {
        if (mCachedTask == null) {
            mCachedTask = new LinkedHashMap<>();
        }
        mCachedTask.clear();

        for (Task task : tasks) {
            mCachedTask.put(task.getTaskId(), task);
        }
        mCacheIsDirty = false;
    }

    @Nullable
    @Override
    public Task getTaskById(@NonNull String taskId) {
        if (cacheNotAvailable()) {
            return null;
        } else {
            return mCachedTask.get(taskId);
        }
    }
}
