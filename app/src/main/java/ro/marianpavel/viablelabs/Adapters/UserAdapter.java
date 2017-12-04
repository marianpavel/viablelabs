package ro.marianpavel.viablelabs.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ro.marianpavel.viablelabs.POJO.HumanPOJO;
import ro.marianpavel.viablelabs.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<HumanPOJO> mDataset;
    private Context context;
    private Calendar calendar;
    private int currentYear;
    private SimpleDateFormat simpleDateFormat;

    public UserAdapter(Context context, List<HumanPOJO> myDataset) {
        mDataset = myDataset;
        this.context = context;
        calendar = Calendar.getInstance();
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        HumanPOJO human = mDataset.get(holder.getAdapterPosition());
        StringBuilder fullName = new StringBuilder(human.getName().getTitle() + " " + human.getName().getFirst() + " " + human.getName().getLast());
        holder.name.setText(fullName);

        try {
            Date date = simpleDateFormat.parse(human.getDob());
            calendar.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int age = currentYear - calendar.get(Calendar.YEAR);
        holder.age.setText("Age: " + age);

        Glide.with(context)
                .load(human.getPicture().getThumbnail())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.profilePicture);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView name, age;
        ImageView profilePicture, flag;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            age = v.findViewById(R.id.age);
            profilePicture = v.findViewById(R.id.profilePicture);
            flag = v.findViewById(R.id.countryFlag);
        }
    }
}