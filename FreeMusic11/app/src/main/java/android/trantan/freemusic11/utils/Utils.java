package android.trantan.freemusic11.utils;

import android.content.Context;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.trantan.freemusic11.database.DowloadSongModel;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanph on 22/11/2017.
 */

public class Utils {
    public static void openFragment(FragmentManager fragmentManager, int layoutID, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void rotateImage(ImageView imageView, boolean isRotate) {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 359f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(10000);
        if (isRotate) {
            if (imageView.getAnimation() == null) {
                imageView.startAnimation(rotateAnimation);
            }
        } else {
            imageView.setAnimation(null);
        }
    }
    public static String convertTime(long time)
    {
        long min = time/60000;
        long sec = (time - min*60000) / 1000;
        return String.format("%02d:%02d",min,sec);
    }
    public static List<DowloadSongModel> getPathFile() {
        List<DowloadSongModel> dowloadSongModelList = new ArrayList<>();
        File imageFolder = new File(Environment.getExternalStorageDirectory().toString() + "/MusicFree");
        File[] listFile = imageFolder.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                String filePath = listFile[i].getAbsolutePath();
                String[] path = filePath.split("-");
                DowloadSongModel dowloadSongModel = new DowloadSongModel();
                dowloadSongModel.singer=path[1].substring(0,path[1].length()-4);
                String root = Environment.getExternalStorageDirectory().toString() + "/MusicFree";
                dowloadSongModel.song= path[0].substring(root.length()+1);
                dowloadSongModel.mp3Path=filePath;
                dowloadSongModelList.add(dowloadSongModel);
            }
        }
        return dowloadSongModelList;
    }

}
