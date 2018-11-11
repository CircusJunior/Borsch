package com.borsch.shift.cft.features.borsch.presentation;

import android.content.Context;

import com.borsch.shift.cft.App;
import com.borsch.shift.cft.features.borsch.data.ContentApi;
import com.borsch.shift.cft.features.borsch.data.ContentDataSource;
import com.borsch.shift.cft.features.borsch.data.ContentDataSourceImpl;
import com.borsch.shift.cft.features.borsch.data.ContentRepository;
import com.borsch.shift.cft.features.borsch.data.ContentRepositoryImpl;
import com.borsch.shift.cft.features.borsch.domain.ContentInteractor;
import com.borsch.shift.cft.features.borsch.domain.ContentInteractorImpl;

public final class ContentPresenterFactory {

    public static ContentPresenter createContentPresenter(Context context) {
        final ContentApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(ContentApi.class);

        final ContentDataSource dataSource = new ContentDataSourceImpl(api);
        final ContentRepository repository = new ContentRepositoryImpl(dataSource);
        final ContentInteractor interactor = new ContentInteractorImpl(repository);

        return new ContentPresenter(interactor);
    }

    public static RecipePresenter createRecipePresenter(Context context) {
        final ContentApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(ContentApi.class);

        final ContentDataSource dataSource = new ContentDataSourceImpl(api);
        final ContentRepository repository = new ContentRepositoryImpl(dataSource);
        final ContentInteractor interactor = new ContentInteractorImpl(repository);

        return new RecipePresenter(interactor);
    }

    public static RecipePresenter createRequestPresenter(Context context) {
        final ContentApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(ContentApi.class);

        final ContentDataSource dataSource = new ContentDataSourceImpl(api);
        final ContentRepository repository = new ContentRepositoryImpl(dataSource);
        final ContentInteractor interactor = new ContentInteractorImpl(repository);

        return new RecipePresenter(interactor);
    }
}
