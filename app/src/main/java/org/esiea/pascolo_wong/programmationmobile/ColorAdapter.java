package org.esiea.pascolo_wong.programmationmobile;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rémi on 18/12/2016.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorHolder>
{
    private final LayoutInflater inflater;
    private JSONArray colors = null;

    public ColorAdapter(Context context, JSONArray jsonArray)
    {
        this.inflater = LayoutInflater.from(context);
        this.colors = jsonArray;
    }

    @Override
    public ColorHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.rv_color_element, parent, false);

        ColorHolder colorH = new ColorHolder(v);
        Log.i("Holder", "Holder créé");
        return colorH;
    }

    @Override
    public void onBindViewHolder(ColorHolder holder, int position) {

        String item = null;
        try
        {
            item = colors.getJSONObject(position).getString("name");
            Log.i("JSON", item);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        holder.name.setText(item);
    }

    @Override
    public int getItemCount() {
        return colors.length();
    }

    class ColorHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private View container;

        public ColorHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.rv_color_element_name);
            container = itemView.findViewById(R.id.itemContainer);
        }

    }
}
