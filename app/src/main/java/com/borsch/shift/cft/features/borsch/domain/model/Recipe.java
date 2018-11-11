package com.borsch.shift.cft.features.borsch.domain.model;

import com.borsch.shift.cft.features.account.domain.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public final class Recipe {
    private String idRecipe;
    private String name;
    private String idPovar;
    private ArrayList<Product> productList;
    private HashMap<String, String> finalUserList;
    private HashMap<String,HashMap<String,User>> listUsersForEachProduct;
}
