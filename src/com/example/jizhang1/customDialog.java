package com.example.jizhang1;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.R.integer;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.widget.NumberPicker.OnValueChangeListener;

public class customDialog extends Dialog {

	Context context;
	Button okaybtn;
	Button cancelbtn;
	NumberPicker[] numberPicker;
	int flag;
	dataBaseRecord dbRecord;
	DBhelper dBhelper;
	
	int daynum[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	LayoutInflater inflater;
	
	public customDialog(Context context, DBhelper dbhelper) {
		super(context);
		this.dBhelper = dbhelper;
		// TODO 自动生成的构造函数存根
		setContentView(R.layout.datapicker_layout);
		setTitle("设置日期");
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
				WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		
		this.context=context;
		numberPicker = new NumberPicker[4];
		numberPicker[1] = (NumberPicker)findViewById(R.id.numberPicker11);
		numberPicker[2] = (NumberPicker)findViewById(R.id.numberPicker22);
		numberPicker[3] = (NumberPicker)findViewById(R.id.numberPicker33);
		numberPicker[1].setMinValue(1900);
		numberPicker[1].setMaxValue(3000);
		numberPicker[2].setMinValue(1);
		numberPicker[2].setMaxValue(12);
		numberPicker[3].setMinValue(1);
		for(int i=1;i<=3;i++)
		{
			this.setDatePickerDividerColor(numberPicker[i]);
		}
		flag=0;
		numberPicker[3].setMaxValue(daynum[numberPicker[2].getValue()]);
		if(numberPicker[2].getValue()==2){
			if((numberPicker[1].getValue()%4==0&&numberPicker[1].getValue()%100!=0)
					||numberPicker[1].getValue()%400==0)
			{
				numberPicker[3].setMaxValue(29);
			}
		}
		numberPicker[1].setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO 自动生成的方法存根
				numberPicker[3].setMaxValue(daynum[numberPicker[2].getValue()]);
				if(numberPicker[2].getValue()==2){
					if((numberPicker[1].getValue()%4==0&&numberPicker[1].getValue()%100!=0)
							||numberPicker[1].getValue()%400==0)
					{
						numberPicker[3].setMaxValue(29);
					}
				}
			}
		});
		numberPicker[2].setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO 自动生成的方法存根
				numberPicker[3].setMaxValue(daynum[numberPicker[2].getValue()]);
				if(numberPicker[2].getValue()==2){
					if((numberPicker[1].getValue()%4==0&&numberPicker[1].getValue()%100!=0)
							||numberPicker[1].getValue()%400==0)
					{
						numberPicker[3].setMaxValue(29);
					}
				}
			}
		});
		init();
	}
	
	public void init(){
		gettime();
		okaybtn = (Button)findViewById(R.id.customButton1);
		cancelbtn = (Button)findViewById(R.id.customButton2);
		okaybtn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				dbRecord.setYear( numberPicker[1].getValue() );
				dbRecord.setMonth( numberPicker[2].getValue() );
				dbRecord.setDay( numberPicker[3].getValue() );
				flag = 1; 
				System.out.println(dbRecord.getMonth());
				dBhelper.dbInsert(dbRecord);
				Cursor cursor = dBhelper.db.rawQuery("SELECT * FROM info", null);
				System.out.println(cursor.getColumnIndex("MONTH"));
				cancel();
			}
		});
		cancelbtn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				flag = 2;
				cancel();
			}
		});
	}

	//更改numberpicker的分割线颜色
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
	
	private void gettime()
	{
		Calendar calendar = Calendar.getInstance();
		numberPicker[1].setValue(calendar.get(Calendar.YEAR));
		numberPicker[2].setValue(calendar.get(Calendar.MONTH));
		numberPicker[3].setValue(calendar.get(Calendar.DAY_OF_MONTH));
	}
}
