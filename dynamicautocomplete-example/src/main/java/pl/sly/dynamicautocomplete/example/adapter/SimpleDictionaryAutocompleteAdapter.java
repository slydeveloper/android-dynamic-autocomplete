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
package pl.sly.dynamicautocomplete.example.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.sly.dynamicautocomplete.example.provider.SimpleDictionaryAutocompleteProvider;
import pl.sly.dynamicautocomplete.DynamicAutocompleteFilter;
import pl.sly.dynamicautocomplete.OnDynamicAutocompleteFilterListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class SimpleDictionaryAutocompleteAdapter extends ArrayAdapter<String> implements
		OnDynamicAutocompleteFilterListener<String> {

    private List<String> mListItems;
	private SimpleDictionaryAutocompleteProvider mSimpleDictionaryAutocompleteProvider;
	private DynamicAutocompleteFilter<String> mDictionaryAutocompleteFilter;
	private LayoutInflater mLayoutInflater;
	private int mLayoutId;
	
	public SimpleDictionaryAutocompleteAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		mSimpleDictionaryAutocompleteProvider = new SimpleDictionaryAutocompleteProvider();
		mDictionaryAutocompleteFilter = new DynamicAutocompleteFilter<>(mSimpleDictionaryAutocompleteProvider, this);
		mDictionaryAutocompleteFilter.useCache(true);
		mListItems = new ArrayList<>();
		mLayoutId = textViewResourceId;
		mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void onDynamicAutocompleteFilterResult(Collection<String> result) {
		mListItems.clear();
		mListItems.addAll(result);
		notifyDataSetChanged();
	}
	
	@Override
	public Filter getFilter() {
		return mDictionaryAutocompleteFilter;
	}
	
	@Override
	public int getCount() {
		return mListItems.size();
	}

	@Override
	public void clear() {
		mListItems.clear();
	}

	@Override
	public String getItem(int position) {
		return mListItems.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mLayoutId, null);

            viewHolder = new ViewHolder();
            viewHolder.word = (TextView) convertView
                    .findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.word.setText(getItem(position));

		return convertView;
	}

    private static class ViewHolder {
        private TextView word;
    }
}