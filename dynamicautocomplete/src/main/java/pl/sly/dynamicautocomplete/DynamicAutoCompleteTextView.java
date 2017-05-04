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
package pl.sly.dynamicautocomplete;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.ListAdapter;

/**
 * Customized version of AutoCompleteTextView. An editable text view that shows completion
 * suggestions automatically while the user is typing. Based on filtering mechanism which provides
 * dynamic update of content.
 *
 * @author Sylwester Sokolowski
 */
public class DynamicAutoCompleteTextView extends AutoCompleteTextView {

    private OnDynamicAutocompleteListener mOnDynamicAutocompleteListener;

    /**
     * Constructor.
     */
    public DynamicAutoCompleteTextView(Context context) {
        super(context);
    }

    /**
     * Constructor.
     */
    public DynamicAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     */
    public DynamicAutoCompleteTextView(Context context, AttributeSet attrs,
                                       int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Register a callback to be invoked when this view is clicked. If this view is not clickable,
     * it becomes clickable.
     *
     * @param onDynamicAutocompleteListener registered as a callback invoked when autocomplete
     * operation has been finished.
     */
    public void setOnDynamicAutocompleteListener(
            OnDynamicAutocompleteListener onDynamicAutocompleteListener) {
        this.mOnDynamicAutocompleteListener = onDynamicAutocompleteListener;
    }

    @Override
    public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            if (getFilter() instanceof DynamicAutocompleteFilter) {
                DynamicAutocompleteFilter<T> filter = (DynamicAutocompleteFilter<T>) getFilter();
                filter.setDynamicAutoCompleteTextView(this);
            }
        }
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        super.performFiltering(text, keyCode);
        if (mOnDynamicAutocompleteListener != null) {
            mOnDynamicAutocompleteListener.onDynamicAutocompleteStart(this);
        }
    }

    @Override
    public void onFilterComplete(int count) {
        super.onFilterComplete(count);
        if (mOnDynamicAutocompleteListener != null) {
            mOnDynamicAutocompleteListener.onDynamicAutocompleteStop(this);
        }
    }
}