package fpoly.acount.ph56623_thi19.Demo2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.acount.ph56623_thi19.R;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todoList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);

        void onEditClick(int position);

        void onStatusChange(int position, boolean inDone);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public TodoAdapter(List<Todo> todoList) {
        this.todoList = todoList;
    }
    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemdemo2,parent,false);
        return new TodoViewHolder(view,listener);
    }
    //gan data
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Todo currentTodo=todoList.get(position);//lay ve vtri htai
        //dien dlieu vao trg title
        holder.tvTodoName.setText(currentTodo.getTitle());
        //dien dlieu vao trg checkbox
        holder.checkBox.setChecked(currentTodo.getStatus()==1);
        //xu li skien checkbox
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(listener!=null){
                    listener.onStatusChange(position,isChecked);
                }
            }
        });
    }
    //lay tong item
    @Override
    public int getItemCount() {
        return todoList.size();
    }
    public  static class TodoViewHolder extends RecyclerView.ViewHolder{
        TextView tvTodoName;
        CheckBox checkBox;
        Button btnDelete,btnEdit;

        public TodoViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            //anh xa
            tvTodoName=itemView.findViewById(R.id.demo2_item_edit);
            checkBox=itemView.findViewById(R.id.demo2_checkBox);
            btnDelete=itemView.findViewById(R.id.demo2_item_Delete);
            btnEdit=itemView.findViewById(R.id.demo2_item_edit);
            //xu li sk
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!= null){
                        int pos=getAdapterPosition();
                        if (pos!=RecyclerView.NO_POSITION){
                         listener.onDeleteClick(pos);
                        }
                    }
                }
            });
            //xli skien edit
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int pos=getAdapterPosition();
                        if (pos!=RecyclerView.NO_POSITION){
                            listener.onEditClick(pos);
                        }
                    }
                }
            });
        }
    }
}
