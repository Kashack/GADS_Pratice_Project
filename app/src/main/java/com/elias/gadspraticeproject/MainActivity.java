package com.elias.gadspraticeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elias.gadspraticeproject.adapter.ViewPagerAdapter;
import com.elias.gadspraticeproject.ui.LearningFragment;
import com.elias.gadspraticeproject.ui.SkillFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SkillFragment skillFragment;
    private LearningFragment learningFragment;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        button = findViewById(R.id.submit_btn);
        learningFragment = new LearningFragment();
        skillFragment = new SkillFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragmemt(learningFragment, getString(R.string.learning_leaders_text));
        viewPagerAdapter.addFragmemt(skillFragment,getString(R.string.skill_iq_leaders_text));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SubmitActivity.class);
            startActivity(intent);
        });
    }
}