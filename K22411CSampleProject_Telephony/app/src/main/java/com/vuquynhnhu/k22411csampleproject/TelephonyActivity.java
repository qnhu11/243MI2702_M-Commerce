package com.vuquynhnhu.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.models.TelephonyInfor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.TelephonyInforAdapter;
import android.util.Log;
import android.view.MenuInflater;
import android.widget.AdapterView;
import androidx.annotation.NonNull;


public class TelephonyActivity extends AppCompatActivity {
    ListView lvTelephony;
    //    ArrayAdapter<TelephonyInfor> adapter;
    TelephonyInforAdapter adapter;
    private List<TelephonyInfor> fullList = new ArrayList<>();
    private final String[] viettelPrefixes = {"086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039"};
    private final String[] mobiPrefixes = {"089", "090", "093", "070", "076", "077", "078", "079"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        getAllContacts();
        addEvents();
    }

    private void addEvents() {
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TelephonyInfor ti = adapter.getItem(position);
                makePhoneCall(ti);
            }
        });
    }

    private void makePhoneCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:"+ti.getPhone());
//        Intent intent = new9 Intent(Intent.ACTION_DIAL);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        adapter.clear();
        fullList.clear();

        while (cursor.moveToNext()){
            int nameIndex =cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex); //Get Name
            int phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds. Phone.NUMBER);
            String phone = cursor.getString(phoneIndex); //Get Phone Number

            TelephonyInfor ti = new TelephonyInfor();
            ti.setName(name);
            ti.setPhone(phone);
            fullList.add(ti);
        }
        cursor.close();


    }

    private void addViews() {
        lvTelephony = findViewById(R.id.lvTelephony);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor, fullList);
        lvTelephony.setAdapter(adapter);
    }

    public void directCall(TelephonyInfor ti)
    {
        Uri uri = Uri.parse("tel:"+ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }
    public void dialupCall(TelephonyInfor ti)
    {
        Uri uri = Uri.parse("tel:"+ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_telephony, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        List<TelephonyInfor> filteredList = new ArrayList<>();

        if (item.getItemId() == R.id.menu_viettel) {
            filteredList = filterByPrefix(fullList, viettelPrefixes);
        } else if (item.getItemId() == R.id.menu_mobifone) {
            filteredList = filterByPrefix(fullList, mobiPrefixes);
        } else if (item.getItemId() == R.id.menu_others) {
            filteredList = filterOthers(fullList);
        } else {
            return super.onOptionsItemSelected(item);
        }

        adapter = new TelephonyInforAdapter(this,R.layout.item_telephony_infor, filteredList);
        lvTelephony.setAdapter(adapter);
        Log.d("DEBUG", "Filtered size = " + filteredList.size());
        return true;
    }


    private List<TelephonyInfor> filterByPrefix(List<TelephonyInfor> list, String[] prefixes) {
        List<TelephonyInfor> result = new ArrayList<>();
        for (TelephonyInfor info : list) {
            for (String prefix : prefixes) {
                if (info.getPhone().startsWith(prefix)) {
                    result.add(info);
                    break;
                }
            }
        }
        return result;
    }

    private List<TelephonyInfor> filterOthers(List<TelephonyInfor> list) {
        List<TelephonyInfor> result = new ArrayList<>();
        for (TelephonyInfor info : list) {
            String num = info.getPhone();
            boolean isViettel = Arrays.asList(viettelPrefixes).stream().anyMatch(num::startsWith);
            boolean isMobi = Arrays.asList(mobiPrefixes).stream().anyMatch(num::startsWith);
            if (!isViettel && !isMobi) {
                result.add(info);
            }
        }
        return result;
    }


    public void dialUpCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:"+ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }
}
