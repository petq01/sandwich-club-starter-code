package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String OWN_NAME = "name";
    private static final String OWN_MAIN_NAME = "name";
    private static final String OWN_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String OWN_INGREDIENTS = "ingredients";
    private static final String OWN_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String OWN_DESCRIPTION = "description";
    private static final String OWN_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();

        JSONObject sandwitchJson = new JSONObject(json);
        if (sandwitchJson.has(OWN_NAME)) {
            JSONObject name = sandwitchJson.getJSONObject(OWN_NAME);
            String mainName = name.optString(OWN_MAIN_NAME);
            JSONArray alsoKnownAs = name.getJSONArray(OWN_ALSO_KNOWN_AS);
            List<String> alsoList = new ArrayList<String>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoList.add(alsoKnownAs.optString(i));
            }


            JSONArray ingredients = sandwitchJson.getJSONArray(OWN_INGREDIENTS);
            List<String> ingredientsList = new ArrayList<String>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsList.add(ingredients.optString(i));
            }


            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(alsoList);
            sandwich.setPlaceOfOrigin(sandwitchJson.optString(OWN_PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwitchJson.optString(OWN_DESCRIPTION));
            sandwich.setImage(sandwitchJson.optString(OWN_IMAGE));
            sandwich.setIngredients(ingredientsList);
        }


        return sandwich;
    }
}
