package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {
    TextView origin;
    TextView alsoKnown;
    TextView desc;
    TextView ingredients;
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        origin = (TextView) findViewById(R.id.origin_tv);
        alsoKnown = (TextView) findViewById(R.id.also_known_tv);
        desc = (TextView) findViewById(R.id.description_tv);
        ingredients = (TextView) findViewById(R.id.ingredients_tv);
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        try {
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);
            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }


            populateUI(sandwich);
            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(ingredientsIv);

            setTitle(sandwich.getMainName());
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if (sandwich.getPlaceOfOrigin().isEmpty() || sandwich.getPlaceOfOrigin() == "") {
            origin.setText(getResources().getString(R.string.notAvalable));
        } else {
            origin.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            origin.setText(getResources().getString(R.string.notAvalable));
        } else {
            alsoKnown.setText(sandwich.getAlsoKnownAs().toString());
        }

        if (sandwich.getDescription().isEmpty() || sandwich.getDescription() == "") {
            origin.setText(getResources().getString(R.string.notAvalable));
        } else {
            desc.setText(sandwich.getDescription());
        }
        if (sandwich.getIngredients().isEmpty()) {
            origin.setText(getResources().getString(R.string.notAvalable));
        } else {
            ingredients.setText(sandwich.getIngredients().toString());
        }
    }

}
