package com.arturoguillen.socialpicture.view.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.arturoguillen.socialpicture.R;
import com.arturoguillen.socialpicture.di.component.ActivityComponent;
import com.arturoguillen.socialpicture.entities.client.twitter.LoginRequest;
import com.arturoguillen.socialpicture.presenter.SearchPresenter;
import com.arturoguillen.socialpicture.utils.InstantAutoComplete;
import com.arturoguillen.socialpicture.view.InjectedActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by arturo.guillen on 04/09/2017.
 */

public class SearchActivity extends InjectedActivity implements SearchView {

    private static final String EXTRA_LOGIN_REQUEST = "EXTRA_LOGIN_REQUEST";
    private static final String RECYCLERVIEW_STATE = "RECYCLERVIEW_STATE";
    private static final String RECYCLEVIEW_CONTENT = "RECYCLEVIEW_CONTENT";

    @Inject
    SearchPresenter presenter;

    @Inject
    Picasso picasso;

    @BindView(R.id.actv_search)
    InstantAutoComplete searchTextView;

    @BindView(R.id.rv_list_images)
    RecyclerView listImages;

    private ArrayAdapter<String> searchAdapter;
    private List<String> searchTerms;
    private List<String> imageUrls;

    public static Intent createIntent(Context context, LoginRequest loginRequest) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_LOGIN_REQUEST, loginRequest);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchTerms = new ArrayList<>();
        imageUrls = new ArrayList<>();

        searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, searchTerms);

        searchTextView.setAdapter(searchAdapter);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        ImagesAdapter imagesAdapter = new ImagesAdapter(picasso, imageUrls);
        listImages.setAdapter(imagesAdapter);
        listImages.setLayoutManager(new GridLayoutManager(this, 2));
        ((SimpleItemAnimator) listImages.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    @OnClick(R.id.bt_search)
    public void searchOnClick() {
        hideSoftKeyboard();
        String searchTerm = String.valueOf(searchTextView.getText());

        addItemToHistory(searchTerm);
        searchTextView.setText("");
        presenter.search(searchTerm);
    }

    private void addItemToHistory(String searchTerm) {
        searchTerms.add(0, searchTerm);
        searchAdapter.clear();
        searchAdapter.addAll(searchTerms);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    protected void injectComponent(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void searchOK(List<String> imagesUrls) {
        ImagesAdapter adapter = (ImagesAdapter) listImages.getAdapter();
        adapter.appendImages(imagesUrls);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            ImagesAdapter adapter = (ImagesAdapter) listImages.getAdapter();
            adapter.appendImages(savedInstanceState.getStringArrayList(RECYCLEVIEW_CONTENT));
            listImages.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(RECYCLERVIEW_STATE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECYCLERVIEW_STATE, listImages.getLayoutManager().onSaveInstanceState());
        outState.putStringArrayList(RECYCLEVIEW_CONTENT, (ArrayList<String>) imageUrls);
    }

    @Override
    public void searchNOK(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
