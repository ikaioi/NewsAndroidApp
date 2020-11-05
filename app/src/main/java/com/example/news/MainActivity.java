package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.news.model.NewsArticle;
import com.example.news.ui.details.DetailsFragment;
import com.example.news.ui.main.MainFragment;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {


            executor = ContextCompat.getMainExecutor(this);
            biometricPrompt = new BiometricPrompt(MainActivity.this,
                    executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.auth_error) + errString, Toast.LENGTH_SHORT)
                            .show();

                    if (errorCode == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE  ||  errorCode == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ){
                        proceedToMainFragment();
                    }
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.auth_succeeded), Toast.LENGTH_SHORT)
                            .show();

                    proceedToMainFragment();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(), getString(R.string.auth_failed),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

            promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle(getString(R.string.biometric_login))
                    .setSubtitle(getString(R.string.login_with_credentials))
                    .setNegativeButtonText(getString(R.string.password))
                    .build();

            biometricPrompt.authenticate(promptInfo);

        }

    }


    public void proceedToMainFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow();
    }





    /** Change to headline detail fragment */
    public void show(NewsArticle headline) {

        DetailsFragment detailsFragment = DetailsFragment.newInstance(headline);

        getSupportFragmentManager()
                .beginTransaction()
                .setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE )
                .addToBackStack("headline")
                .replace(R.id.container,
                        detailsFragment, null).commit();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}