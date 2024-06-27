package fpoly.acount.ph56623_thi19;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.acount.ph56623_thi19.Model.XeMay;

public class MainActivity extends AppCompatActivity {
    TextInputEditText name, price, tinhtrang;
    Button btn_save, btn_cancel;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        context = this;
        name = findViewById(R.id.edt_name);
        tinhtrang = findViewById(R.id.edt_tinhtrang);
        price = findViewById(R.id.edt_price);
        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        Intent intent = getIntent();
        if (intent.hasExtra("ten")) {
            name.setText(intent.getStringExtra("ten"));
        }
        if (intent.hasExtra("gia")) {
            price.setText(String.valueOf(intent.getIntExtra("gia", 0)));
        }
        if (intent.hasExtra("tinhtrang")) {
            tinhtrang.setText(intent.getStringExtra("tinhtrang"));
        }

        btn_save.setOnClickListener(v -> {
            String name_ = name.getText().toString().trim();
            String price_ = price.getText().toString().trim();
            String tinhtrang_ = tinhtrang.getText().toString().trim();
            if (name_.isEmpty() || price_.isEmpty() || tinhtrang_.isEmpty()) {
                Toast.makeText(context, "Không được để trống các trường", Toast.LENGTH_SHORT).show();
                return;
            }
            int priceValue = Integer.parseInt(price_);
            if (priceValue > 5000000) {
                Toast.makeText(context, "Giá xe máy không được vượt quá 5,000,000", Toast.LENGTH_SHORT).show();
                return;
            }

            XeMay update = new XeMay(name_, priceValue, R.drawable.ic_launcher_foreground, tinhtrang_);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("UpdateData", update);
            setResult(RESULT_OK, returnIntent);
            finish();
        });

        btn_cancel.setOnClickListener(v -> finish());
    }
    private boolean isValidSize(String size) {
        return size.equals("L") || size.equals("M") || size.equals("S") || size.equals("XL");
    }
}
