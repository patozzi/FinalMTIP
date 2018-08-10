package android.example.com.mtipmedicaldictionary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final TermAdapterOnClickHandler mTermClickHandler;

    public interface TermAdapterOnClickHandler {
        void onTermClick(ListItem item);
    }

    private List<ListItem> listItems;


    public MyAdapter(TermAdapterOnClickHandler TermClickHandler){
        mTermClickHandler = TermClickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.parent_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

//        holder.textViewTerm.setText(listItem.getTerm());
//        holder.textViewDefinition.setText(listItem.getDefinition());
//        holder.textViewSymptoms.setText(listItem.getSymptoms());
//        holder.textViewTreatment.setText(listItem.getTreatment());
//        holder.textViewMusicalTechniques.setText(listItem.getMusical_techniques());
//

        holder.textTerm.setText(listItem.getTerm());

    }

    public void setListItems(List<ListItem>items) {
        listItems = items;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        if (null == listItems) return 0;
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView textTerm;
//        public TextView textViewDefinition;
//        public TextView textViewSymptoms;
//        public TextView textViewTreatment;
//        public TextView textViewMusicalTechniques;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTerm = (TextView) itemView.findViewById(R.id.textTerm);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            ListItem item = listItems.get(adapterPosition);
            mTermClickHandler.onTermClick(item);


        }

    }
}
