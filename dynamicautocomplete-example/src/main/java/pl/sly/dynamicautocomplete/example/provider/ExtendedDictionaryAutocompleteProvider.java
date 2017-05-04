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
package pl.sly.dynamicautocomplete.example.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

import pl.sly.dynamicautocomplete.example.activity.MainActivity;
import pl.sly.dynamicautocomplete.example.model.CountryModel;
import pl.sly.dynamicautocomplete.DynamicAutocompleteProvider;
import timber.log.Timber;

public class ExtendedDictionaryAutocompleteProvider extends AbstractProvider implements DynamicAutocompleteProvider<CountryModel> {

    private static final String REST_URL = "http://sstgadh.wirt09.biznes-host.pl/rest/api/country/%s";

    @Override
    public Collection<CountryModel> provideDynamicAutocompleteItems(String constraint) {
        Timber.tag(MainActivity.TAG);
        Timber.i("Constraint: " + constraint);

        String queryUrl = buildQueryUrl(REST_URL, constraint);
        String json = invokeRestQuery(queryUrl);

        Type type = new TypeToken<Collection<CountryModel>>(){}.getType();
        return new Gson().fromJson(json, type);
    }
}