package ro.marianpavel.viablelabs.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Arrays;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ro.marianpavel.viablelabs.Adapters.UserAdapter;
import ro.marianpavel.viablelabs.Interfaces.UserListener;
import ro.marianpavel.viablelabs.POJO.GenericPOJO;
import ro.marianpavel.viablelabs.POJO.HumanPOJO;
import ro.marianpavel.viablelabs.R;
import ro.marianpavel.viablelabs.Singletons.RestClient;
import ro.marianpavel.viablelabs.Utilities.Constants;
import ro.marianpavel.viablelabs.Utilities.HumanRepository;
import ro.marianpavel.viablelabs.Utilities.PaginationScrollListener;

public class MainActivity extends AppCompatActivity implements UserListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private int currentPage = 1;
    private int maximumResults = 20;
    private ProgressBar progress;
    private boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupUserList();
        fetchUsers(currentPage);
    }

    private void fetchUsers(final int currentPage) {
        loading = true;
        progress.setVisibility(View.VISIBLE);
        RestClient.getInstance(this)
                .getApi()
                .getHumans(currentPage, maximumResults)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GenericPOJO<HumanPOJO[]>>() {
                    @Override
                    public void accept(GenericPOJO<HumanPOJO[]> genericPOJO) throws Exception {
                        if (genericPOJO != null && genericPOJO.getObject().length > 0) {
                            progress.setVisibility(View.GONE);
                            HumanRepository.getInstance().getHumans().addAll(Arrays.asList(genericPOJO.getObject()));
                            mAdapter.notifyItemRangeInserted((currentPage - 1) * maximumResults, maximumResults);

                            loading = false;
                            progress.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void setupUserList() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UserAdapter(getApplicationContext(), HumanRepository.getInstance().getHumans(), MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                fetchUsers(++currentPage);
            }

            @Override
            public int getTotalPageCount() {
                return currentPage + 1;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return loading;
            }
        });
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.user_recycler);
        progress = findViewById(R.id.loading);
    }

    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(Constants.INTENT_SELECTED_POSITION, position);
        startActivity(intent);
    }
}
