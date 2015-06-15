package com.example.jizhang1;

import java.lang.reflect.Field;
import java.net.CacheRequest;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources.NotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {

	SQLiteDatabase db;
	private int type;
	
	NumberPicker[] numberPicker;
	Button button;
	RadioGroup radioGroup;
	RadioButton[] radioButton;
	customEditText editText;
	customDialog cDialog;
	dataBaseRecord dbrecord;
	DBhelper dBhelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		numberPicker = new NumberPicker[6];
		numberPicker[0] = (NumberPicker)findViewById(R.id.numberpicker1);
		numberPicker[1] = (NumberPicker)findViewById(R.id.numberpicker2);
		numberPicker[2] = (NumberPicker)findViewById(R.id.numberpicker3);
		numberPicker[3] = (NumberPicker)findViewById(R.id.numberpicker4);
		numberPicker[4] = (NumberPicker)findViewById(R.id.numberpicker5);
		
		button = (Button)findViewById(R.id.okaybutton);
		button.setOnClickListener(clickListener);
		
		radioGroup = (RadioGroup)findViewById(R.id.radiogroup1);
		
		radioButton = new RadioButton[6];
		radioButton[0] = (RadioButton)findViewById(R.id.radiobutton1);
		radioButton[1] = (RadioButton)findViewById(R.id.radiobutton2);
		radioButton[2] = (RadioButton)findViewById(R.id.radiobutton3);
		radioButton[3] = (RadioButton)findViewById(R.id.radiobutton4);
		radioButton[4] = (RadioButton)findViewById(R.id.radiobutton5);
		radioButton[5] = (RadioButton)findViewById(R.id.radiobutton6);
		
		for(int i = 0; i < 6; i++){
			Drawable[] drawable = radioButton[i].getCompoundDrawables();
			drawable[1].setBounds(0, 0, 100, 100);
			radioButton[i].setCompoundDrawables(drawable[0], drawable[1], drawable[2], drawable[3]);
		}
		editText = (customEditText)findViewById(R.id.edittext1);
		for(int i = 0; i < 5 ; i++){
			numberPicker[i].setMaxValue(9);
			numberPicker[i].setMinValue(0);
			setDatePickerDividerColor(numberPicker[i]);
			numberPicker[i].setValue(0);
		}
		
		//打开或创建数据库
		db = openOrCreateDatabase("info.db", Context.MODE_PRIVATE, null);

		db.execSQL("DROP TABLE IF EXISTS info");
		dBhelper = new DBhelper(db);
		dBhelper.CreatTable();
		
		//设置radiogroup监听事件
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO 自动生成的方法存根
				int radioButtonId = group.getCheckedRadioButtonId();
				switch (radioButtonId) {
				case R.id.radiobutton1:
					type = dataBaseRecord.SHOPPING;
					break;
				case R.id.radiobutton2:
					type = dataBaseRecord.TRAFFIC;
					break;
				case R.id.radiobutton3:
					type = dataBaseRecord.DIET;
					break;
				case R.id.radiobutton4:
					type = dataBaseRecord.LIFE;
					break;
				case R.id.radiobutton5:
					type = dataBaseRecord.RELATION;
					break;
				case R.id.radiobutton6:
					type = dataBaseRecord.OTHER;
					break;	
				default:
					break;
				}
			}
		});
	}
	
	
	OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			int id = v.getId();
			switch (id) {
			case R.id.okaybutton:
				dbrecord = new dataBaseRecord();
				dbrecord.setMoney(getmoney());
				dbrecord.setType(type);
				dbrecord.setComment(editText.getText().toString());
				cDialog = new customDialog(v.getContext(), dBhelper);
				cDialog.dbRecord = dbrecord;
				cDialog.show();
				cDialog.getCurrentFocus();
				break;
			default://取消按钮
				break;
			}
			
		}
	};
	
	protected double getmoney() {
		int qian = numberPicker[0].getValue();
		int bai = numberPicker[1].getValue();
		int shi = numberPicker[2].getValue();
		int ge = numberPicker[3].getValue();
		int jiao = numberPicker[4].getValue();
		double tmp = 0.1 * (double)jiao;
		double value = qian * 1000 + bai * 100 + shi * 10 + ge + tmp;
		return value;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//设置numberpicker分割线颜色
	private void setDatePickerDividerColor(NumberPicker picker){
             
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(Color.RED));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
	
	
}

