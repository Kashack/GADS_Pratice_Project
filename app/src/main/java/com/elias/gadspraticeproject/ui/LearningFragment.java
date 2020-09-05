package com.elias.gadspraticeproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elias.gadspraticeproject.R;
import com.elias.gadspraticeproject.adapter.HoursRecyclerAdapter;
import com.elias.gadspraticeproject.model.Hours;
import com.elias.gadspraticeproject.services.LearningLeaders;
import com.elias.gadspraticeproject.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment {
    private RecyclerView recyclerView;
    private HoursRecyclerAdapter leaderboardRecyclerAdapter;
    private LinearLayoutManager linearLayoutManager;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.learning_recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        LearningLeaders learningLeaders = ServiceBuilder.buildServices(LearningLeaders.class);

        Call<List<Hours>> call = learningLeaders.getAllHours();

        call.enqueue(new Callback<List<Hours>>() {
            @Override
            public void onResponse(Call<List<Hours>> call, Response<List<Hours>> response) {
                Toast.makeText(getContext(),"Sucess",Toast.LENGTH_LONG).show();
                leaderboardRecyclerAdapter = new HoursRecyclerAdapter(getContext(), response.body());
                recyclerView.setAdapter(leaderboardRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<Hours>> call, Throwable t) {

            }
        });

    }
}