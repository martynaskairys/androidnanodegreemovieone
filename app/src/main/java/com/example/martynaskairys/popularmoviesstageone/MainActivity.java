package com.example.martynaskairys.popularmoviesstageone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private String TITLE = "title";
    private String OVERVIEW = "overview";
    private String RELEASE_DATE = "release_date";
    private String VOTE_NUMBER = "vote_number";
    private String POSTER = "poster";
    public String user_choice = "popular";

    private String[] imgUrl = new String[10];

    private JSONArray movieDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        showGridView(user_choice);
    }

    private void showGridView(String user_choice) {
        GridView gridView;
        if (fetchMovieData()) {
            gridView = findViewById(R.id.grid_view);
            gridView.setAdapter(new ImageAdapter(this, imgUrl));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject object = movieDetails.getJSONObject(i);
                        String title = object.getString(TITLE);
                        String overview = object.getString(OVERVIEW);
                        String release_date = object.getString(RELEASE_DATE);
                        String vote_number = object.getString(VOTE_NUMBER);
                        String poster = object.getString(POSTER);

                        Intent intent = new Intent(getApplicationContext(), MovieInfo.class);
                        intent.putExtra(TITLE, title);
                        intent.putExtra(OVERVIEW, overview);
                        intent.putExtra(RELEASE_DATE, release_date);
                        intent.putExtra(VOTE_NUMBER, vote_number);
                        intent.putExtra(POSTER, poster);
                        startActivity(intent);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "something is wrong",
                                Toast.LENGTH_LONG).show();
                    }

                }
            });


        } else {
            Toast.makeText(getApplicationContext(), "something is wrong",
                    Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mostPopular:
                user_choice = "popular";
                showGridView(user_choice);
                return true;

            case R.id.bestRated:
                user_choice = "best";
                showGridView(user_choice);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private boolean fetchMovieData() {

        DownloadMovieInfo downloadMovieInfo = new DownloadMovieInfo();

        try {
            String results = downloadMovieInfo.execute(user_choice).get();
            if (results != null) {
                JSONObject movie = new JSONObject(results);
                movieDetails = movie.getJSONArray("results");

                return true;
            } else
                return false;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

}
