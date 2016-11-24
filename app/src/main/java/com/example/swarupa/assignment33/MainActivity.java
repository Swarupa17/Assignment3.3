package com.example.swarupa.assignment33;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int img_result=1;
    String ImageDecode;
    ImageView imUpload;
    Button upload;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imUpload = (ImageView)findViewById(R.id.imageView);
        upload =(Button)findViewById(R.id.button);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,img_result);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode==img_result && resultCode==RESULT_OK && null!= data)
            {
                Uri uri =data.getData();
                String[] File = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(uri,File,null,null,null);
                cursor.moveToFirst();

                int columnIndex =  cursor.getColumnIndex(File[0]);
                ImageDecode = cursor.getString(columnIndex);

                cursor.close();

                imUpload.setImageBitmap(BitmapFactory.decodeFile(ImageDecode));
            }
        }catch (Exception e)
        {
            Toast.makeText(this,"Please try again",Toast.LENGTH_LONG).show();
        }
    }
}
