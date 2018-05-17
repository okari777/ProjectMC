package com.example.okari.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String[] menu = {
            "1元小薯","10元麥香魚","1元冰淇淋","澳美客","麵線","麵疙瘩","燒肉","排骨酥","水果","排骨飯","清蒸鱸魚","竹筍炒肉絲","雞腿飯","碗糕","炒高麗菜"
    };
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            String info = bundle.getString("picture");
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("Your choice");
            dialog.setMessage("you choose " + info);
            dialog.setNeutralButton("Got it", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this,"I got it",Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }
        bt = (Button) findViewById(R.id.bt);
        //get click event
        bt.setOnClickListener(buttonlistener);

    }
    //when get click event, do what you want
    private Button.OnClickListener buttonlistener = new Button.OnClickListener(){
        @Override
        //actually do thing when you click
        public void onClick(View v){
            //宣告新視窗清單
            AlertDialog.Builder dialog_list = new AlertDialog.Builder(MainActivity.this);
            //設定對話框標題
            dialog_list.setTitle("prefer picture");
            dialog_list.setIcon(R.drawable.nike);
            //設定對話框內容
            dialog_list.setItems(menu, new DialogInterface.OnClickListener() {
                @Override
                //actually do thing when you click.
                //which 變數用來取得你所點的選項
                public void onClick(DialogInterface dialog, int which) {
                    //頁面切換的宣告方式
                    Intent intent = new Intent();
                    //設定切換到哪個新頁面
                    intent.setClass(MainActivity.this, Main2Activity.class);
                    //不同頁面之間 若要傳遞參數 則需邀選告Bundle class變數
                    Bundle bundle = new Bundle();
                    //設定要傳遞何種type資料 這裡以String為例 並且設定一個string key值 "picture"接你所選的選項
                    bundle.putString("picture",menu[which]);
                    //傳遞出去
                    intent.putExtras(bundle);
                    //正式切換頁面
                    startActivity(intent);
                }
            });
            //當dialog_list設定完要顯示的內容後 才秀出對話框 若沒有dialog_list.show() 則不會跳出對話框
            dialog_list.show();
        }
    };
}
