package edu.ccm.gameslib;


import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class googleSheets extends Activity
    implements EasyPermissions.PermissionCallbacks {
    GoogleAccountCredential mCredential;
    private TextView mOutputText;
    private Button mCallApiButton;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static final String BUTTON_TEXT = "Call Google Sheets API";
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = { SheetsScopes.SPREADSHEETS };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        layoutParams.setMargins(16,16,16,16);

        ViewGroup.LayoutParams t_lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mCallApiButton = new Button(this);
        mCallApiButton.setText(BUTTON_TEXT);
        mCallApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallApiButton.setEnabled(false);
                mOutputText.setText("");
                getResultsFromApi();
                mCallApiButton.setEnabled(true);
            }
        });
        linearLayout.addView(mCallApiButton);

        mOutputText = new TextView(this);
        mOutputText.setLayoutParams(t_lp);
        mOutputText.setPadding(16,16,16,16);
        mOutputText.setVerticalScrollBarEnabled(true);
        mOutputText.setMovementMethod(new ScrollingMovementMethod());
        mOutputText.setText(
                "Click the " + BUTTON_TEXT + "\' button to test the API.");

        linearLayout.addView(mOutputText);

        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

    }

    private void getResultsFromApi() {
        
        if (! isGooglePlayServiceAvaliable()){
            aquireGooglePlayService();
        }else if (mCredential.getSelectedAccountName() == null){
            chooseAccount();
        }else if (! isDeviceOnline()){
            mOutputText.setText("No Network Connection Avaliable.");
        }else {
            new MakeRequestTask(mCredential).execute();
        }
    }

    private void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                this, Manifest.permission.GET_ACCOUNTS)){
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);

            if (accountName != null){
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            }else {
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        }else {
            EasyPermissions.requestPermissions(
                    this,"This App Requires access to Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    protected void onActivityResult(
        int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (requestCode != RESULT_OK) {
                    mOutputText.setText
                            ("This app requires google play services please install google play" +
                                    " google play servicies and relaunch this app.");
                } else {
                    getResultsFromApi();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (requestCode == RESULT_OK && data != null && data.getExtras() != null){
                    String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);

                    if (accountName != null){
                        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        mCredential.setSelectedAccountName(accountName);
                        getResultsFromApi();
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (requestCode == RESULT_OK){
                    getResultsFromApi();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,
                permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //do nothing
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //do nothing
    }
    private boolean isDeviceOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean isGooglePlayServiceAvaliable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }


    private void aquireGooglePlayService() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
        
        if (apiAvailability.isUserResolvableError(connectionStatusCode)){
            showGooglePlayServiciesAvalibilityErrorDialog(connectionStatusCode);
        }
    }

    void showGooglePlayServiciesAvalibilityErrorDialog(final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                googleSheets.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }


    private class MakeRequestTask extends AsyncTask<Void,Void, List<String>> {

        private com.google.api.services.sheets.v4.Sheets mService = null;
        private Exception mLastError = null;

         MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                    transport, jsonFactory, credential).
                    setApplicationName("CCM Games Library").build();
        }

        @Override
        protected List<String> doInBackground(Void... params) {
             try {
                 return getDataFromApi();
             }
             catch (Exception e){
                 mLastError = e;
                 cancel(true);
                 return null;
             }
        }
        private List<String> getDataFromApi() throws IOException {
            String spreedsheetId = "1rZm3tuX03L8Tz8PPYO_AnDhJMR63dmIQXae3TSMRIaM";
            String range = "Info Gathering! A1:H";

            List<String> results = new ArrayList<String>();

            ValueRange response = this.mService.spreadsheets().values()
                    .get(spreedsheetId, range).execute();
            List<List<Object>> values = response.getValues();
            if (values != null){
                results.add("Item, Platform, Donated, Genre(s), Developer(s)");
                for (List row: values){
                    //CHANGE THIS FOR DISPLAYING THE SPREEDSHEET DATA THIS IS TEMP
                    results.add(row.get(0) + ", " + row.get(4));
                }
            }
            return results;
        }

        @Override
        protected void onPreExecute() {
             mOutputText.setText("");

        }

        @Override
        protected void onPostExecute(List<String> output) {
             if (output == null || output.size() == 0){
                 mOutputText.setText("No results returned.");
             } else {
                 output.add(0, "Data retrieved using the Google Sheets API:");
                 mOutputText.setText(TextUtils.join("\n", output));
             }
        }

        @Override
        protected void onCancelled() {

             if (mLastError != null){
                 if (mLastError instanceof GooglePlayServicesAvailabilityIOException){
                     showGooglePlayServiciesAvalibilityErrorDialog(
                             ((GooglePlayServicesAvailabilityIOException) mLastError)
                             .getConnectionStatusCode());
                 }else if (mLastError instanceof UserRecoverableAuthIOException){
                     startActivityForResult(
                             ((UserRecoverableAuthIOException) mLastError).getIntent(),
                             googleSheets.REQUEST_AUTHORIZATION);
                 } else {
                     mOutputText.setText("The Following error occured: \n" +
                             mLastError.getMessage());
                 }
             }
             else {
                 mOutputText.setText("Request cancelled");
             }
        }
    }


}
