package ru.trubin23.tasks_mvvm_databinding.addedittask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.databinding.AddEditTaskFragBinding;
import ru.trubin23.tasks_mvvm_databinding.databinding.TaskDetailFragBinding;

public class AddEditTaskFragment extends Fragment {

    private static final String ARGUMENT_TASK_ID = "TASK_ID";

    private AddEditTaskViewModel mViewModel;

    public AddEditTaskFragment() {
    }

    public static AddEditTaskFragment newInstance(String taskId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TASK_ID, taskId);
        AddEditTaskFragment addEditTaskFragment = new AddEditTaskFragment();
        addEditTaskFragment.setArguments(arguments);
        return addEditTaskFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_edit_task_frag, container, false);

        AddEditTaskFragBinding viewDataBinding = AddEditTaskFragBinding.bind(view);
        viewDataBinding.setViewModel(mViewModel);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();
    }

    private void setupFab() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_task);
        fab.setOnClickListener(view -> mViewModel.saveTask());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null){
            mViewModel.start(getArguments().getString(ARGUMENT_TASK_ID));
        } else {
            mViewModel.start(null);
        }
    }

    public void setViewModel(AddEditTaskViewModel viewModel) {
        mViewModel = viewModel;
    }
}
