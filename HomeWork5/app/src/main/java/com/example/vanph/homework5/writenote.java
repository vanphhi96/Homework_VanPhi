package com.example.vanph.homework5;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class writenote extends AppCompatActivity implements  View.OnClickListener
{
    private static final String TAG = writenote.class.toString();
    private ImageView iv_done;
    private ImageView iv_back;
    private ImageView iv_delete;
    private EditText et_title;
    private  EditText et_description;
    private MyDBHelper myDBHelper;
    public static int id=0;
    private NoteModel noteModel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writenote);
        myDBHelper = new MyDBHelper(this);
        setupUI();
        addListtenners();
        loadData();
    }
    public void setupUI()
    {
        iv_done = (ImageView) findViewById(R.id.iv_done);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_title= (EditText) findViewById(R.id.et_title);
        et_description= (EditText) findViewById(R.id.et_description);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);

    }
    public void addNote()
    {
        String title;
        String desciption;
        title=et_title.getText().toString();
        desciption=et_description.getText().toString();

        if(!(title.equals("")&&desciption.equals("")))
        {
            NoteModel abc = new NoteModel(title,desciption);
            if(noteModel==null)
            {
                myDBHelper.addNote(abc);
                Toast.makeText(this,"Save",Toast.LENGTH_SHORT);
            }
            else{
                abc.setId(noteModel.getId());
                myDBHelper.Update(abc);
            }
        }
        else{
            Toast.makeText(this,"Not saved",Toast.LENGTH_SHORT);
        }


    }
    public void loadData()
    {
        noteModel = (NoteModel) getIntent().getSerializableExtra(MainActivity.NOTE_KEY);
        if(noteModel!=null)
        {
            et_title.setText(noteModel.getTitle());
            et_description.setText(noteModel.getDescription());
        }
    }
    public void addListtenners()
    {
        iv_done.setOnClickListener(this) ;
        iv_back.setOnClickListener(this);
        iv_delete.setOnClickListener(this);
    }
public void setClickDelete()
{

    boolean set=false;
    if(noteModel!=null)
    {
        AlertDialog.Builder altdial = new AlertDialog.Builder(this);
        altdial.setMessage("Do you want delete note?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDBHelper.deleteNote(noteModel.getId());
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).show();



    }


}
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_done:
                addNote();
                iv_done.setClickable(false);
                Log.d(TAG,"add successfully");
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_delete:
                setClickDelete();
                break;
        }
    }
}
