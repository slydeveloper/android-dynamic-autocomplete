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
package pl.sly.dynamicautocomplete.example.filter;

import pl.sly.dynamicautocomplete.example.model.CountryModel;
import pl.sly.dynamicautocomplete.DynamicAutocompleteFilter;
import pl.sly.dynamicautocomplete.DynamicAutocompleteProvider;
import pl.sly.dynamicautocomplete.OnDynamicAutocompleteFilterListener;

/**
 * Instance of {@link DynamicAutocompleteFilter} for convert {@link CountryModel} to
 * {@link CharSequence} item.
 */
public class ExtendedDictionaryAutocompleteFilter extends DynamicAutocompleteFilter<CountryModel> {

    public ExtendedDictionaryAutocompleteFilter(
            DynamicAutocompleteProvider dynamicAutocompleteProvider,
            OnDynamicAutocompleteFilterListener onDynamicAutocompleteFilterListener) {
        super(dynamicAutocompleteProvider, onDynamicAutocompleteFilterListener);
    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        if (resultValue instanceof CountryModel) {
            CountryModel countryModel = (CountryModel) resultValue;

            return countryModel.getName();
        }

        return super.convertResultToString(resultValue);
    }
}
