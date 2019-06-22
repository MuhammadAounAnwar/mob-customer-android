package com.locatemybus.myorderbox.Utilities;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * A helper class for progress dialog
 */
public class ProgressDialogHelper {

    private ProgressDialog mProgressDialog;
    private Context mContext;


    public ProgressDialogHelper(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Show Progress Dialog
     *
     * @param message message to show in progress dialog
     */
    public void showDialog(String message, boolean isCancelable) {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    /**
     * Hide Progress Dialog
     */
    public void hideDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * Updates progress dialog message when running
     */
    public void updateMsg(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(msg);
        }
    }
}
