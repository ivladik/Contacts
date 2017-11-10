package com.test.avoidpeople.contacts.fragments_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.avoidpeople.contacts.ContactsActivity;
import com.test.avoidpeople.contacts.R;
import com.test.avoidpeople.contacts.di_test.ActivityComponent;
import com.test.avoidpeople.contacts.fragments_test.details.DetailsContract;
import com.test.avoidpeople.contacts.models.Contact;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 09.11.2017.
 */

public class DetailsFragment extends Fragment implements DetailsContract.View {

    private static final String TAG = DetailsFragment.class.getSimpleName();
    public static final String CONTACT_KEY = "CONTACT_KEY";

//    private ActivityComponent activityComponent;

    @BindView(R.id.username_details)
    TextView tvUsername;
    @BindView(R.id.phone_details)
    TextView tvPhone;
    @BindView(R.id.address_details)
    TextView tvAddress;
    @BindView(R.id.website_details)
    TextView tvWebsite;
    @BindView(R.id.company_details)
    TextView tvCompany;

    private Contact contact;

    public static DetailsFragment newInstance(Contact contact) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CONTACT_KEY, contact);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        activityComponent = ((ContactsActivity)getActivity()).getActivityComponent();

        if(getArguments() != null){
            contact = (Contact) getArguments().getSerializable(CONTACT_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, view);

        tvUsername.setText(TextUtils.isEmpty(contact.getName()) ? "no name" : contact.getName());
        tvPhone.setText(TextUtils.isEmpty(contact.getPhone()) ? "no phone" : contact.getPhone());
        tvAddress.setText(TextUtils.isEmpty(contact.getAddress().getCity()) ? "no address" : contact.getAddress().getCity());
        tvWebsite.setText(TextUtils.isEmpty(contact.getWebsite()) ? "no website" : contact.getWebsite());
        tvCompany.setText(TextUtils.isEmpty(contact.getCompany().getName()) ? "no company" : contact.getCompany().getName());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setActionBar();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void setActionBar() {
        if(((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(contact.getName());
        }
    }
}
