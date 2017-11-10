package com.test.avoidpeople.contacts.di_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.avoidpeople.contacts.R;
import com.test.avoidpeople.contacts.models.Contact;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 09.11.2017.
 */

public class ContactsAdapter2 extends RecyclerView.Adapter<ContactsAdapter2.ViewHolder> {
    private Context context;
    private List<Contact> contacts;

    public ContactsAdapter2(Context context, List<Contact> contacts){
        this.context = context;
        this.contacts = contacts;

        sortContent();
    }

    public void swapData(List<Contact> contacts){
        this.contacts = contacts;
        sortContent();
        this.notifyDataSetChanged();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Probably not best to do this here, but since I'm using this across modules, I didn't want
     * to have to presort.
     */
    private void sortContent() {
        Collections.sort(this.contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact contactOne, Contact contactTwo) {
                return contactOne.getName().compareTo(contactTwo.getName());
            }
        });
    }

    @Override
    public ContactsAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ContactsAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter2.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvEmail.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvEmail = (TextView)itemView.findViewById(R.id.tvEmail);
        }
    }
}
