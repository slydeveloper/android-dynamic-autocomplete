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

import com.github.kevinsawicki.http.HttpRequest;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

/**
 * Abstract class for common methods of data provider.
 */
public abstract class AbstractProvider {

    private static final String URF_8 = "UTF-8";
    private static final String EMPTY_STRING = "";

    /**
     * Build valid API URL enriched with query.
     *
     * @param baseUrl base
     * @param searchQuery
     * @return
     */
    public String buildQueryUrl(final String baseUrl, String searchQuery) {
        try {
            searchQuery = URLEncoder.encode(searchQuery.toLowerCase(Locale.getDefault()), URF_8);
            URL url = new URI(String.format(baseUrl, searchQuery)).toURL();

            return url.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return EMPTY_STRING;
    }

    /**
     * Make an HTTP request for query.
     *
     * @param url of API service.
     * @return result of HTTP query.
     */
    public String invokeRestQuery(String url) {
        HttpRequest request = HttpRequest.get(url);

        if (request.ok()) {
            return request.body();
        }

        return EMPTY_STRING;
    }
}
