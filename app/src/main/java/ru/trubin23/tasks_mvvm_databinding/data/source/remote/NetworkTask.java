package ru.trubin23.tasks_mvvm_databinding.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 15.03.2018.
 */

class NetworkTask {

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("dateOfCreation")
    @Expose
    private String mDateOfCreation;
    @SerializedName("completed")
    @Expose
    private Integer mCompleted;
    @SerializedName("dateOfChange")
    @Expose
    private String mDateOfChange;

    public NetworkTask(@NonNull String id, @NonNull String title,
                       @NonNull String description, @NonNull String dateOfCreation,
                       boolean completed, @NonNull String dateOfChange) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDateOfCreation = dateOfCreation;
        mCompleted = StatusOfTask.booleanToInteger(completed);
        mDateOfChange = dateOfChange;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDateOfCreation() {
        return mDateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        mDateOfCreation = dateOfCreation;
    }

    public Integer getCompleted() {
        return mCompleted;
    }

    public void setCompleted(Integer completed) {
        mCompleted = completed;
    }

    public String getDateOfChange() {
        return mDateOfChange;
    }

    public void setDateOfChange(String dateOfChange) {
        mDateOfChange = dateOfChange;
    }
}
