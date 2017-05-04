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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.sly.examples.dynamic.autocomplete.R;
import pl.sly.dynamicautocomplete.example.filter.ExtendedDictionaryAutocompleteFilter;
import pl.sly.dynamicautocomplete.example.model.CountryModel;
import pl.sly.dynamicautocomplete.example.provider.ExtendedDictionaryAutocompleteProvider;
import pl.sly.dynamicautocomplete.OnDynamicAutocompleteFilterListener;

public class ExtendedDictionaryAutocompleteAdapter extends ArrayAdapter<CountryModel> implements
        OnDynamicAutocompleteFilterListener<CountryModel> {

    private List<CountryModel> mListItems;
    private ExtendedDictionaryAutocompleteProvider mExtendedDictionaryAutocompleteProvider;
    private ExtendedDictionaryAutocompleteFilter mDictionaryAutocompleteFilter;
    private LayoutInflater mLayoutInflater;
    private int mLayoutId;

    public ExtendedDictionaryAutocompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        mExtendedDictionaryAutocompleteProvider = new ExtendedDictionaryAutocompleteProvider();
        mDictionaryAutocompleteFilter = new ExtendedDictionaryAutocompleteFilter(mExtendedDictionaryAutocompleteProvider, this);
        mDictionaryAutocompleteFilter.useCache(true);
        mListItems = new ArrayList<>();
        mLayoutId = textViewResourceId;
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onDynamicAutocompleteFilterResult(Collection<CountryModel> result) {
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
    public CountryModel getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mLayoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.flag = (ImageView) convertView.findViewById(R.id.flagImage);
            viewHolder.name = (TextView) convertView.findViewById(R.id.countryNameText);
            viewHolder.code = (TextView) convertView.findViewById(R.id.countryCodeText);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CountryModel countryModel = getItem(position);
        Picasso.with(getContext()).load(countryModel.getFlagUrl()).into(viewHolder.flag);
        viewHolder.name.setText(countryModel.getName());
        viewHolder.code.setText(countryModel.getCode());

        return convertView;
    }

    /**
     * Holder for list item.
     */
    private static class ViewHolder {
        private ImageView flag;
        private TextView name;
        private TextView code;
    }
}