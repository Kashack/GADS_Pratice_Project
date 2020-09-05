package com.elias.gadspraticeproject.services;

import com.elias.gadspraticeproject.model.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIQLeaders {
    @GET("/api/skilliq")
    Call<List<Skill>> getAllSkill();
}
