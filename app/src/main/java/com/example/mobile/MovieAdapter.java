package com.example.mobile;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    ArrayList<Movie> items = new ArrayList<Movie>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.movie_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Movie item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie item) {
        items.add(item);
    }

    public void setItems(ArrayList<Movie> items) {
        this.items = items;
    }

    public Movie getItem(int position) {
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView audienceChangeText; // 전일 대비 관객수 증감을 나타내는 TextView 추가

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            audienceChangeText = itemView.findViewById(R.id.audienceChangeText); // 레이아웃에 추가한 TextView 연결
        }

        public void setItem(Movie item) {
            textView.setText(item.movieNm);
            Log.d("영화명 : ", textView.getText().toString());
            textView2.setText(item.audiCnt + " 명");
            audienceChangeText.setText(item.audiInten + " 명");

            String audienceChange = item.audiInten; // audienceChangeText의 텍스트 가져오기
            int changeValue = Integer.parseInt(audienceChange.replace("+", "")); // "+" 기호 제거 후 정수로 변환

            if (changeValue > 0) {
                audienceChangeText.setTextColor(itemView.getContext().getResources().getColor(android.R.color.holo_red_dark)); // 양수면 빨간색
            } else if (changeValue < 0) {
                audienceChangeText.setTextColor(itemView.getContext().getResources().getColor(android.R.color.holo_blue_dark)); // 음수면 파란색
            }
        }

    }

}
