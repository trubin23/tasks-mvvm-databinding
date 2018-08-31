package ru.trubin23.tasks_mvvm_databinding.statistics;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.databinding.StatisticsFragBinding;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel mViewModel;

    private StatisticsFragBinding mStatisticsFragBinding;

    public StatisticsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mStatisticsFragBinding = DataBindingUtil.inflate(
                inflater, R.layout.statistics_frag, container, false);
        return mStatisticsFragBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mStatisticsFragBinding.setViewModel(mViewModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start();
    }

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    public void setViewModel(StatisticsViewModel viewModel) {
        mViewModel = viewModel;
    }
}
