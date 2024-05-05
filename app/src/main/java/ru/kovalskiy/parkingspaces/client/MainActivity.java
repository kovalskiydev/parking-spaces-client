package ru.kovalskiy.parkingspaces.client;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import android.os.Bundle;
import lombok.val;
import ru.kovalskiy.data.api.StreetApiDataSource;
import ru.kovalskiy.data.repository.StreetRepositoryImpl;
import ru.kovalskiy.data.storage.sqlite.SQLiteDbHelper;
import ru.kovalskiy.data.storage.sqlite.impl.SQLiteStreetServiceImpl;
import ru.kovalskiy.domain.repository.usecase.GetStreetByIdUseCaseImpl;

import java.io.IOException;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "ObsoleteSdkInt"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val dbHelper = new SQLiteDbHelper(this);
        val localDataSource = new SQLiteStreetServiceImpl(dbHelper);
        val remoteDataSource = new StreetApiDataSource(localDataSource);

        val repository = new StreetRepositoryImpl(remoteDataSource, localDataSource);

        val getStreetByIdUseCase = new GetStreetByIdUseCaseImpl(repository);

        TextView dataTextView = findViewById(R.id.dataTextView);
        Button getDataButton = findViewById(R.id.getDataButton);


        getDataButton.setOnClickListener(view -> {
            Log.d("OnClickListener", "Button clicked");
            try {
                dataTextView.setText(getStreetByIdUseCase.execute(1).toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}