package app.gitsquare.com.gitsquare.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import app.gitsquare.com.gitsquare.R;
import app.gitsquare.com.gitsquare.model.Model_Contrib;


/**
 * Created by admin on 09-09-2016.
 */
public class ContribAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Activity activity;

    private ArrayList<Model_Contrib> ALlProducts = new ArrayList<>();

    private String TAG = "CONTRIB";


    public ContribAdapter(Activity act, ArrayList<Model_Contrib> list) {
        activity = act;
        ALlProducts = list;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contrib, parent, false);
        return new VHItems(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        final Model_Contrib model = ALlProducts.get(position);

        String str_img_path = model.getImagePath().trim();

        if (str_img_path != null && !str_img_path.equals("")) {
            Glide.with(activity)
                    .load(str_img_path)
                    .into(((VHItems) holder).imgPic);

        }

        ((VHItems) holder).txtloginName.setText(model.getLogin());

        ((VHItems) holder).txtContribution.setText("Contribution : "+model.getContribution());

        ((VHItems) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return ALlProducts.size();
    }

    class VHItems extends RecyclerView.ViewHolder {


        private TextView txtloginName;
        private TextView txtContribLink;
        private TextView txtContribution;
        private CircularImageView imgPic;

        public VHItems(View convertView) {
            super(convertView);

            txtloginName = (TextView) convertView.findViewById(R.id.textview_loing_name);
            txtContribLink = (TextView) convertView.findViewById(R.id.textviewContributionLink);
            txtContribution = (TextView) convertView.findViewById(R.id.textviewContribution);
            imgPic = (CircularImageView) convertView.findViewById(R.id.imageview_profile_pic);

        }
    }


}
