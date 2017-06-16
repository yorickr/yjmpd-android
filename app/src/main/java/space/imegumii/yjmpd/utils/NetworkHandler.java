package space.imegumii.yjmpd.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by imegumii on 06/06/2017.
 */

public class NetworkHandler {

    private static NetworkHandler nwh = new NetworkHandler();

    private String protocol = "http://";
    private String hostName = "imegumii.space";
    private int port = 8080;

    OkHttpClient client;

    private NetworkHandler () {
        client = new OkHttpClient();
    }

    public static NetworkHandler getInstance() {
        return nwh;
    }

    public String getUrl() {
        return protocol + hostName + ":" + port;
    }

    public Request getRequest(String path, Callback cb) {
        Request req = new Request.Builder()
                .url(protocol + hostName + ":" + port + path)
                .build();
        client.newCall(req).enqueue(cb);
        return req;
    }
}
