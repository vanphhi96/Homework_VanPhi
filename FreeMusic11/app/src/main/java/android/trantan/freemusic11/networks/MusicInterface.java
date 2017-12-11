package android.trantan.freemusic11.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Trần_Tân on 15/11/2017.
 */

public interface MusicInterface {
    @GET("api")
    Call<MusicTypesResponseJSON> getMusicType();
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongResponseJSON>getTopSong(@Path("id")String id);
    @GET("https://tk-gx.herokuapp.com/api/audio")
    Call<SearchResponseJSON>getSearchSong(@Query("search_terms")String search);
}
