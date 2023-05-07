package com.example.windowcalculator;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //자바 개인 변수 설정
    String progress = "";
    String progress_main = "";
    String number1 = "";
    String number2 = "";
    String number3 = "";
    String memory_string = "";
    String Memory_number1 = "";
    String Memory_number2 = "";

    NumberFormat format = NumberFormat.getInstance();
    int type;

    int Sum = 0;
    int Sub = 1;
    int Mul = 2;
    int Div = 3;
    int Mod = 4;
    int Ce = 5;
    int C = 6;
    double Exp = 1;
    double Squ = 0;
    double Root = 0;
    double num1;
    double num2;
    double num3;
    double memory_double;

    double num_memory1;

    double num_memory2;

    double result_set;

    //액티비티 변수 선언
    TextView Txt_pro, Txt_Main;
    Button btnMC, btnMR, btnMsum, btnMsub, btnMS, btnMod, btnCE, btnC,
            btnDel, btnExp, btnSqu, btnRoot, btnDiv, btnMul, btnSub, btnSum, btnPLMA,btnEqu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액티비티 입력 변수설정
        Txt_pro = (TextView) findViewById(R.id.Txt_Pro);
        Txt_Main = (TextView) findViewById(R.id.Txt_Main);
        btnMC = (Button) findViewById(R.id.btnMC);
        btnMR = (Button) findViewById(R.id.btnMR);
        btnMsum = (Button) findViewById(R.id.btnMsum);
        btnMsub = (Button) findViewById(R.id.btnMsub);
        btnMS = (Button) findViewById(R.id.btnMS);
        btnMod = (Button) findViewById(R.id.btnMod);
        btnCE = (Button) findViewById(R.id.btnCE);
        btnC = (Button) findViewById(R.id.btnC);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnExp = (Button) findViewById(R.id.btnExp);
        btnSqu = (Button) findViewById(R.id.btnSqu);
        btnRoot = (Button) findViewById(R.id.btnRoot);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnPLMA = (Button) findViewById(R.id.btnPLMA);
        btnEqu = (Button) findViewById(R.id.btnEqu);


        btnSum.setOnClickListener(mListener);
        btnSub.setOnClickListener(mListener);
        btnMul.setOnClickListener(mListener);
        btnDiv.setOnClickListener(mListener);
        btnMod.setOnClickListener(mListener);
        btnEqu.setOnClickListener(mListener);
        btnDel.setOnClickListener(mListener);

        btnMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((Txt_Main.getText().toString().isEmpty() == false) &&
                        (Txt_Main.getText().toString() != "숫자를 입력하시오") &&
                        (Txt_Main.getText().toString() != "0으로 나눌 수 없습니다.")) {
                    memory_string = Txt_Main.getText().toString();
                    memory_double = Double.parseDouble(memory_string);

                    NumberFormat format1 = NumberFormat.getInstance();
                    format1.setGroupingUsed(false);
                    memory_string = format1.format(memory_double);

                    btnMC.setEnabled(true);
                    btnMC.setClickable(true);
                    btnMR.setEnabled(true);
                    btnMR.setClickable(true);

                    Toast.makeText(getApplicationContext(), "메모리:" + memory_string, Toast.LENGTH_SHORT).show();
                }
                else if((Txt_Main.getText().toString().isEmpty() == true)||
                        (Txt_Main.getText().toString() == "숫자를 입력하시오")||
                        (Txt_Main.getText().toString() == "0으로 나눌 수 없습니다.")){
                    Txt_Main.setText("숫자를 입력하시오");
                }

            }
        });
        btnMsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(memory_string.isEmpty() == false) {
                    Memory_number2 = Txt_Main.getText().toString();
                    num_memory2 = Double.parseDouble(Memory_number2);

                    NumberFormat format2 = NumberFormat.getInstance();
                    format2.setGroupingUsed(false);

                    memory_double = memory_double - num_memory2;
                    memory_string = format2.format(memory_double);

                    Toast.makeText(getApplicationContext(), "메모리:" + memory_string, Toast.LENGTH_SHORT).show();
                }
                else
                    return;
            }
        });

        btnMsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(memory_string.isEmpty() == false) {
                    Memory_number2 = Txt_Main.getText().toString();
                    num_memory2 = Double.parseDouble(Memory_number2);

                    NumberFormat format2 = NumberFormat.getInstance();
                    format2.setGroupingUsed(false);

                    memory_double = memory_double + num_memory2;
                    memory_string = format2.format(memory_double);

                    Toast.makeText(getApplicationContext(), "메모리:" + memory_string, Toast.LENGTH_SHORT).show();
                }
                else
                    return;
            }
        });
        btnMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Txt_Main.setText(""+memory_string);
            }
        });
        btnMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory_string = "";
                memory_double = 0;

                btnMC.setEnabled(false);
                btnMC.setClickable(false);
                btnMR.setEnabled(false);
                btnMR.setClickable(false);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Txt_Main.setText("");
                Txt_pro.setText("");
                progress = number1= number2 ="";
            }
        });

        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Txt_Main.setText("");
            }
        });

        btnPLMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((Txt_Main.getText().toString().isEmpty() == false) &&
                        (Txt_Main.getText().toString() != "숫자를 입력하시오") &&
                        (Txt_Main.getText().toString() != "0으로 나눌 수 없습니다.")) {
                    if ((Double.parseDouble(Txt_Main.getText().toString())) - (int) Double.parseDouble(Txt_Main.getText().toString()) == 0.0) {
                        Txt_Main.setText("" + (Integer.parseInt(Txt_Main.getText().toString()) * -1));
                    } else {
                        Txt_Main.setText("" + (Double.parseDouble(Txt_Main.getText().toString()) * -1));
                    }
                }
                else if((Txt_Main.getText().toString().isEmpty() == true)||
                        (Txt_Main.getText().toString() == "숫자를 입력하시오")||
                        (Txt_Main.getText().toString() == "0으로 나눌 수 없습니다.")){
                    Txt_Main.setText("숫자를 입력하시오");
                }
            }
        });

        btnExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Txt_Main.getText().toString().isEmpty() == false)&&
                        (Txt_Main.getText().toString() != "숫자를 입력하시오")&&
                        (Txt_Main.getText().toString() != "0으로 나눌 수 없습니다.")){
                        number1 = Txt_Main.getText().toString();
                        num1 = Double.parseDouble(number1);
                        NumberFormat format = NumberFormat.getInstance();
                        format.setGroupingUsed(false);

                        Txt_pro.setText("1/" + number1);
                        Txt_Main.setText("" + format.format(1 / num1));
                        result_set = 1/num1;
                    if (num1 == 0)
                        Txt_Main.setText("0으로 나눌 수 없습니다.");
                    }
                else if((Txt_Main.getText().toString().isEmpty() == true)||
                        (Txt_Main.getText().toString() == "숫자를 입력하시오")||
                        (Txt_Main.getText().toString() == "0으로 나눌 수 없습니다.")){
                    Txt_Main.setText("숫자를 입력하시오");
                }
            }
        });

        btnRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Txt_Main.getText().toString().isEmpty() == false)&&
                        (Txt_Main.getText().toString() != "숫자를 입력하시오")&&
                        (Txt_Main.getText().toString() != "0으로 나눌 수 없습니다.")){
                    number1 = Txt_Main.getText().toString();
                    num1 = Double.parseDouble(number1);
                    NumberFormat format = NumberFormat.getInstance();
                    format.setGroupingUsed(false);

                    Txt_pro.setText("√" + number1);
                    Txt_Main.setText("" + format.format(sqrt(num1)));
                    result_set = sqrt(num1);
                }
                else if((Txt_Main.getText().toString().isEmpty() == true)||
                        (Txt_Main.getText().toString() == "숫자를 입력하시오")||
                        (Txt_Main.getText().toString() == "0으로 나눌 수 없습니다.")){
                    Txt_Main.setText("숫자를 입력하시오");
                }
            }
        });

        btnSqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Txt_Main.getText().toString().isEmpty() == false)&&
                        (Txt_Main.getText().toString() != "숫자를 입력하시오")&&
                        (Txt_Main.getText().toString() != "0으로 나눌 수 없습니다.")){
                    number1 = Txt_Main.getText().toString();
                    num1 = Double.parseDouble(number1);
                    NumberFormat format = NumberFormat.getInstance();
                    format.setGroupingUsed(false);
                    Txt_pro.setText(number1 + "^2");
                    Txt_Main.setText("" + format.format(num1 * num1));
                    result_set = num1*num1;
                }
                else if((Txt_Main.getText().toString().isEmpty() == true)||
                        (Txt_Main.getText().toString() == "숫자를 입력하시오")||
                        (Txt_Main.getText().toString() == "0으로 나눌 수 없습니다.")){
                    Txt_Main.setText("숫자를 입력하시오");
                }
            }
        });
    }

    Button.OnClickListener mListener = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            if((Txt_Main.getText().toString().isEmpty() == false)&&
                    (Txt_Main.getText().toString() != "숫자를 입력하시오")&&
                    (Txt_Main.getText().toString() != "0으로 나눌 수 없습니다.")) {
                switch (view.getId()) {
                    case R.id.btnSum:
                        number1 = Txt_Main.getText().toString();
                        progress = number1 + "+";
                        Txt_pro.setText(progress);
                        Txt_Main.setText("");

                        type = Sum;
                        break;
                    case R.id.btnSub:
                        number1 = Txt_Main.getText().toString();
                        progress = Txt_Main.getText().toString() + "-";
                        Txt_pro.setText(progress);
                        Txt_Main.setText("");

                        type = Sub;
                        break;
                    case R.id.btnMul:
                        number1 = Txt_Main.getText().toString();
                        progress = Txt_Main.getText().toString() + "*";
                        Txt_pro.setText(progress);
                        Txt_Main.setText("");

                        type = Mul;
                        break;
                    case R.id.btnDiv:
                        number1 = Txt_Main.getText().toString();
                        progress = Txt_Main.getText().toString() + "/";
                        Txt_pro.setText(progress);
                        Txt_Main.setText("");

                        type = Div;
                        break;
                    case R.id.btnMod:
                        number1 = Txt_Main.getText().toString();
                        progress = Txt_Main.getText().toString() + "%";
                        Txt_pro.setText(progress);
                        Txt_Main.setText("");

                        type = Mod;
                        break;
                    case R.id.btnDel:
                        String DelNum = Txt_Main.getText().toString();
                        if (DelNum.length() == 0)
                            break;
                        else if (DelNum == "0으로 나눌 수 없습니다.") {
                            break;
                        } else {
                            Txt_Main.setText(DelNum.substring(0, DelNum.length() - 1));
                            break;
                        }

                    case R.id.btnEqu:
                        number2 = Txt_Main.getText().toString();
                        progress_main = progress + Txt_Main.getText().toString() + "=";
                        Txt_pro.setText(progress_main);

                        if ((number1.isEmpty() == true)||(number2.isEmpty() == true)){
                            Txt_pro.setText(""+Txt_Main.getText().toString());
                            break;
                        }

                        Txt_Main.setText(cal(type));

                        number1 = Txt_Main.getText().toString();
                        break;
                }
            }
            else if((Txt_Main.getText().toString().isEmpty() == true)||
                    (Txt_Main.getText().toString() == "숫자를 입력하시오")||
                    (Txt_Main.getText().toString() == "0으로 나눌 수 없습니다.")){
                Txt_Main.setText("숫자를 입력하시오");
            }
        }
    };
    String cal(int cal_type){
        String cal_result = "";
        double res = 0;
        num1 = Double.parseDouble(number1);
        num2 = Double.parseDouble(number2);

        format.setGroupingUsed(false);

        if (cal_type == Sum) {
            res = num1 + num2;
            //cal_result = format.format(num1 + num2);

        } else if (cal_type == Sub) {
            res = num1 - num2;
        } else if (cal_type == Mul) {
            res = num1 * num2;
        } else if (cal_type == Div) {
            if (num2 == 0)
                cal_result = "0으로 나눌 수 없습니다.";
            else {
                res = num1 / num2;
            }
        } else if (cal_type == Mod) {
            res = num1 % num2;
        }
        cal_result = Double.toString(res);
        num1 = res;

        return cal_result;
    }

    public void onClick (View view){
        if((Txt_Main.getText().toString() != "숫자를 입력하시오")&&
                (Txt_Main.getText().toString() != "0으로 나눌 수 없습니다.")) {
            switch (view.getId()) {
                case R.id.btn0:
                    Txt_Main.setText(Txt_Main.getText().toString() + 0);
                    break;
                case R.id.btn1:
                    Txt_Main.setText(Txt_Main.getText().toString() + 1);
                    break;
                case R.id.btn2:
                    Txt_Main.setText(Txt_Main.getText().toString() + 2);
                    break;
                case R.id.btn3:
                    Txt_Main.setText(Txt_Main.getText().toString() + 3);
                    break;
                case R.id.btn4:
                    Txt_Main.setText(Txt_Main.getText().toString() + 4);
                    break;
                case R.id.btn5:
                    Txt_Main.setText(Txt_Main.getText().toString() + 5);
                    break;
                case R.id.btn6:
                    Txt_Main.setText(Txt_Main.getText().toString() + 6);
                    break;
                case R.id.btn7:
                    Txt_Main.setText(Txt_Main.getText().toString() + 7);
                    break;
                case R.id.btn8:
                    Txt_Main.setText(Txt_Main.getText().toString() + 8);
                    break;
                case R.id.btn9:
                    Txt_Main.setText(Txt_Main.getText().toString() + 9);
                    break;
                case R.id.btnDot:
                    Txt_Main.setText(Txt_Main.getText().toString() + ".");
                    break;
            }
        }
    }
}