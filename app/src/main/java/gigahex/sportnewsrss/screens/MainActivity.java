package gigahex.sportnewsrss.screens;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.l4digital.fastscroll.FastScrollRecyclerView;

import java.util.List;
import gigahex.sportnewsrss.R;
import gigahex.sportnewsrss.connect.ApiClient;
import gigahex.sportnewsrss.connect.NewsApi;
import gigahex.sportnewsrss.models.Article;
import gigahex.sportnewsrss.models.RSSFeed;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import gigahex.sportnewsrss.screens.RSSFeedListAdapter;

public class MainActivity extends AppCompatActivity {
    List<Article> articles;
    ProgressDialog progressDialog;

    @BindView(R.id.recyclerView)
    FastScrollRecyclerView recyclerView;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
         progressDialog = new ProgressDialog(MainActivity.this);
         progressDialog.setMessage("Подождите....");
         progressDialog.show();
        NewsApi apiService =
                ApiClient.getClient().create(NewsApi.class);

        Call<RSSFeed> call = apiService.loadRSSFeed();
        call.enqueue(new Callback<RSSFeed>() {
            @Override
            public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
                articles = response.body().getArticleList();
                progressDialog.dismiss();
                generateDataList(articles);
            }

            @Override
            public void onFailure(Call<RSSFeed> call, Throwable t) {
               progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(List<Article> articles) {
        RSSFeedListAdapter adapter = new RSSFeedListAdapter(this, articles);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.info:
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                return true;
        }
            return super.onOptionsItemSelected(item);
    }
}
