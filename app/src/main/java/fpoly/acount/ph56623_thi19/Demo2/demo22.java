package fpoly.acount.ph56623_thi19.Demo2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.acount.ph56623_thi19.R;

public class demo22 extends AppCompatActivity {
    private EditText txtContent,txtDate,txtType;
    private TextView txtTitle;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private TodoDao todoDao;
    private List<Todo> todoList;
    private Todo currentEditingTodo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo22);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtTitle = findViewById(R.id.demo2_txtTitle);
        txtContent = findViewById(R.id.demo2_txtcontent);
        txtDate = findViewById(R.id.demo2_txtDate);
        txtType = findViewById(R.id.demo2_txtType);
        btnAdd=findViewById(R.id.btnAdd2);
        recyclerView=findViewById(R.id.demo2_recyclerView);
        todoDao=new TodoDao(this);
        todoList=todoDao.getAllTodos();
        adapter=new TodoAdapter(todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(demo22.this));
        recyclerView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentEditingTodo==null){
                    addTodo();
                }else {
                    updateTodo();
                }
            }
        });
        adapter.setOnItemClickListener(new TodoAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteTodo(position);
            }

            @Override
            public void onEditClick(int position) {
                editTodo(position);
            }

            @Override
            public void onStatusChange(int position, boolean inDone) {

            }
        });
    }
    private void updateTodoStartus(int pos,boolean isDone){
        Todo todo=todoList.get(pos);
        todo.setStatus(isDone?1:0);
        todoDao.updateTodoStatus(todo.getId(),todo.getStatus());
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemChanged(pos);
            }
        });
        Toast.makeText(this, "update thanh cong ", Toast.LENGTH_SHORT).show();
    }
    private  void addTodo(){
        String title=txtTitle.getText().toString().trim();
        String content=txtContent.getText().toString().trim();
        String date=txtDate.getText().toString().trim();
        String type=txtType.getText().toString().trim();
        Todo todo=new Todo(0,0,title,content,date,type);
        todoDao.addTodo(todo);
        todoList.add(todo);
        adapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
        clearFields();
    }
    private void updateTodo(){
        String title=txtTitle.getText().toString().trim();
        String content=txtContent.getText().toString().trim();
        String date=txtDate.getText().toString().trim();
        String type=txtType.getText().toString().trim();
        currentEditingTodo.setTitle(title);
        currentEditingTodo.setContent(content);
        currentEditingTodo.setData(date);
        currentEditingTodo.setType(type);
        todoDao.updateTodo(currentEditingTodo);
        int pos=todoList.indexOf(currentEditingTodo);
        adapter.notifyItemChanged(pos);
        currentEditingTodo=null;
        btnAdd.setText("Add");
        clearFields();
    }
    private void editTodo(int pos){
        currentEditingTodo=todoList.get(pos);
        txtTitle.setText(currentEditingTodo.getTitle());
        txtContent.setText(currentEditingTodo.getContent());
        txtDate.setText(currentEditingTodo.getData());
        txtType.setText(currentEditingTodo.getType());
        btnAdd.setText("Update");
    }
    private void deleteTodo(int pos){
        Todo todo=todoList.get(pos);
        todoDao.deleteTodo(todo.getId());
        todoList.remove(todo);
        adapter.notifyItemRemoved(pos);
    }
    private void clearFields(){
        txtTitle.setText("");
        txtContent.setText("");
        txtDate.setText("");
        txtType.setText("");
    }
}