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
import com.elias.gadspraticeproject.adapter.SkillRecyclerAdapter;
import com.elias.gadspraticeproject.model.Skill;
import com.elias.gadspraticeproject.services.ServiceBuilder;
import com.elias.gadspraticeproject.services.SkillIQLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillFragment extends Fragment {
    private RecyclerView recyclerView;
    private SkillRecyclerAdapter leaderboardRecyclerAdapter;
    private LinearLayoutManager linearLayoutManager;

    public SkillFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.skill_recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        SkillIQLeaders skillIQLeaders = ServiceBuilder.buildServices(SkillIQLeaders.class);
        Call<List<Skill>> call = skillIQLeaders.getAllSkill();

        call.enqueue(new Callback<List<Skill>>() {
            @Override
            public void onResponse(Call<List<Skill>> call, Response<List<Skill>> response) {
                Toast.makeText(getContext(),"Sucess",Toast.LENGTH_LONG).show();
                leaderboardRecyclerAdapter = new SkillRecyclerAdapter(getContext(), response.body());
                recyclerView.setAdapter(leaderboardRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<Skill>> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }

}