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
package pl.sly.dynamicautocomplete.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import pl.sly.examples.dynamic.autocomplete.R;
import pl.sly.dynamicautocomplete.example.activity.dictionary.ExtendedDictionaryActivity;
import pl.sly.dynamicautocomplete.example.activity.dictionary.SimpleDictionaryActivity;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String TAG = "DynamicAutocompleteExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (ExamplesActivitiesEnum examplesActivitiesEnum : ExamplesActivitiesEnum.values()) {
            Button button = new Button(getApplicationContext());
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(getString(examplesActivitiesEnum.getNameStringId()));
            button.setTag(examplesActivitiesEnum.getActivity());
            button.setOnClickListener(this);

            linearLayout.addView(button);
        }

        setContentView(linearLayout);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button button = (Button) v;
            
            if (button.getTag() != null) {
                Class<? extends Activity> clazz = (Class<? extends Activity>) button.getTag();
                Intent intent = new Intent(this, clazz);
                startActivity(intent);
            }
        }
    }

    private enum ExamplesActivitiesEnum {
        SIMPLE_DICTIONARY(R.string.simple_dictionary, SimpleDictionaryActivity.class),
        EXTENDED_DICTIONARY(R.string.extended_dictionary, ExtendedDictionaryActivity.class);

        ExamplesActivitiesEnum(int nameStringId, Class<? extends Activity> activity) {
            this.nameStringId = nameStringId;
            this.activity = activity;
        }

        public Class<? extends Activity> getActivity() {
            return activity;
        }

        public int getNameStringId() {
            return nameStringId;
        }

        private Class<? extends Activity> activity;
        private int nameStringId;
    }
}
