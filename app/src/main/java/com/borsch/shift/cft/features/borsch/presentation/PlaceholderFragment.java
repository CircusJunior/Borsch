package com.borsch.shift.cft.features.borsch.presentation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.borsch.shift.cft.R;
import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.MvpView;
import com.borsch.shift.cft.features.borsch.domain.model.ChangeProduct;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Request;
import com.borsch.shift.cft.features.borsch.domain.model.Success;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment implements MvpView {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private FridgeAdapter fridgeAdapter;
    private RecipeAdapter recipeAdapter;
    private RequestAdapter requestAdapter;
    private ContentPresenter contentPresenter;
    private Spinner fridgeSpinner;
    private Spinner recipeSpinner;
    private Button fridgeAddProduct;
    private ArrayList<String> product;
    private Button addRecipe;
    private Button createRecipe;
    private Button addRecipeProduct;
    private RecipePresenter recipePresenter;
    private RequestPresenter requestPresenter;


    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments().getInt(ARG_SECTION_NUMBER) == 0) {
            View rootView = inflater.inflate(R.layout.fridge_account, container, false);
            return rootView;
        } else {
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                View rootView = inflater.inflate(R.layout.recipe_account, container, false);
                return rootView;
            } else {
                View rootView = inflater.inflate(R.layout.request_account, container, false);
             //   RecyclerView requestRecyclerView = (RecyclerView) rootView.findViewById(R.id.fridge_recycler);
                return rootView;
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view.getId() == R.id.fridge_account) {
            RecyclerView fridgeRecyclerView = (RecyclerView) view.findViewById(R.id.fridge_recycler);

            fridgeAdapter = new FridgeAdapter(getContext(), new FridgeAdapter.SelectProductListener() {
                @Override
                public void onProductSelect(Product product) {
                    // contentPresenter.onViewReady();
                }

                @Override
                public void onProductLongClick(Product product) {
                    //contentPresenter.onProductLongClicked(product);
                }

                @Override
                public void onProductClickDelete(Product product) {
                    contentPresenter.onProductClickDelete(product);
                }
            });

            fridgeRecyclerView.setAdapter(fridgeAdapter);
            fridgeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            contentPresenter = ContentPresenterFactory.createContentPresenter(getContext());
            contentPresenter.attachView(this);
            contentPresenter.onViewReady();

            fridgeSpinner = (Spinner)view.findViewById(R.id.fridge_spinner);
            contentPresenter.fridgeChangeProduct();

            fridgeAddProduct = (Button)view.findViewById(R.id.ftridge_add_product);
            fridgeAddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contentPresenter.fridgeAddProduct(fridgeSpinner.getSelectedItemPosition());
                }
            });
        }
        if (view.getId() == R.id.recipe_account){

            RecyclerView recipeRecyclerView = (RecyclerView) view.findViewById(R.id.recipe_recycler);

            recipeAdapter = new RecipeAdapter(getContext(), new RecipeAdapter.SelectProductListener() {
                @Override
                public void onProductSelect(Product product) {
                    // contentPresenter.onViewReady();
                }

                @Override
                public void onProductLongClick(Product product) {
                    //contentPresenter.onProductLongClicked(product);
                }

                @Override
                public void onProductClickDelete(Product product) {
                 //   contentPresenter.onProductClickDelete(product);
                }
            });

            recipeRecyclerView.setAdapter(recipeAdapter);
            recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            recipePresenter = ContentPresenterFactory.createRecipePresenter(getContext());
            recipePresenter.attachView(this);
            recipePresenter.onViewReady();

            final LinearLayout newRecipe = (LinearLayout) view.findViewById(R.id.new_recipe);

            recipeSpinner = (Spinner)view.findViewById(R.id.new_recipe_product);
            recipePresenter.recipeChangeProduct();

            addRecipe = (Button) view.findViewById(R.id.recipe_add);
            addRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addRecipe.setVisibility(View.GONE);
                    newRecipe.setVisibility(View.VISIBLE);
                }
            });

            addRecipeProduct = (Button) view.findViewById(R.id.new_recipe_add_product);
            addRecipeProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recipePresenter.recipeAddProduct(recipeSpinner.getSelectedItemPosition());
                }
            });
        }
        if (view.getId() == R.id.request_account){

            RecyclerView requestRecyclerView = (RecyclerView) view.findViewById(R.id.request_recycler);

            requestAdapter = new RequestAdapter(getContext(), new RequestAdapter.SelectRequestListener() {
                @Override
                public void onRequestSelect(Request request) {

                }

                @Override
                public void onRequestLongClick(Request request) {

                }

                @Override
                public void onRequestClickDelete(Request request) {

                }
            });

            requestRecyclerView.setAdapter(requestAdapter);
            requestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            requestPresenter = ContentPresenterFactory.createRequestPresenter(getContext());
            requestPresenter.attachView(this);
            requestPresenter.onViewReady();

            final LinearLayout newRecipe = (LinearLayout) view.findViewById(R.id.new_recipe);

            recipeSpinner = (Spinner)view.findViewById(R.id.new_recipe_product);
            recipePresenter.recipeChangeProduct();

            addRecipe = (Button) view.findViewById(R.id.recipe_add);
            addRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addRecipe.setVisibility(View.GONE);
                    newRecipe.setVisibility(View.VISIBLE);
                }
            });

            addRecipeProduct = (Button) view.findViewById(R.id.new_recipe_add_product);
            addRecipeProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recipePresenter.recipeAddProduct(recipeSpinner.getSelectedItemPosition());
                }
            });
        }
    }

    public void showFridge(ArrayList<Product> result) {
        fridgeAdapter.setFridge(result);
    }

    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showRecipe(ArrayList<Product> result) {
        recipeAdapter.setRecipe(result);
    }

    public void showFridgeSpiner(ArrayList<Food> result) {
        product = new ArrayList<String>();
        for(int i=0; i<result.size(); i++) {
            product.add(result.get(i). getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (getContext(), android.R.layout.simple_spinner_item, product);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fridgeSpinner.setAdapter(adapter);
    }

    public void showRecipeSpiner(ArrayList<Food> result) {
        product = new ArrayList<String>();
        for(int i=0; i<result.size(); i++) {
            product.add(result.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (getContext(), android.R.layout.simple_spinner_item, product);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recipeSpinner.setAdapter(adapter);
    }

    public void showRequest(ArrayList<Request> result) {
        fridgeAdapter.setRequest(result);
    }
}
