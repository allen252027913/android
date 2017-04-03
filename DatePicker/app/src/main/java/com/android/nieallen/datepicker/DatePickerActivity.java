package com.android.nieallen.datepicker;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerActivity extends AppCompatActivity {

    private DatePicker mDatePicker;
    private TextView mStartTimeTip;
    private TextView mEndTimeTip;
    private TextView mStartTime;
    private TextView mEndTime;
    private TextView mStartTimeMore;
    private TextView mEndTimeMore;
    private Calendar c = Calendar.getInstance();
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private boolean mIsStartTimeSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mDatePicker = (DatePicker) findViewById(R.id.my_data_picker);
        mStartTimeTip = (TextView) findViewById(R.id.tv_start_time_tip);
        mStartTime = (TextView) findViewById(R.id.tv_start_time);
        mStartTime.setTextColor(getResources().getColor(R.color.colorAccent));
        mStartTimeTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsStartTimeSelected = true;
                mStartTime.setTextColor(getResources().getColor(R.color.colorAccent));
                mEndTime.setTextColor(getResources().getColor(R.color.colorPrimary));
                new TimePickerDialog(DatePickerActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int day, int minute) {
                                setTime(mStartTimeMore, day+":"+minute);
                            }
                        }, mHour, mMinute, false).show();
            }
        });
        mStartTimeMore = (TextView) findViewById(R.id.tv_start_time_more);
        mEndTimeTip = (TextView) findViewById(R.id.tv_end_time_tip);
        mEndTime = (TextView) findViewById(R.id.tv_end_time);
        mEndTimeTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsStartTimeSelected = false;
                mStartTime.setTextColor(getResources().getColor(R.color.colorPrimary));
                mEndTime.setTextColor(getResources().getColor(R.color.colorAccent));
                new TimePickerDialog(DatePickerActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int day, int minute) {
                                setTime(mEndTimeMore, day+":"+minute);
                            }
                        }, mHour, mMinute, false).show();
            }
        });
        mEndTimeMore = (TextView) findViewById(R.id.tv_end_time_more);
    }

    private void initData() {
        // 获取当前的年、月、日、小时、分钟
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mDatePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;
                Date date = new Date(mYear, mMonth, mDay);
                if (mIsStartTimeSelected) {
                    setDate(mStartTime, date);
                } else {
                    setDate(mEndTime, date);
                }

            }
        });

    }

    private void setDate(TextView textView, Date date) {
        textView.setText(date.toString());
    }

    private void setTime(TextView textView, String s) {
        textView.setText(s);
    }
}
