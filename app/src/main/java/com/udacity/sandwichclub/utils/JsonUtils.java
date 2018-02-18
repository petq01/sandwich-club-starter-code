package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();

        JSONObject sandwithJson = new JSONObject(json);
        JSONObject name = sandwithJson.getJSONObject("name");
        String mainName = name.getString("mainName");
        JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
        List<String> alsoList = new ArrayList<String>();
        for (int i = 0; i < alsoKnownAs.length(); i++) {
            alsoList.add(alsoKnownAs.getString(i));
        }


        JSONArray ingredients = sandwithJson.getJSONArray("ingredients");
        List<String> ingredientsList = new ArrayList<String>();
        for (int i = 0; i < ingredients.length(); i++) {
            ingredientsList.add(ingredients.getString(i));
        }


        sandwich.setMainName(mainName);
        sandwich.setAlsoKnownAs(alsoList);
        sandwich.setPlaceOfOrigin(sandwithJson.getString("placeOfOrigin"));
        sandwich.setDescription(sandwithJson.getString("description"));
        sandwich.setImage(sandwithJson.getString("image"));
        sandwich.setIngredients(ingredientsList);


        return sandwich;
    }
}
