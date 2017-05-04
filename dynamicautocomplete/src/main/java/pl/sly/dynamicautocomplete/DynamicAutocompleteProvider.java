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

import android.widget.Filter;
import java.util.Collection;

/**
 * Interface definition for a data provider. Operations will be invoked asynchronously
 * by calling filter(CharSequence). When this method is called, a filtering request is posted
 * in a request queue and processed later. Any call to one of these methods will cancel any previous
 * non-executed filtering request.
 *
 * @param <T> type of the {@link Collection}.
 * @author Sylwester Sokolowski
 */
public interface DynamicAutocompleteProvider<T> {
    /**
     * Provides data according to the constraint. Invoked asynchronously in {@link Filter} thread.
     *
     * @param constraint used to provide the data.
     * @return collection of items.
     */
    Collection<T> provideDynamicAutocompleteItems(String constraint);
}