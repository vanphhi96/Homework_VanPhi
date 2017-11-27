package android.trantan.freemusic11.networks;

import java.util.List;

/**
 * Created by Trần_Tân on 15/11/2017.
 */

public class MusicTypesResponseJSON {
    public List<SubObjectJSON> subgenres;

    public class SubObjectJSON {
        public String id;
        public String translation_key;
    }
}
