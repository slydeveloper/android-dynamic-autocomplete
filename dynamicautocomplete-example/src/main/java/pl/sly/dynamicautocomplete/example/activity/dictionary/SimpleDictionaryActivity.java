/*
 * Copyright (C) 2017 Sylwester Sokolowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.sly.dynamicautocomplete.example.activity.dictionary;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import pl.sly.examples.dynamic.autocomplete.R;
import pl.sly.dynamicautocomplete.example.adapter.SimpleDictionaryAutocompleteAdapter;
import pl.sly.dynamicautocomplete.DynamicAutoCompleteTextView;
import pl.sly.dynamicautocomplete.OnDynamicAutocompleteListener;

public class SimpleDictionaryActivity extends ActionBarActivity implements
        OnDynamicAutocompleteListener {
	private static final int THRESHOLD = 2;
	private DynamicAutoCompleteTextView mDictionaryAutoCompleteTextView;
	private ProgressBar mDictionaryAutoCompleteProgressBar;
	private SimpleDictionaryAutocompleteAdapter mSimpleDictionaryAutocompleteAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_dictionary);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		mSimpleDictionaryAutocompleteAdapter = new SimpleDictionaryAutocompleteAdapter(
				getApplicationContext(), android.R.layout.simple_list_item_1);

		mDictionaryAutoCompleteTextView = (DynamicAutoCompleteTextView) findViewById(R.id.dictionaryAutoCompleteTextView);
		mDictionaryAutoCompleteTextView.setOnDynamicAutocompleteListener(this);
		mDictionaryAutoCompleteTextView.setAdapter(mSimpleDictionaryAutocompleteAdapter);
		mDictionaryAutoCompleteTextView.setThreshold(THRESHOLD);
		
		mDictionaryAutoCompleteProgressBar = (ProgressBar) findViewById(R.id.dictionaryAutoCompleteProgressBar);
		mDictionaryAutoCompleteProgressBar.setVisibility(View.INVISIBLE);
	}

    @Override
    protected void onResume() {
        super.onResume();
        mDictionaryAutoCompleteTextView.dismissDropDown();
    }

    @Override
	protected void onPause() {
		super.onPause();
		mDictionaryAutoCompleteTextView.dismissDropDown();
	}

	@Override
	public void onDynamicAutocompleteStart(AutoCompleteTextView view) {
		if (view == mDictionaryAutoCompleteTextView) {
			mDictionaryAutoCompleteProgressBar.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onDynamicAutocompleteStop(AutoCompleteTextView view) {
		if (view == mDictionaryAutoCompleteTextView) {
			mDictionaryAutoCompleteProgressBar.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}