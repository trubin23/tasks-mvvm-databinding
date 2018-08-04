package ru.trubin23.tasks_mvvm_databinding.addedittask;

import android.support.v4.app.Fragment;

public class AddEditTaskFragment extends Fragment {

    private AddEditTaskViewModel mViewModel;

    private AddEditTaskFragBinding mAddEditTaskFragBinding;

    public AddEditTaskFragment() {
    }

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }
}
