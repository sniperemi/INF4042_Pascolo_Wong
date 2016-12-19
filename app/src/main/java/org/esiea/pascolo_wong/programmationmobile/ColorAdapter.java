package org.esiea.pascolo_wong.programmationmobile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        return colorH;
    }

    @Override
    public void onBindViewHolder(ColorHolder holder, int position) {

        String itemName = null;
        String itemHexa = null;
        String itemRGB = null;

        try
        {
            itemName = colors.getJSONObject(position).getString("name");
            itemHexa = colors.getJSONObject(position).getString("hex");
            itemRGB = colors.getJSONObject(position).getString("rgb");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        holder.name.setText(itemName);
        holder.hexaCode.setText(itemHexa);
        holder.itemView.setBackgroundColor(Color.parseColor(itemHexa));
    }

    @Override
    public int getItemCount() {
        return colors.length();
    }

    class ColorHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView hexaCode;

        public ColorHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.rv_color_element_name);
            hexaCode = (TextView)itemView.findViewById(R.id.rv_color_element_hexa);
        }

    }

    public void showHexa()
    {
        //Toast.makeText(this,getHexa(), Toast.LENGTH_LONG).show();
    }
}
