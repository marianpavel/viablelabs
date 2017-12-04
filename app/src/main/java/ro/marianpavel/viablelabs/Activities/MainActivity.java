package ro.marianpavel.viablelabs.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.marianpavel.viablelabs.Adapters.UserAdapter;
import ro.marianpavel.viablelabs.POJO.HumanPOJO;
import ro.marianpavel.viablelabs.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.user_recycler);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<HumanPOJO> list = new ArrayList<>();
        mAdapter = new UserAdapter(getApplicationContext(), list);
        mRecyclerView.setAdapter(mAdapter);
    }
}
