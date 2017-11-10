package com.test.avoidpeople.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.avoidpeople.contacts.di_test.ActivityComponent;
import com.test.avoidpeople.contacts.di_test.ApplicationComponent;
import com.test.avoidpeople.contacts.di_test.BaseApplication;
import com.test.avoidpeople.contacts.di_test.DaggerActivityComponent;
import com.test.avoidpeople.contacts.di_test.modules.PresenterModule;
import com.test.avoidpeople.contacts.fragments_test.ContactsFragment;
import com.test.avoidpeople.contacts.fragments_test.DetailsFragment;
import com.test.avoidpeople.contacts.utils.FragmentUtility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity implements ProgressBarProvider {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvLoading)
    TextView tvLoading;
    @BindView(R.id.loading)
    ProgressBar progressBar;
    @BindView(R.id.main_content)
    FrameLayout mainContent;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        // Grab application component from BaseApplication
        ApplicationComponent applicationComponent = BaseApplication.getApplication(this)
                .getApplicationComponent();

        // Create activity component, which has a dependency on ApplicationComponent
        activityComponent = DaggerActivityComponent
                .builder()
                .applicationComponent(applicationComponent)
                .presenterModule(new PresenterModule(this))
                .build();

        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {

            // Set our first Fragment to the RepositoryFragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_content, ContactsFragment.newInstance(0))
                    .commitNow();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.sortAZ:
                FragmentUtility.goToFragment(getSupportFragmentManager(),
                        ContactsFragment.newInstance(1),
                        R.id.main_content,
                        true);
                return true;
            case R.id.sortZA:
                FragmentUtility.goToFragment(getSupportFragmentManager(),
                        ContactsFragment.newInstance(2),
                        R.id.main_content,
                        true);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        tvLoading.setVisibility(View.VISIBLE);
        mainContent.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        tvLoading.setVisibility(View.GONE);
        mainContent.setVisibility(View.VISIBLE);
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
