package com.eip.red.caritathelp.Views.Subscribe;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Presenters.Subscribe.SubscribePresenter;
import com.eip.red.caritathelp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pierr on 11/01/2016.
 */

public class FirstView extends Fragment implements IFirstView, View.OnClickListener {

    private User    user;

    private SubscribePresenter  presenter;

    private EditText        firstName;
    private EditText        lastName;
    private ToggleButton    genderM;
    private ToggleButton    genderF;
    private Button          birthdayBtn;

    private DatePickerDialog    datePickerDialog;
    private List<Integer>       birthday;

    private AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((SubscribeActivity) getActivity()).getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_subscribe_first, container, false);

        // Init Presenter
        presenter = new SubscribePresenter(this, user);

        // Init UI Elements
        firstName = (EditText) view.findViewById(R.id.subscribe_firstname);
        lastName = (EditText) view.findViewById(R.id.subscribe_lastname);
        genderM = (ToggleButton) view.findViewById(R.id.subscribe_gender_male);
        genderF = (ToggleButton) view.findViewById(R.id.subscribe_gender_female);
        birthdayBtn = (Button) view.findViewById(R.id.subscribe_btn_birthday);

        // Init Listener
        firstName.setOnClickListener(this);
        lastName.setOnClickListener(this);
        genderM.setOnClickListener(this);
        genderF.setOnClickListener(this);
        birthdayBtn.setOnClickListener(this);

        // Init DatePicker listener
        initDatePicker();

        // Init DatePickerDialog

        // Init Dialog
        dialog = new AlertDialog.Builder(this.getContext()).create();

        // Init Birthday if non null
        String birthday = user.getBirthday();
        if (birthday != null) {
            String newStr = "Date de naissance " + birthday;
            birthdayBtn.setText(newStr);
        }

        return (view);
    }

    private void initDatePicker() {
        // Init Birthday Container
        birthday = new ArrayList<>();

        // Init Date values
        Calendar    calendar = Calendar.getInstance();
        int         year = calendar.get(Calendar.YEAR);
        int         month = calendar.get(Calendar.MONTH);
        int         day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                // Set Birthday container
                birthday.add(0, year);
                birthday.add(1, month);
                birthday.add(2, day);

                // Substring Title button + birthday
                String birthday = "Date de naissance : ";

                // Init Day
                if (day < 10)
                    birthday += "0" + String.valueOf(day);
                else
                    birthday += String.valueOf(day);

                // Init Month
                if (month < 10)
                    birthday += "/0" + String.valueOf(month);
                else
                    birthday += "/" + String.valueOf(month);

                // Init Year
                birthday += "/" + String.valueOf(year);

                // Set Text Button
                birthdayBtn.setText(birthday);
            }
        };

        datePickerDialog = new DatePickerDialog(this.getContext(), myDateListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribe_gender_male:
                genderM.setChecked(true);
                genderF.setChecked(false);
                break;
            case R.id.subscribe_gender_female:
                genderF.setChecked(true);
                genderM.setChecked(false);
                break;
            case R.id.subscribe_btn_birthday:
                datePickerDialog.show();
                break;
        }
    }

    public void showDialog(String message) {
        datePickerDialog.dismiss();

        // Display a dialog box
        dialog.setCancelable(true);
        dialog.setMessage(message);
        dialog.show();

        // Init Text Button
        birthdayBtn.setText(R.string.subscribe_btn_birthday);
    }

    @Override
    public void setFirstNameError(String error) {
        firstName.setError(error);
    }

    @Override
    public void setLastNameError(String error) {
        lastName.setError(error);
    }

    @Override
    public void setBirthDateError(String error) {
        birthdayBtn.setError(error);
    }

    public String getFirstName() {
        return (firstName.getText().toString());
    }

    public String getLastName() {
        return (lastName.getText().toString());
    }

    public String getGender() {
        if (genderM.isChecked())
            return ("m");
        return ("f");
    }

    public List<Integer> getBirthday() {
        return (birthday);
    }

    public SubscribePresenter getPresenter() {
        return presenter;
    }
}
