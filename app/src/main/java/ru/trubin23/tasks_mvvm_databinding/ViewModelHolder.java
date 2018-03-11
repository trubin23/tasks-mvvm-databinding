package ru.trubin23.tasks_mvvm_databinding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 11.03.2018.
 */

public class ViewModelHolder<T> extends Fragment {

    private T mViewModel;

    public static <VM> ViewModelHolder createContainer(@NonNull VM viewModel){
        ViewModelHolder<VM> viewModelHolder = new ViewModelHolder<>();
        viewModelHolder.setViewModel(viewModel);
        return viewModelHolder;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public T getViewModel() {
        return mViewModel;
    }

    private void setViewModel(@NonNull T viewModel) {
        mViewModel = viewModel;
    }
}
