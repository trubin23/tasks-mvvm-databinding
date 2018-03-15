package ru.trubin23.tasks_mvvm_databinding.data.source.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 15.03.2018.
 */

class NetworkTask {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dateOfCreation")
    @Expose
    private String dateOfCreation;
    @SerializedName("completed")
    @Expose
    private String completed;
    @SerializedName("dateOfChange")
    @Expose
    private String dateOfChange;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(String dateOfChange) {
        this.dateOfChange = dateOfChange;
    }
}
