package com.example.martynaskairys.popularmoviesstageone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by martynaskairys on 4/15/18.
 */

public class MovieInfo extends AppCompatActivity {

    private TextView descriptionView;
    private TextView votingNumbers;
    private TextView titleName;
    private TextView releaseDate;

    private ImageView movieImage;

    String description;
    String voting;
    String title;
    String releaseDateMovie;
    String poster;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        setContentView(R.layout.movie_details);

        descriptionView = findViewById(R.id.description);
        votingNumbers = findViewById(R.id.voting);
        titleName = findViewById(R.id.title);
        releaseDate = findViewById(R.id.releaseDateMovie);
        movieImage = findViewById(R.id.movieImage);

        updateMovieInfo();

    }

    private boolean updateMovieInfo() {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {

            String TITLE = "title";
            String DESCRIPTION = "description";
            String VOTING = "voting";
            String RELEASE_DATE_MOVIE = "releaseDateMovie";
            String POSTER = "poster";


            if (extras.containsKey(TITLE) && extras.containsKey(DESCRIPTION)
                    && extras.containsKey(VOTING) && extras.containsKey(RELEASE_DATE_MOVIE)
                    && extras.containsKey(POSTER)) {
                title = intent.getStringExtra(TITLE);
                description = intent.getStringExtra(DESCRIPTION);
                voting = intent.getStringExtra(VOTING);
                releaseDateMovie = intent.getStringExtra(RELEASE_DATE_MOVIE);
                poster = intent.getStringExtra(POSTER);

            } else
                return false;
        } else
            return false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(releaseDateMovie);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat dateFormat = new SimpleDateFormat();
        releaseDate.setText("Release Date: " + dateFormat.format(date));
        titleName.setText("Title: " + title);
        descriptionView.setText("Description: " + description);
        votingNumbers.setText("Vote average: " + voting);
        Picasso.with(this).load(poster).into(movieImage);
        return true;

    }
}
