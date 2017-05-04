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

import android.support.v4.util.LruCache;

/**
 * Simple implementation of custom LRU Cache.
 * Allows to cache a filtering result.
 *
 * @author Sylwester Sokolowski
 */
public class FilterCache {

    private static final int CACHE_SIZE = 5 * 1024;
    private LruCache<String, Object> lruCache;

    /**
     * Constructor.
     */
    public FilterCache() {
        lruCache = new LruCache<String, Object>(CACHE_SIZE);
    }

    /**
     * Get LRU cache instance.
     *
     * @return instance of LRU cache.
     */
    public LruCache<String, Object> getLruCache() {
        return lruCache;
    }
}