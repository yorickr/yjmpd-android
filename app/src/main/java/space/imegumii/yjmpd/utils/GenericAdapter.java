package space.imegumii.yjmpd.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.logging.Logger;

import space.imegumii.yjmpd.R;
import space.imegumii.yjmpd.constants.Constants;

/**
 * Created by Yorick Rommers on 16/06/2017.
 */

public class GenericAdapter<T> extends ArrayAdapter {
    List<T> data;
    private RenderCallback rcb;

    public interface RenderCallback {
        View render(int position, @Nullable View convertView, @NonNull ViewGroup parent, Context c, Object o);
    }

    public GenericAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.data = objects;
        this.rcb = new RenderCallback() {
            @Override
            public View render(int position, @Nullable View convertView, @NonNull ViewGroup parent, Context c, Object o) {
                Log.e(Constants.LOG_TAG, "Someone forgot to set a rendercallback in GenericAdapter");
                return null;
            }
        };
    }

    public void setRenderCallback(RenderCallback cb) {
        this.rcb = cb;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        T item = data.get(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song, parent, false);
//        }

        return this.rcb.render(position, convertView, parent, getContext(), item);
//        return super.getView(position, convertView, parent);
    }
}

