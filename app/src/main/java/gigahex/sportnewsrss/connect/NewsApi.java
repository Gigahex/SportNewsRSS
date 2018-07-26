package gigahex.sportnewsrss.connect;

import retrofit2.Call;
import retrofit2.http.GET;
import gigahex.sportnewsrss.models.RSSFeed;
public interface NewsApi {
    @GET("all_news.xml")
    Call<RSSFeed> loadRSSFeed();
}
