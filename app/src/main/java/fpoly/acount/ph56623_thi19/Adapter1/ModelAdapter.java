package fpoly.acount.ph56623_thi19.Adapter1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.acount.ph56623_thi19.Moder1.Model;
import fpoly.acount.ph56623_thi19.R;

public class ModelAdapter extends ArrayAdapter<Model> {
    public ModelAdapter( Context context, List<Model> models) {
        super(context, 0, models);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(getContext())
                    .inflate(R.layout.item_view,parent,false);
        }
        Model model=getItem(position);
        //lay dlieu tren item
        TextView tvTile=convertView.findViewById(R.id.item_Title);
        TextView tvDes=convertView.findViewById(R.id.item_Description);
        //dua dlieu vao tung t phan
        tvTile.setText(model.getTitle());
        tvDes.setText(model.getDescription());
        return  convertView;
    }
}
