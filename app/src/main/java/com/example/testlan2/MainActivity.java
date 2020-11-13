package com.example.testlan2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    Context context;
    RelativeLayout gridviewdata;
    ArrayList<ProductModel> productData;
    ProductAdapter productAdapter;
    ProductModel productModel;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        //getviews
        gridView = findViewById(R.id.gridview);
        gridviewdata = (RelativeLayout) findViewById(R.id.gridviewdata);
        productData = new ArrayList<>();

        //add Countries Data
        populateProductData();
        gridView.setOnItemLongClickListener(new ItemLongClickRemove());
        productAdapter = new ProductAdapter(context,productData);
        gridView.setAdapter(productAdapter);
        registerForContextMenu(gridView);
        gridView = findViewById(R.id.gridview);
        gridView.setOnItemLongClickListener(new ItemLongClickRemove());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context,productData.get(position).getNamedh(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",productData.get(position).getNamedh());
                intent.putExtra("image",productData.get(position).getImages());
                intent.putExtra("gia",productData.get(position).getGiadh());
                startActivity(intent);
            }
        });

    }
    private void populateProductData() {
        //product1
        productModel = new ProductModel();
        productModel.setId(1);
        productModel.setNamedh("Đồng hồ Casio Nam Chính Hãng Anh Khuê MTP-VT01L");
        productModel.setImages(R.drawable.dh1);
        productModel.setGiadh("987.000 VND");
        productData.add(productModel);

        //product2
        productModel = new ProductModel();
        productModel.setId(2);
        productModel.setNamedh("Đồng hồ nam sunrise 1115sa full box, , dây kim loại");
        productModel.setImages(R.drawable.dh2);
        productModel.setGiadh("786.000 VND");
        productData.add(productModel);

        //product3
        productModel = new ProductModel();
        productModel.setId(3);
        productModel.setNamedh("Đồng hồ thể thao nam G-SHOCK GA110 ");
        productModel.setImages(R.drawable.dh3);
        productModel.setGiadh("599.000 VND");
        productData.add(productModel);

        //product4
        productModel = new ProductModel();
        productModel.setId(4);
        productModel.setNamedh("[MỚI 2020] đồng hồ nam cao cấp GREEN STORE-CRNAIRA C898");
        productModel.setImages(R.drawable.dh4);
        productModel.setGiadh("650.000 VND");
        productData.add(productModel);

        //product5
        productModel = new ProductModel();
        productModel.setId(5);
        productModel.setNamedh("Đồng hồ Nam NIBOSI LOGAN Dây Da Cao Cấp – Chạy Full 6 Kim – Chống Nước Cực Tốt");
        productModel.setImages(R.drawable.dh5);
        productModel.setGiadh("900.000 VND");
        productData.add(productModel);

        //product6
        productModel = new ProductModel();
        productModel.setId(6);
        productModel.setNamedh("Đồng hồ thể thao G Shock ⚡FreeShip⚡ Kim điện tử");
        productModel.setImages(R.drawable.dh6);
        productModel.setGiadh("300.000 VND");
        productData.add(productModel);
    }
    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bạn có muốn xóa sản phẩm này?");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ
                    productData.remove(position);
                    //cập nhật lại gridview
                    productAdapter.notifyDataSetChanged();
                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
}