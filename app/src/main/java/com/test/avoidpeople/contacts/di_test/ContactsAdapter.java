package com.test.avoidpeople.contacts.di_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.avoidpeople.contacts.R;
import com.test.avoidpeople.contacts.models.Contact;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by User on 09.11.2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public interface ContactsItemClick {
        void OnContactsItemClick(View view, Contact item);
    }


    private Context context;
    private List<Contact> contacts;
    private ContactsAdapter.ContactsItemClick itemClickCallback;
    private int sort;

    public ContactsAdapter(Context context, List<Contact> contacts, ContactsAdapter.ContactsItemClick itemClickCallback, int sort) {
        this.context = context;
        this.contacts = contacts;
        this.itemClickCallback = itemClickCallback;
        this.sort = sort;

        /*if (sort > 0) {
            sortContent(sort);
        }*/
    }

    public void swapData(List<Contact> contacts) {
        this.contacts = contacts;
        if (sort > 0)
            sortContent(sort);

        this.notifyDataSetChanged();
    }

    public void setContactsItemClick(ContactsAdapter.ContactsItemClick contactsItemClick) {
        this.itemClickCallback = contactsItemClick;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    private void sortContent(int sort) {
        switch (sort) {
            case 1:
                Collections.sort(this.contacts, new Comparator<Contact>() {
                    @Override
                    public int compare(Contact contactOne, Contact contactTwo) {
                        return contactOne.getName().compareTo(contactTwo.getName());
                    }
                });
                break;
            case 2:
                Collections.sort(this.contacts, new Comparator<Contact>() {
                    @Override
                    public int compare(Contact contactOne, Contact contactTwo) {
                        return contactTwo.getName().compareTo(contactOne.getName());
                    }
                });
                break;
        }
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);

        return new ContactsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvEmail.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout container;
        TextView tvName;
        TextView tvEmail;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickCallback != null) {
                itemClickCallback.OnContactsItemClick(view,
                        contacts.get(getAdapterPosition()));
            }
        }
    }
}
