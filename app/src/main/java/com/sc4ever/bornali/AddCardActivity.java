package com.sc4ever.bornali;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sc4ever.bornali.data.CardCategoryRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCardActivity extends AppCompatActivity {
    private ImageView cardImage;
    private Button saveButton;
    private EditText textDescription;
    private Uri imageUri;
    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        saveButton =  findViewById(R.id.buttonSave);
        cardImage = findViewById(R.id.imageView101);
        textDescription = findViewById(R.id.editText);
        final CardCategoryRepository newCardCategoryRepository = new CardCategoryRepository(getApplicationContext());
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               try {
                   newCardCategoryRepository.updateCard(textDescription.getText().toString(),
                           imageUri.toString(), 0 , 0);
               } catch (SQLException e){
                   e.printStackTrace();
                   Toast.makeText(getApplicationContext(),  R.string.category_error,
                           Toast.LENGTH_LONG).show();
               } finally {
                   Toast.makeText(getApplicationContext(), R.string.category_success,
                           Toast.LENGTH_LONG).show();
                   finish();
               }

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            Intent intent = new Intent(AddCardActivity.this, HelpPageActivity.class) ;
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    void uploadPhoto() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        File pictureDirectory = Environment.getDataDirectory();
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        i.setDataAndType(data, "image/*");
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == RESULT_LOAD_IMAGE)
            {
                imageUri = data.getData();
                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    cardImage.setImageBitmap(image);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(this,"Unable to Open",Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}
