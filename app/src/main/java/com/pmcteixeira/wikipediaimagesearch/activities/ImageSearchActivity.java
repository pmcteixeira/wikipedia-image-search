package com.pmcteixeira.wikipediaimagesearch.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridView;

import com.pmcteixeira.wikipediaimagesearch.R;
import com.pmcteixeira.wikipediaimagesearch.adapters.ImageGridViewAdapter;
import com.pmcteixeira.wikipediaimagesearch.backend.RetrofitService;
import com.pmcteixeira.wikipediaimagesearch.backend.WikipediaImagesClient;
import com.pmcteixeira.wikipediaimagesearch.backend.pojos.Page;
import com.pmcteixeira.wikipediaimagesearch.backend.pojos.Result;
import com.pmcteixeira.wikipediaimagesearch.backend.pojos.WikiImgsResponse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ImageSearchActivity extends ActionBarActivity {

	private final String TAG = ImageSearchActivity.class.getSimpleName();

	private final String QUERY_STATE_KEY = "query";
	private final int TEXT_CHANGED_MIN_DELAY = 400;  // ms
	private final int IMAGE_RESULT_COUNT = 50;

	private Toolbar mToolbar;
	private EditText mEditText;
	private GridView mGridView;
	private ImageGridViewAdapter mImageGridViewAdapter;
	private WikipediaImagesClient mWikiClient;
	private List<Page> mPageListResult;
	private long mTextChangedTimeStamp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);

		// Create a REST adapter which points the Wikipedia API endpoint.
		mWikiClient = new RetrofitService().createService(WikipediaImagesClient.class);

		widgetsInit();

		// Maintain result after rotate
		if(savedInstanceState != null) {
			String query = savedInstanceState.getString(QUERY_STATE_KEY, null);
			if(query != null) {
				performImageQuery(query);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Save all appropriate fragment state.
	 *
	 * @param outState
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(QUERY_STATE_KEY, mEditText.getText().toString());
	}

	private void performImageQuery(String query) {
		mWikiClient.getImages(query, IMAGE_RESULT_COUNT, mWikiImageSearchCallback);
	}

	protected void widgetsInit() {

		// configure toolbar stuff
		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		mImageGridViewAdapter = new ImageGridViewAdapter(getBaseContext());

		mGridView = (GridView) findViewById(R.id.gridView);
		mGridView.setAdapter(mImageGridViewAdapter);

		mEditText = (EditText) findViewById(R.id.searchEditText);
		mEditText.addTextChangedListener(mEditTextWatcher);
	}

	Callback<WikiImgsResponse> mWikiImageSearchCallback = new Callback<WikiImgsResponse>() {
		@Override
		public void success(WikiImgsResponse imagePagesResponse, Response response) {

			Result query = imagePagesResponse.getQuery();
			if(query != null) {
				mPageListResult = query.getPages().getPageList();

				// Sort pages by index
				Collections.sort(mPageListResult, new Comparator<Page>() {
					public int compare(Page o1, Page o2) {
						return o1.getIndex().compareTo(o2.getIndex());
					}
				});

				mImageGridViewAdapter.updateList(mPageListResult);
				mImageGridViewAdapter.notifyDataSetChanged();

			} else {
				mPageListResult = null;
			}
		}

		@Override
		public void failure(RetrofitError error) {
			Log.i(TAG, error.toString());
		}
	};

	protected TextWatcher mEditTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {

			// First time writing
			if(mTextChangedTimeStamp == 0) {
				mTextChangedTimeStamp = System.currentTimeMillis();
			}

			if(System.currentTimeMillis() - mTextChangedTimeStamp >= TEXT_CHANGED_MIN_DELAY) {
				performImageQuery(s.toString());
			}
		}
	};
}
