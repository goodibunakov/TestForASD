package ru.goodibunakov.testforasd;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.goodibunakov.testforasd.adapters.RecyclerAdapter;
import ru.goodibunakov.testforasd.api.ApiService;
import ru.goodibunakov.testforasd.api.ServiceGenerator;
import ru.goodibunakov.testforasd.model.DbItem;
import ru.goodibunakov.testforasd.model.Rss;
import ru.goodibunakov.testforasd.utils.DatabaseHelper;
import ru.goodibunakov.testforasd.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    ApiService client;
    private DatabaseHelper databaseHelper;
    private RecyclerAdapter adapter;
    private ConnectivityManager cm;
    public static final int PAGE_SIZE = 30;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        client = ServiceGenerator.getJsonService();
        databaseHelper = new DatabaseHelper(this);
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerAdapter();
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        adapter.setItems(databaseHelper.getItemsFromDb());


        if (NetworkUtils.haveNetworkConnection(cm)) {
            getContentFromServer();
        }


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    loadMoreItems();
                }
            }
        });
    }

    private void getContentFromServer() {
        client.getNewsItems().enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                Log.d("debug", "response.body() " + response.toString());
                if (response.isSuccessful()) {
                    Log.d("debug", response.body().getChannel().getItems().toString());
                    databaseHelper.saveToDb(response.body().getChannel().getItems());
                }
                adapter.setItems(databaseHelper.getItemsFromDb());
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                adapter.setItems(databaseHelper.getItemsFromDb());
            }
        });

    }

    private void loadMoreItems() {
        isLoading = true;

//        currentPage += 1;
//
//        Call findRelatedVideosCall = vimeoService.findRelatedVideos(videoId, currentPage, PAGE_SIZE);
//        calls.add(findRelatedVideosCall);
//        findRelatedVideosCall.enqueue(findRelatedVideosNextFetchCallback);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("povorot", 1);
    }

    @Override
    public void onItemClick(int position, View view) {
        DbItem dbItem = adapter.getItem(position);

        if (dbItem != null) {
            String link = dbItem.getLink();
            if (link != null && !link.isEmpty()) {
                if (NetworkUtils.haveNetworkConnection(cm)) {
                    Intent intent = new Intent(this, DetailsActivity.class);
                    intent.putExtra("links", link);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Нет интернета!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
