package com.example.catify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.popup);
        dialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<DataModel>> call = jsonPlaceholder.getData();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Home.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    dialog.dismiss();
                    List<DataModel> breedList = response.body();
                    DetailAdapter postAdapter = new DetailAdapter(Home.this, breedList);
                    recyclerView.setAdapter(postAdapter);

                    postAdapter.setOnItemClickListener(new DetailAdapter.OnIteClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            ArrayList<String> catDetails = new ArrayList<>();

                            catDetails.add(0, breedList.get(position).getName()+"");
                            catDetails.add(1, breedList.get(position).getDescription()+"");
                            catDetails.add(2, breedList.get(position).getWeight().getMetric()+"");
                            catDetails.add(3, breedList.get(position).getOrigin()+"");
                            catDetails.add(4, breedList.get(position).getTemperament()+"");
                            catDetails.add(5, breedList.get(position).getImage().getUrl()+"");
                            catDetails.add(6, breedList.get(position).getVetstreet_url()+"");
                            Intent intent = new Intent(Home.this, Profile.class);
                            intent.putExtra("ls", catDetails);
                            startActivity(intent);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                dialog.setContentView(R.layout.popuperr);
                dialog.show();
                Toast.makeText(Home.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}