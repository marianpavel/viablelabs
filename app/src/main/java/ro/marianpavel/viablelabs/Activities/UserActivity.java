package ro.marianpavel.viablelabs.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ro.marianpavel.viablelabs.POJO.HumanPOJO;
import ro.marianpavel.viablelabs.R;
import ro.marianpavel.viablelabs.Utilities.Constants;
import ro.marianpavel.viablelabs.Utilities.HumanRepository;

public class UserActivity extends AppCompatActivity {

    private HumanPOJO human;
    private ImageView avatar;
    private TextView name, age, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        if (getIntent().getExtras() != null) {
            int position = getIntent().getIntExtra(Constants.INTENT_SELECTED_POSITION, 0);
            human = HumanRepository.getInstance().getHumans().get(position);
        }

        initViews();
        populateViews();
        setupListeners();
    }

    private void setupListeners() {
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:" + human.getEmail());
                intent.setData(data);
                startActivity(intent);
            }
        });
    }

    private void populateViews() {
        Glide.with(this)
                .load(human.getPicture().getLarge())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(avatar);

        String nameBuild = human.getName().getFirst() + " " + human.getName().getLast();
        name.setText(nameBuild);


        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);

        int currentAge = currentYear - calendar.get(Calendar.YEAR);
        age.setText(getString(R.string.age, String.valueOf(currentAge)));

        email.setText(human.getEmail());
    }

    private void initViews() {
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
    }
}
