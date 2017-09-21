package com.harshadachavan.contactaction;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String[] name = {
            "Harshada Chavan",
            "Abhijeet",
            "Aditya",
            "Ajinkya",
            "Uma",
            "Rushikesh"
    } ;
    String[] number = {
            "8805997408",
            "9819196008",
            "766129619",
            "8793763671",
            "9764054594",
            "9762528328"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomList adapter = new
                CustomList(MainActivity.this, name, number);
        list=(ListView)findViewById(R.id.contactlist);

        list.setAdapter(adapter);

        // Register the ListView  for Context menu
        registerForContextMenu(list);

       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " +name[+ position], Toast.LENGTH_SHORT).show();

            }
        }); */
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Send SMS");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        if(item.getTitle()=="Call"){
            //Toast.makeText(getApplicationContext(),"calling code",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+number[+ index]));
            startActivity(intent);
        }
        else if(item.getTitle()=="Send SMS"){
           // Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();

            Uri uri = Uri.parse("smsto:"+number[+ index]);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", "The SMS text");
            startActivity(it);
        }else{
            return false;
        }
        return true;
    }
}
