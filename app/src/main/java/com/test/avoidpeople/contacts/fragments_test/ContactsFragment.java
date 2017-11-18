package com.test.avoidpeople.contacts.fragments_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.avoidpeople.contacts.ContactsActivity;
import com.test.avoidpeople.contacts.R;
import com.test.avoidpeople.contacts.di_test.ActivityComponent;
import com.test.avoidpeople.contacts.di_test.ContactsAdapter;
import com.test.avoidpeople.contacts.fragments_test.contacts.ContactsContract;
import com.test.avoidpeople.contacts.models.Contact;
import com.test.avoidpeople.contacts.utils.FragmentUtility;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 09.11.2017.
 */

    public class ContactsFragment extends Fragment implements ContactsContract.View

    {
        private static final String TAG = ContactsFragment.class.getSimpleName();

        private ActivityComponent activityComponent;
        public static final String SORT_KEY = "SORT_KEY";
        private int sort;

        @Inject
        ContactsContract.Presenter presenter;

        @BindView(R.id.rv_contacts)
        RecyclerView contactsRecyclerView;
        ContactsAdapter contactsAdapter;

        private ContactsAdapter.ContactsItemClick contactsItemClickCallback = (view, item) -> onContactsItemClick(item);

        public static ContactsFragment newInstance(int sort) {
            ContactsFragment fragment = new ContactsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(SORT_KEY, sort);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);

            activityComponent = ((ContactsActivity) getActivity()).getActivityComponent();

            if(getArguments() != null){
                sort = getArguments().getInt(SORT_KEY);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_contacts, container, false);

            ButterKnife.bind(this, view);

            activityComponent.inject(this);
            presenter.setView(this);

            if (contactsAdapter == null) {
                contactsAdapter = new ContactsAdapter(getContext(), Collections.<Contact>emptyList(), contactsItemClickCallback, sort);
            }
            contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            contactsRecyclerView.setAdapter(contactsAdapter);

            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            setActionBar();

            presenter.start(getActivity());
        }

        @Override
        public void onStop() {
            super.onStop();

            presenter.stop();
        }

        private void setActionBar() {
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            }
        }

        @Override
        public void updateContactsList(List<Contact> contacts) {
            contactsAdapter.swapData(contacts);
        }

        @Override
        public void onContactsItemClick(Contact item) {
            FragmentUtility.goToFragment(getFragmentManager(),
                    DetailsFragment.newInstance(item),
                    R.id.main_content,
                    true);
        }
    }
