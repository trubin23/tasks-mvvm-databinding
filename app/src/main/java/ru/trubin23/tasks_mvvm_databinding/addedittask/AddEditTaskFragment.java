package ru.trubin23.tasks_mvvm_databinding.addedittask;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ru.trubin23.tasks_mvvm_databinding.databinding.AddEditTaskFragBinding;
import ru.trubin23.tasks_mvvm_databinding.taskdetail.TaskDetailFragment;

public class AddEditTaskFragment extends Fragment {

    private static final String ARGUMENT_TASK_ID = "TASK_ID";

    private AddEditTaskViewModel mViewModel;

    private AddEditTaskFragBinding mAddEditTaskFragBinding;

    public AddEditTaskFragment() {
    }

    public static AddEditTaskFragment newInstance(String taskId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TASK_ID, taskId);
        AddEditTaskFragment addEditTaskFragment = new AddEditTaskFragment();
        addEditTaskFragment.setArguments(arguments);
        return addEditTaskFragment;
    }
}
