package android.trantan.freemusic11.networks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vanph on 25/11/2017.
 */

public class TopSongResponseJSON {
    public FeedJSON feed;
    public class FeedJSON{
        public List<EntryJSON> entry;
        public class EntryJSON{
            @SerializedName("im:name")
            public Name name;
            @SerializedName("im:artist")
            public Artist artist;
            @SerializedName("im:image")
            public List<Image> images;
            public class Name{
                public String label;
            }
            public class  Image{
                public String label;
            }
            public class Artist{
                public String label;
            }
        }
    }


}
