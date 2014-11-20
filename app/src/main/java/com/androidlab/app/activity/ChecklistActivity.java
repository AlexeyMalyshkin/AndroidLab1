package com.androidlab.app.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidlab.app.R;

/**
 * @author appsrox.com
 *
 */
public class ChecklistActivity {
	
	//private static final String TAG = "ChecklistActivity";

    public void onCreate(Bundle savedInstanceState) {

    }
	
	public void onClick(View v) {

		
		LinearLayout checkitemLL;
        switch (v.getId()) {
            case R.id.action_add:
//                checkitemLL = (LinearLayout) inflater.inflate(R.layout.row_checkitem, null);
//                EditText itemEdit = (EditText) checkitemLL.findViewById(R.id.item_et);
//                itemEdit.setTypeface(font);
//                checklistLL.addView(checkitemLL);
                break;

            case R.id.operationDivisionButton:
//                checkitemLL = (LinearLayout) v.getParent();
//                TextView itemId = (TextView) checkitemLL.findViewById(R.id.item_id);
//                if (!TextUtils.isEmpty(itemId.getText()))
//                    new CheckItem(Long.parseLong(itemId.getText().toString())).delete(db);
//                checklistLL.removeView(checkitemLL);
                break;
        }
	}


	protected void reset() {


	}
	
	protected void persist() {

	}
	


}
