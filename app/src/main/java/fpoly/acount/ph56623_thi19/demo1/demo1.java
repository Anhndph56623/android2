package fpoly.acount.ph56623_thi19.demo1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import fpoly.acount.ph56623_thi19.Adapter1.Helper;
import fpoly.acount.ph56623_thi19.Adapter1.ModelAdapter;
import fpoly.acount.ph56623_thi19.Moder1.Model;
import fpoly.acount.ph56623_thi19.R;

public class demo1 extends AppCompatActivity {
    private ModelAdapter adapter;
    private List<Model> list;
    private EditText txtTitle,txtDes;
    Helper dbHelper;
    private Button btnAdd;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.list_view);
        txtTitle=findViewById(R.id.txt_title);
        txtDes=findViewById(R.id.txt_des);
        btnAdd=findViewById(R.id.btn_add);
        dbHelper=new Helper(this);
        list=dbHelper.getAllData();
        adapter=new ModelAdapter(this,list);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(v -> {
            String title=txtTitle.getText().toString();
            String des=txtDes.getText().toString();
            Model model=new Model(title,des);
           dbHelper.them(model);
           list.clear();
           list.addAll(dbHelper.getAllData());
           adapter.notifyDataSetChanged();
        });
    }
}