package ro.marianpavel.viablelabs.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ro.marianpavel.viablelabs.POJO.HumanPOJO;
import ro.marianpavel.viablelabs.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<HumanPOJO> mDataset;
    private Context context;

    public UserAdapter(Context context, List<HumanPOJO> myDataset) {
        mDataset = myDataset;
        this.context = context;
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

        holder.name.setText(mDataset.get(holder.getAdapterPosition()).getName().getFirst());

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