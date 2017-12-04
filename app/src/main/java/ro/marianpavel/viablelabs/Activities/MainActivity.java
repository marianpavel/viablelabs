package ro.marianpavel.viablelabs.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ro.marianpavel.viablelabs.Adapters.UserAdapter;
import ro.marianpavel.viablelabs.POJO.GenericPOJO;
import ro.marianpavel.viablelabs.POJO.HumanPOJO;
import ro.marianpavel.viablelabs.R;
import ro.marianpavel.viablelabs.Singletons.RestClient;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupUserList();
        fetchUsers();
    }

    private void fetchUsers() {
        RestClient.getInstance(this)
                .getApi()
                .getHumans(1, 20)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GenericPOJO<HumanPOJO[]>>() {
                    @Override
                    public void accept(GenericPOJO<HumanPOJO[]> genericPOJO) throws Exception {
                        List<HumanPOJO> humans = new ArrayList<>();
                        if (genericPOJO != null && genericPOJO.getObject().length > 0) {
                            humans.addAll(Arrays.asList(genericPOJO.getObject()));
                            mAdapter = new UserAdapter(getApplicationContext(), humans);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }

    private void setupUserList() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.user_recycler);
    }
}
