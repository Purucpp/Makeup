package com.yesandroid.makeup;







        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;
        import com.yesandroid.makeup.api.MakeItem;
        import com.yesandroid.makeup.second.MainActivity2;
        import com.yesandroid.makeup.second.SingleClick;

        import java.util.LinkedList;


public class MakeupListAdapter extends RecyclerView.Adapter<MakeupListAdapter.MakeupListViewHolder> {

    private final LinkedList<MakeItem> makeupList;
    private final LayoutInflater mInflater;

    class MakeupListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        public final TextView desc;
        public final ImageView makeupImageView;
       public final MakeupListAdapter mAdapter;


        public MakeupListViewHolder(View itemView, MakeupListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            desc = itemView.findViewById(R.id.desc);
            makeupImageView=itemView.findViewById(R.id.image);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in makeupList.
          //  String element = makeupList.get(mPosition);
            SingleClick singleClick=SingleClick.getInstance();
            singleClick.setMakeItem(makeupList.get(mPosition));

            Intent intent = new Intent(view.getContext(), MainActivity2.class);


            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().getApplicationContext().startActivity(intent);
            // Change the word in the makeupList.

         //   makeupList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }

    public MakeupListAdapter(Context context, LinkedList<MakeItem> wordList) {
        mInflater = LayoutInflater.from(context);
        this.makeupList = wordList;
    }


    @Override
    public MakeupListViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.makeuplist_item, parent, false);
        return new MakeupListViewHolder(mItemView, this);
    }


    @Override
    public void onBindViewHolder(MakeupListViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        String mCurrent = makeupList.get(position).getName();

        // Add the data to the view holder.
        holder.wordItemView.setText(mCurrent);
        holder.desc.setText(makeupList.get(position).getType());
       // Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.makeupImageView);
        Picasso.get().load(makeupList.get(position).getUrl()).into(holder.makeupImageView);
    }


    @Override
    public int getItemCount() {
        return makeupList.size();
    }
}
