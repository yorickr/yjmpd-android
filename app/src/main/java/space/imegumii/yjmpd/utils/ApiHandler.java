package space.imegumii.yjmpd.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import space.imegumii.yjmpd.objects.Song;

/**
 * Created by Yorick Rommers on 06/06/2017.
 */

public class ApiHandler {

    public interface Cb <T> {
        void onEvent(T o);
    }

    public static void downloadSong(int songId) {
    }

    public static void getSongs (final Cb<SortedSet<Song>> cb) {
        NetworkHandler.getInstance().getRequest("/api/songs", new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        SortedSet<Song> returnSet = new TreeSet<Song>();
                        JSONObject o = new JSONObject(response.body().string());
                        if (o.getBoolean("success")) {
                            JSONArray a = o.getJSONArray("data");
                            for (int i = 0; i < a.length(); i++ ){
                                JSONObject obj = a.getJSONObject(i);
//                                System.out.println(obj);
                                Song s;
                                if (obj.getInt("id") != -1) {
                                    s = new Song(obj.getInt("id"));
                                    s.addAlbum(obj.getString("album"));
                                    s.addArtist(obj.getString("artist"));
                                    s.addGenre(obj.getString("genre"));
                                    s.addTitle(obj.getString("title"));
                                    returnSet.add(s);
                                }
                            }
                        }
                        cb.onEvent(returnSet);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Something went wrong, print error code.
                    System.out.println("Error occured in getSongs " + response);
                }
            }
        });
    }
}
