package ru.trubin23.tasks_mvvm_databinding.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Andrey on 12.03.2018.
 */

@Entity(tableName = "tasks")
public final class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "task_id")
    private final String mTaskId;

    @NonNull
    @ColumnInfo(name = "title")
    private final String mTitle;

    @NonNull
    @ColumnInfo(name = "description")
    private final String mDescription;

    @NonNull
    @ColumnInfo(name = "date_of_creation")
    private final String mDateOfCreation;

    @ColumnInfo(name = "completed")
    private final boolean mCompleted;

    @NonNull
    @ColumnInfo(name = "date_of_change")
    private final String mDateOfChange;

    public Task(@NonNull String title, @NonNull String description,
                @NonNull String taskId, @NonNull String dateOfCreation,
                boolean completed, @NonNull String dateOfChange) {
        mTitle = title;
        mDescription = description;
        mTaskId = taskId;
        mDateOfCreation = dateOfCreation;
        mCompleted = completed;
        mDateOfChange = dateOfChange;
    }

    @NonNull
    public String getTaskId() {
        return mTaskId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public String getDateOfCreation() {
        return mDateOfCreation;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    @NonNull
    public String getDateOfChange() {
        return mDateOfChange;
    }
}
