package ru.trubin23.tasks_mvvm_databinding.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 15.03.2018.
 */

class StatusOfTask {

    @SerializedName("completed")
    @Expose
    private Integer mCompleted;

    StatusOfTask(boolean completed) {
        mCompleted = booleanToInteger(completed);
    }

    @NonNull
    Integer getCompleted() {
        return mCompleted;
    }

    void setCompleted(@NonNull Integer completed) {
        mCompleted = completed;
    }

    @NonNull
    static Integer booleanToInteger(boolean completed) {
        return completed ? 1 : 0;
    }

    @NonNull
    static boolean integerToBoolean(Integer completed) {
        return completed != 0;
    }
}
