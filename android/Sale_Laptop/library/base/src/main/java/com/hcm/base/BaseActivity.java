package com.hcm.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    protected V mViewModel;

    protected abstract void setupUI();

    protected abstract void setupAction();

    protected abstract void setupData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    protected void setup() {
        setupUI();
        setupAction();
        setupData();
    }

    public boolean hasPermission(String permission) {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        final View view = this.getCurrentFocus();
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view == null || imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void hideProgressBar() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public boolean isNetworkConnected() {
        final ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        requestPermissions(permissions, requestCode);
    }

    public void showProgressBar() {
        hideProgressBar();
        if (mProgressDialog == null) {
            mProgressDialog = showLoadingDialog(this);
            return;
        }
        mProgressDialog.show();
    }

    /**
     * Hiển thị một thông báo Toast ngắn.
     *
     * @param message Chuỗi thông báo cần hiển thị.
     */
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Điều hướng đến một Activity khác.
     *
     * @param targetActivity Activity đích.
     */
    public void navigateTo(Class<?> targetActivity) {
        final Intent intent = new Intent(this, targetActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * Điều hướng đến một Activity khác với dữ liệu bổ sung.
     *
     * @param targetActivity Activity đích.
     * @param bundle         Bundle chứa dữ liệu bổ sung.
     */
    public void navigateToWithData(Class<?> targetActivity, Bundle bundle) {
        final Intent intent = new Intent(this, targetActivity);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * Thiết lập sự kiện OnClick cho các View.
     *
     * @param view     View cần thiết lập sự kiện.
     * @param listener OnClickListener cho View.
     */
    public void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

    public void setOnTouchListener(View view, View.OnTouchListener listener) {
        view.setOnTouchListener(listener);
    }

    /**
     * Đóng Activity hiện tại.
     */
    public void finishActivity() {
        finish();
    }

    /**
     * Thiết lập thanh trạng thái trong suốt.
     */
    protected void setTransparentStatusBar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Dọn dẹp tài nguyên nếu cần
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    protected void configEditTextPassword(final EditText editText, final Drawable offIcon, final Drawable onIcon) {
        final int TYPE_CLASS_TEXT = InputType.TYPE_CLASS_TEXT;
        final int TYPE_TEXT_VARIATION_PASSWORD = InputType.TYPE_TEXT_VARIATION_PASSWORD;
        setOnTouchListener(editText, new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_UP ||
                        event.getRawX() < (editText.getRight() - editText.getCompoundDrawables()[2].getBounds().width())) {
                    return false;
                }
                if (editText.getInputType() == (TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD)) {
                    editText.setInputType(TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    editText.setCompoundDrawablesWithIntrinsicBounds(null, null, onIcon, null);
                } else {
                    editText.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                    editText.setCompoundDrawablesWithIntrinsicBounds(null, null, offIcon, null);
                }
                editText.setSelection(editText.getText().length());
                return true;
            }
        });
    }

    protected void setUpSpannableString(SpannableString spannableString, String stringValue, String stringStart, int color, Class<?> activity) {
        final int start = stringValue.indexOf(stringStart);
        final int end = start + stringStart.length();
        final ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                navigateTo(activity);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getColor(color));
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }
        };
        spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}

