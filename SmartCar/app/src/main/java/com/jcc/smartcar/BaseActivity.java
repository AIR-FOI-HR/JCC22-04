package com.jcc.smartcar;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private Dialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void showProgressDialog(final String text) {
        mProgressDialog = new Dialog(this);

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        //mProgressDialog.setContentView(R.layout.dialog_progress);

        //mProgressDialog.tv_progress_text.text = text;

        //Start the dialog and display it on screen.
        mProgressDialog.show();
    }

    void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    String getCurrentUserID() {
        // TODO: after firebase setup is done fetch current user id from firebase instance
        //  return FirebaseAuth.getInstance().currentuser.id;
        return "";
    }

    /* DO WE NEED THIS?
    private void doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(
                this,
                resources.getString(R.string.please_click_back_again_to_exit),
                Toast.LENGTH_SHORT
        ).show();


        new Handler().postDelayed(this.doubleBackToExitPressedOnce = false, 2000);
    }*/

    void showErrorSnackBar(final String message) {
        Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View snackBarView = snackBar.getView();
        snackBarView.setBackgroundColor(Color.WHITE);
        snackBar.show();
    }
}
