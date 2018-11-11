package com.borsch.shift.cft.features.account.domain.model;

import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.State;

import java.util.HashMap;

public final class User {
    private Account userInfo;
    private Fridge fridge;
    private String login;
    private String password;
    private HashMap<String,Recipe> myRecipes; // я участвствую как повар
    private HashMap<String, State> recipeState; // состояния рецептов, где я учавствую, но не повар
    private HashMap<String,Recipe> notMyRecipes; // я участвствую как не повар
}
