package ro.marianpavel.viablelabs.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mukesh.countrypicker.Country;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ro.marianpavel.viablelabs.Interfaces.UserListener;
import ro.marianpavel.viablelabs.POJO.HumanPOJO;
import ro.marianpavel.viablelabs.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<HumanPOJO> mDataset;
    private Context context;
    private Calendar calendar;
    private int currentYear;
    private SimpleDateFormat simpleDateFormat;
    private UserListener listener;

    /**
     * @param context - used for Glide library to fetch images from the given url
     * @param myDataset - the dataset, in our case the list of humanPOJO
     * @param listener - the listener to trigger click event
     */
    public UserAdapter(Context context, List<HumanPOJO> myDataset, UserListener listener) {
        mDataset = myDataset;
        this.context = context;
        this.listener = listener;

        calendar = Calendar.getInstance(); //Initialize calendar and simpledateformat in constructor for better performance
        currentYear = calendar.get(Calendar.YEAR);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_adapter, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final HumanPOJO human = mDataset.get(holder.getAdapterPosition());
        StringBuilder fullName = new StringBuilder(human.getName().getTitle() + " " + human.getName().getFirst() + " " + human.getName().getLast());
        holder.name.setText(fullName);

        try {
            Date date = simpleDateFormat.parse(human.getDob());
            calendar.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int age = currentYear - calendar.get(Calendar.YEAR);
        holder.age.setText(context.getString(R.string.age, String.valueOf(age)));

        Glide.with(context)
                .load(human.getPicture().getThumbnail())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.profilePicture);

        Glide.with(context)
                .load(Country.getCountryByISO(human.getNat()).getFlag())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.flag);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, age;
        ImageView profilePicture, flag;
        ConstraintLayout container;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            age = v.findViewById(R.id.age);
            profilePicture = v.findViewById(R.id.profilePicture);
            flag = v.findViewById(R.id.countryFlag);
            container = v.findViewById(R.id.container);
        }
    }
}