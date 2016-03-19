package com.eip.red.caritathelp.Views.Organisation.Management.OrganisationEventCreation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Presenters.Organisation.Management.EventCreation.OrganisationEventCreationPresenter;
import com.eip.red.caritathelp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventCreationView extends Fragment implements IOrganisationEventCreationView, View.OnClickListener{

    private OrganisationEventCreationPresenter presenter;

    private EditText        title;
    private TextView        beginDate;
    private TextView        endDate;
    private EditText        location;
    private EditText        description;
    private ProgressBar     progressBar;

    private DatePickerDialog    dateBeginDialog;
    private DatePickerDialog    dateEndDialog;
    private AlertDialog         dialog;

    public static OrganisationEventCreationView newInstance(int idOrganisation) {
        OrganisationEventCreationView    myFragment = new OrganisationEventCreationView();

        Bundle args = new Bundle();
        args.putInt("organisation id", idOrganisation);
        myFragment.setArguments(args);

        return (myFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network Model & Id Organisation
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        int     organisationId = getArguments().getInt("organisation id");

        // Init Presenter
        presenter = new OrganisationEventCreationPresenter(this, network, organisationId);

        // Init Dialog
        dialog = new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .create();

        // Init Date Picker Dialog
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.FRANCE);
        Calendar newCalendar = Calendar.getInstance();

        dateBeginDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                String date = "Début : " + dateFormatter.format(newDate.getTime());
                beginDate.setText(date);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        dateEndDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                String date = "Fin : " + dateFormatter.format(newDate.getTime());
                endDate.setText(date);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_organisation_management_event_creation, container, false);

        // Set ToolBar
        ((MainActivity) getActivity()).getToolBar().update("Création événement", true, false);

        // Init UI Elements
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        title = (EditText) view.findViewById(R.id.title);
        beginDate = (TextView) view.findViewById(R.id.date_begin);
        endDate = (TextView) view.findViewById(R.id.date_end);
        location = (EditText) view.findViewById(R.id.location);
        description = (EditText) view.findViewById(R.id.description);

        // Init Listener
        view.findViewById(R.id.btn_create).setOnClickListener(this);
        beginDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

        return (view);
    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setTitleError(String error) {
        title.setError(error);
    }

    @Override
    public void setPhotoError(String error) {
    }

    @Override
    public void setBeginDateError(String error) {
        beginDate.setError(error);
    }

    @Override
    public void setEndDateError(String error) {
        endDate.setError(error);
    }

    @Override
    public void setLocationError(String error) {
        location.setError(error);
    }

    @Override
    public void setDescriptionError(String error) {
        description.setError(error);
    }

    @Override
    public void setDialogError(String title, String msg) {
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    public DatePickerDialog getDateEndDialog() {
        return dateEndDialog;
    }

    public DatePickerDialog getDateBeginDialog() {
        return dateBeginDialog;
    }

    public HashMap<String, String>  getData() {
        HashMap<String, String> data = new HashMap<>();

        data.put("title", title.getText().toString());
        data.put("date begin", beginDate.getText().toString());
        data.put("date end", endDate.getText().toString());
        data.put("location", location.getText().toString());
        data.put("description", description.getText().toString());

        return (data);
    }
}
