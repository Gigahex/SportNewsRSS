package gigahex.sportnewsrss.screens;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.util.List;

import gigahex.sportnewsrss.models.Article;
import gigahex.sportnewsrss.R;

class RSSFeedListAdapter
        extends RecyclerView.Adapter<RSSFeedListAdapter.FeedModelViewHolder> {

    private List<Article> articles;
    private Context context;

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public RSSFeedListAdapter(Context context, List<Article> rssFeedModels) {
        articles = rssFeedModels;
        this.context = context;
    }

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rss_feed, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final Article article = articles.get(position);
        ((TextView)holder.rssFeedView.findViewById(R.id.titleText)).setText(article.getTitle());
        String description = Jsoup.parse(article.getDescription()).text();
        ((TextView)holder.rssFeedView.findViewById(R.id.descriptionText))
                .setText(description);
        Button button = ((Button)holder.rssFeedView.findViewById(R.id.linkText));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("link", article.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
