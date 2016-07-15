package com.shadowsong.stepview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shadowsong.stepview.VerticalStepDecoration;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initList();
  }

  private void initList() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
    // LinearLayoutManager.VERTICAL));
    recyclerView
        .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    VerticalStepDecoration stepDecoration = new VerticalStepDecoration.Builder(this)
        .currentRes(R.drawable.step_current)
        .doneRes(R.drawable.step_done)
        .undoneRes(R.drawable.step_undone)
        .width(200)
        .lineWidth(3)
        .lineColor(0xff11cd6e)
        .dashColor(0xff11cd6e)
        .currentStep(25)
        .build();


    recyclerView.addItemDecoration(stepDecoration);
    recyclerView.setAdapter(new ItemAdapter());

  }

  class ItemHolder extends RecyclerView.ViewHolder {

    TextView contentTextView;

    public ItemHolder(View itemView) {
      super(itemView);
      contentTextView = (TextView) itemView.findViewById(R.id.content_tv);
    }
  }
  class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new ItemHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
      holder.contentTextView.setText("Step " + position);
    }

    @Override
    public int getItemCount() {
      return 50;
    }
  }
}
