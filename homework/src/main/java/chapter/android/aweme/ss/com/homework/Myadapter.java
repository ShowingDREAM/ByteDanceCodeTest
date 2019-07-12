package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private List<String> strings;

    public Myadapter(List<String> temp) {

        strings = temp;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.im_list_item,viewGroup,false);
        return  new ViewHolder(view);
    }
//123123
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final String temp = strings.get(i);
        viewHolder.tvdescription.setText(temp);
        viewHolder.tvdescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),temp,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
         TextView tvtitle;
         TextView tvdescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tv_title);
            tvdescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
