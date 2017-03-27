package com.example.ghz.supercalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class MainActivity extends Activity implements View.OnClickListener {
    //0~9十个按键
    private Button[]btn = new Button[10];
    private Button btn_seven,btn_eight,btn_nine,btn_four,btn_five,btn_six,btn_one,btn_two,btn_three,btn_zero;
    private TextView Max_Led;//大显示屏
    private  TextView Min_Led;//小显示屏
    private TextView short_textView;//临时做记录的
    private Button btn_two_nd;//2nd
    private Button left,right,dot,bksp;//（）.删除
    private Button btn_percent;//%
    private Button btn_reciprocal;//倒数
    private Button btn_x_square;//x²
    private Button btn_x_cube;//x³
    private Button square;//y^x  平方
    private Button factorial;//阶乘
    private Button sqrt;//开根号
    private Button btn_x_sqrt_y;//x√y
    private Button log,sin,cos,tan,ln;
    private Button sinh,cosh,tanh;
    private Button btn_e_x;//e^x
    private Button drg;//角度弧度控制键
    private Button btn_pai;//π
    private Button btn_ee;//EE
    private Button btn_Rand;//Rand
    private Button btn_mc;//mc
    private Button btn_mdd;//m+
    private Button btn_m_subtract;//m-
    private Button btn_mr;//mr
    private Button c;//input清屏键
    private Button btn_minus;//+/-
    private Button div,mul,sub,add,equal;//÷ × - + =
    //判断是否是按=之后的输入，true表示输入在=之前，false反之
    public boolean equals_flag = true;

    //输入控制，true为重新输入，false为接着输入
    public boolean vbegin = true;

    //true表示正确，可以继续输入；false表示有误，输入被锁定
    public boolean tip_lock = true;

    public boolean sign = false;//  false为接着输入，.+-x/如果在0后面
    public boolean clickdot = false;//对点的开关控制，点只能出现一次，在按Ac时重置
    public boolean operator = false;//运算符标志位
    public boolean mathcount =false;//
    //控制DRG按键，true为角度，false为弧度
    public boolean drg_flag = true;
    //π值：3.14
    public double pi=4*Math.atan(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        short_textView = (TextView) findViewById(R.id.short_textView);
        Max_Led = (TextView) findViewById(R.id.Max_Led);
        Min_Led = (TextView) findViewById(R.id.Min_Led);
        btn_two_nd = (Button) findViewById(R.id.btn_two_nd);
        left = (Button) findViewById(R.id.btn_left_bracket);
        right = (Button) findViewById(R.id.btn_right_bracket);
        btn_percent = (Button) findViewById(R.id.btn_percent);
        btn_reciprocal = (Button) findViewById(R.id.btn_reciprocal);
        btn_x_square = (Button) findViewById(R.id.btn_x_square);
        btn_x_cube = (Button) findViewById(R.id.btn_x_cube);
        square = (Button) findViewById(R.id.btn_y_x);
        factorial = (Button) findViewById(R.id.btn_not_x);
        square = (Button) findViewById(R.id.btn_x_sqrt);
        btn_x_sqrt_y = (Button) findViewById(R.id.btn_x_sqrt_y);
        log = (Button) findViewById(R.id.btn_log);
        sin = (Button) findViewById(R.id.btn_sin);
        cos = (Button) findViewById(R.id.btn_cos);
        tan = (Button) findViewById(R.id.btn_tan);
        ln = (Button) findViewById(R.id.btn_ln);
        sinh = (Button) findViewById(R.id.btn_sinh);
        cosh = (Button) findViewById(R.id.btn_cosh);
        tanh = (Button) findViewById(R.id.btn_tanh);
        btn_e_x = (Button) findViewById(R.id.btn_e_x);
        drg = (Button) findViewById(R.id.btn_Rad);
        btn_pai = (Button) findViewById(R.id.btn_pai);
        btn_ee = (Button) findViewById(R.id.btn_ee);
        btn_Rand = (Button) findViewById(R.id.btn_Rand);
        btn_mc = (Button) findViewById(R.id.btn_mc);
        btn_mdd = (Button) findViewById(R.id.btn_mdd);
        btn_m_subtract = (Button) findViewById(R.id.btn_m_subtract);
        btn_mr = (Button) findViewById(R.id.btn_mr);
        c = (Button) findViewById(R.id.btn_ac);
        bksp = (Button) findViewById(R.id.btn_delete);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        div = (Button) findViewById(R.id.btn_divide);
        mul = (Button) findViewById(R.id.btn_x);
        sub = (Button) findViewById(R.id.btn_subtract);
        add = (Button) findViewById(R.id.btn_add);
        equal = (Button) findViewById(R.id.btn_equalz_to);
        dot = (Button) findViewById(R.id.btn_point);
        btn_zero = (Button) findViewById(R.id.btn_zero);
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);
        btn_five = (Button) findViewById(R.id.btn_five);
        btn_six = (Button) findViewById(R.id.btn_six);
        btn_seven = (Button) findViewById(R.id.btn_seven);
        btn_eight = (Button) findViewById(R.id.btn_eight);
        btn_nine = (Button) findViewById(R.id.btn_nine);
        btn_zero.setOnClickListener(this);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        dot.setOnClickListener(this);
        c.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        equal.setOnClickListener(this);
//        for (int i = 0; i <10 ; i++) {
//            btn[i].setOnClickListener(actionPerformed);
//        }
//        div.setOnClickListener(actionPerformed);
//        mul.setOnClickListener(actionPerformed);
//        sub.setOnClickListener(actionPerformed);
//        add.setOnClickListener(actionPerformed);
//        equal.setOnClickListener(actionPerformed);
//        sin.setOnClickListener(actionPerformed);
//        cos.setOnClickListener(actionPerformed);
//        tan.setOnClickListener(actionPerformed);
//        log.setOnClickListener(actionPerformed);
//        ln.setOnClickListener(actionPerformed);
//        sqrt.setOnClickListener(actionPerformed);
//        square.setOnClickListener(actionPerformed);
//        factorial.setOnClickListener(actionPerformed);
//        bksp.setOnClickListener(actionPerformed);
//        left.setOnClickListener(actionPerformed);
//        right.setOnClickListener(actionPerformed);
//        dot.setOnClickListener(actionPerformed);
//        drg.setOnClickListener(actionPerformed);
//        c.setOnClickListener(actionPerformed);
//    }
    }


    @Override
    public void onClick(View v) {
   switch (v.getId()){
       case R.id.btn_zero:
           String s0 = btn_zero.getText().toString();
           Max_Led.append(s0);
//           String s_0 = Max_Led.getText().toString();
//           Min_Led.setText(s_0);
  //         Min_Led_Show(s0);
           c.setText("C");

         //++++++++++++++++++++++
           String zero =   Max_Led.getText().toString();
           char zero1 = zero.charAt(0);
           char zero2 = zero.charAt(1);
           String zero_1 = String.valueOf(zero1);
           String zero_2 = String.valueOf(zero2);

//     第一个判断语句：比如2后面能跟0，第二个不能出现00.1，
           /**
            public static void main(String[] args){
            //看看这个字符串 里面 有没有t
            String str = "feitianbenyue";   ‘
            String t = "t";
            System.out.println(-1 != str.indexOf(t));
            true
            }
            */
           if(zero_1.indexOf("0")!=-1 && zero_2.equals("0")){
               Max_Led.setText("0");
               //Min_Led.setText("0");
              //Min_Led_Show("");
           }
           break;
       case R.id.btn_one:
           removeZero();
           String s1 = btn_one.getText().toString();
           Max_Led.append(s1);
           Min_Led_Show(s1);
           c.setText("C");

           break;
       case R.id.btn_two:
           removeZero();
           String s2 = btn_two.getText().toString();
           Max_Led.append(s2);
           Min_Led_Show(s2);
           c.setText("C");
           break;
       case R.id.btn_three:
           removeZero();
           String s3 = btn_three.getText().toString();
           Max_Led.append(s3);
           Min_Led_Show(s3);
           c.setText("C");
           break;
       case R.id.btn_four:
           removeZero();
           String s4 = btn_four.getText().toString();
           Max_Led.append(s4);
           Min_Led_Show(s4);
           c.setText("C");
           break;
       case R.id.btn_five:
           removeZero();
           String s5 = btn_five.getText().toString();
           Max_Led.append(s5);
           Min_Led_Show(s5);
           c.setText("C");
           break;
       case R.id.btn_six:
           removeZero();
           String s6 = btn_six.getText().toString();
           Max_Led.append(s6);
           Min_Led_Show(s6);
           c.setText("C");
           break;
       case R.id.btn_seven:
           removeZero();
           String s7 = btn_seven.getText().toString();
           Max_Led.append(s7);
           Min_Led_Show(s7);
           c.setText("C");
           break;
       case R.id.btn_eight:
           removeZero();
           String s8 = btn_eight.getText().toString();
           Max_Led.append(s8);
           Min_Led_Show(s8);
           c.setText("C");
           break;
       case R.id.btn_nine:
           removeZero();
           String s9 = btn_nine.getText().toString();
           Max_Led.append(s9);
           Min_Led_Show(s9);
           c.setText("C");
           break;
       case R.id.btn_point:
           /**
            * 对.的相关说明：
            * TODO 1.当Max_Led显示0时，点击.显示屏显示"0."
            *      实现方式：如果点击".";point_1.equals(".")  Max_Led.setText("0."); Min_Led.setText("0.");
            *      同时，引入一个Boolean变量  boolean sign = false;// false为接着输入，.+-x/如果在0后面
            *      sign起作用的地方：    if (sign==false&&c2.equals("0")){
                                             Max_Led.setText("");
                                                                         }
                   当出现“0.”时如果再输入别的数字时，则会把“0.”清除。。因为：char c1 = first.charAt(0);
                   所以当没输入“0.”时，执行sign==false&&c2.equals("0")
                   当直接点击“.”出现“0.”时，把sign设为true，不执行清零操作：Max_Led.setText("");。
                   需要特别注意：在点击Ac时需要重置sign:  sign=false;//重新归为false,不然当清零后不会执行sign==false&&c2.equals("0")，输入别的数字会出现“0123”
            *TODO 2.一行数字里只能出现一次“.”如“2.33333”;不能出现“2.2.2或者2.....3”
            *       实现方式：引入一个Boolean变量，boolean clickdot = false;//对点的开关控制，点只能出现一次，在按Ac时重置
            *       第一次点击“.”clickdot = false，点击之后设为true。下次点击执行Max_Led.append("");
                                                                                    String s__10 = Max_Led.getText().toString();
                                                                                    Min_Led.setText(s__10);
                                                                                    clickdot=true;
                   需要特别注意：在点击Ac时需要重置clickdot，不然点不了“.”;会执行： Max_Led.append("");
            */
           if (clickdot==false) {
               removeZero();
               String s10 = dot.getText().toString();
               Max_Led.append(s10);
//               String s_10 = Max_Led.getText().toString();
//               Min_Led.setText(s_10);
               Min_Led_Show(s10);
               String point =   Max_Led.getText().toString();
               char point1 =  point.charAt(0);
               String point_1 = String.valueOf(point1);
               if (point_1.equals(".")){
                   Max_Led.setText("0.");
                   Min_Led.setText("0.");
                   sign = true;
               }
               clickdot=true;
           }else {
               Max_Led.append("");
               String s__10 = Max_Led.getText().toString();
               Min_Led.setText(s__10);
           }
           c.setText("C");
           /**
            * 第一位不能为.
            * 点击.---->0.
            * 数字里只能出现一次.
            */

           break;
       case R.id.btn_ac:
           sign=false;//重新归为false,不然第一位下次还是为0
           clickdot=false;
           operator = true;
          // Max_Led.setText("");
           Max_Led.setText("0");
           Min_Led.setText("");
           c.setText("AC");
        break;

       case R.id.btn_add:
           String add1 = add.getText().toString();
           mathcount(add1);
           break;
       case R.id.btn_subtract:
           String sub1 = sub.getText().toString();
           mathcount(sub1);
           break;
       case R.id.btn_x:
           String mul1 = mul.getText().toString();
           mathcount(mul1);
           break;
       case R.id.btn_divide:
           String div1 = div.getText().toString();
           mathcount(div1);
           break;
       case R.id.btn_equalz_to:
//           String equal1 = equal.getText().toString();
//           if (clickdot==false) {
//               Min_Led.append(equal1);
//               clickdot=true;
//           }else {
//               Min_Led.append("");
//           }
           String endMath = Min_Led.getText().toString();

           //变换样子后的式子
          String str_new;
        //   计算算式结果
           str_new = endMath.replaceAll("-", "-1×");
           Log.e("TAG", "onClick: "+str_new );
           new calc().process(str_new);
        break;

   }

    }

    private void removeZero() {
        //把第一个0去掉
        String first =  Max_Led.getText().toString();
        char c1 = first.charAt(0);
        String c2 = String.valueOf(c1);
        if (sign==false&&c2.equals("0")){
            Max_Led.setText("");
        }

    }

    public void Min_Led_Show(String sshow){
        //加减乘除标志位
        mathcount=false;
        if (operator==false) {
            String a1 = Min_Led.getText().toString();
            if (a1.length() >= 11) {
                Min_Led.append("");
            } else {
                Min_Led.append(sshow);
            }
        }else {
            // String name = uri3decode.substring(uri3decode.lastIndexOf("/")+1);//截取最后一个“/”后面的所有字符串
            String a2 = Min_Led.getText().toString();
            String a2_1 = a2.substring(a2.lastIndexOf("+")+1);
            String a2_2 = a2.substring(a2.lastIndexOf("-")+1);
            String a2_3 = a2.substring(a2.lastIndexOf("×")+1);
            String a2_4 = a2.substring(a2.lastIndexOf("÷")+1);

            if (a2_1.length()>=11&&a2_2.length()>=11&&a2_3.length()>=11&&a2_4.length()>=11){
                Min_Led.append("");
            }
            else {
                Min_Led.append(sshow);
            }
        }
    }
    public void mathcount(String matchc){
        short_textView.setText(matchc);
        String b1 = short_textView.getText().toString();
        Log.e("TAG", "mathcount: "+b1);
        if (mathcount==false){
            Min_Led.append(b1);
            mathcount=true;
        }else {
            Min_Led.append("");
        }
        Max_Led.setText("0");
        operator = true;
    }





    /*
      * 整个计算核心，只要将表达式的整个字符串传入calc().process()就可以实行计算了
      * 算法包括以下几部分：
      * 1、计算部分           process(String str)  当然，这是建立在查错无错误的情况下
      * 2、数据格式化      FP(double n)         使数据有相当的精确度
      * 3、阶乘算法           N(double n)          计算n!，将结果返回
      * 4、错误提示          showError(int code ,String str)  将错误返回
      */
    public class calc {
        public calc(){

        }
        final int MAXLEN = 500;
        /*
         * 计算表达式
         * 从左向右扫描，数字入number栈，运算符入operator栈
         * +-基本优先级为1，×÷基本优先级为2，log ln sin cos tan n!基本优先级为3，√^基本优先级为4
         * 括号内层运算符比外层同级运算符优先级高4
         * 当前运算符优先级高于栈顶压栈，低于栈顶弹出一个运算符与两个数进行运算
         * 重复直到当前运算符大于栈顶
         * 扫描完后对剩下的运算符与数字依次计算
         */
        public void process(String str) {
            Log.e("TAG1", "process:+++ "+str);
            int weightPlus = 0, topOp = 0,
                    topNum = 0, flag = 1, weightTemp = 0;
            //weightPlus为同一（）下的基本优先级，weightTemp临时记录优先级的变化
            //topOp为weight[]，operator[]的计数器；topNum为number[]的计数器
            //flag为正负数的计数器，1为正数，-1为负数
            int weight[];  //保存operator栈中运算符的优先级，以topOp计数
            double number[];  //保存数字，以topNum计数
            char ch, ch_gai, operator[];//operator[]保存运算符，以topOp计数
            String num;//记录数字，str以+-×÷()sctgl!√^分段，+-×÷()sctgl!√^字符之间的字符串即为数字
            weight = new int[MAXLEN];
            number = new double[MAXLEN];
            operator = new char[MAXLEN];
            String expression = str;
            StringTokenizer expToken = new StringTokenizer(expression,"+-×÷()sctgl!√^");
            int i = 0;
            while (i < expression.length()) {
                ch = expression.charAt(i);
                //判断正负数
                if (i == 0) {
                    if (ch == '-')
                        flag = -1;
                } else if(expression.charAt(i-1) == '(' && ch == '-')
                    flag = -1;
                //取得数字，并将正负符号转移给数字
                if (ch <= '9' && ch >= '0'|| ch == '.' || ch == 'E') {
                    num = expToken.nextToken();
                    ch_gai = ch;
                    Log.e("guojs",ch+"--->"+i);
                    //取得整个数字
                    while (i < expression.length() &&
                            (ch_gai <= '9' && ch_gai >= '0'|| ch_gai == '.' || ch_gai == 'E'))
                    {
                        ch_gai = expression.charAt(i++);
                        Log.e("guojs","i的值为："+i);
                    }
                    //将指针退回之前的位置
                    if (i >= expression.length()) i-=1; else {i-=2;}
                    if (num.compareTo(".") == 0) number[topNum++] = 0;
                        //将正负符号转移给数字
                    else {
                        number[topNum++] = Double.parseDouble(num)*flag;
                        flag = 1;
                    }
                }
                //计算运算符的优先级
                if (ch == '(') weightPlus+=4;
                if (ch == ')') weightPlus-=4;
                if (ch == '-' && flag == 1 || ch == '+' || ch == '×'|| ch == '÷' ||
                        ch == 's' ||ch == 'c' || ch == 't' || ch == 'g' || ch == 'l' ||
                        ch == '!' || ch == '√' || ch == '^') {
                    switch (ch) {
                        //+-的优先级最低，为1
                        case '+':
                        case '-':
                            weightTemp = 1 + weightPlus;
                            break;
                        //x÷的优先级稍高，为2
                        case '×':
                        case '÷':
                            weightTemp = 2 + weightPlus;
                            break;
                        //sincos之类优先级为3
                        case 's':
                        case 'c':
                        case 't':
                        case 'g':
                        case 'l':
                        case '!':
                            weightTemp = 3 + weightPlus;
                            break;
                        //其余优先级为4
                        //case '^':
                        //case '√':
                        default:
                            weightTemp = 4 + weightPlus;
                            break;
                    }
                    //如果当前优先级大于堆栈顶部元素，则直接入栈
                    if (topOp == 0 || weight[topOp-1] < weightTemp) {
                        weight[topOp] = weightTemp;
                        operator[topOp] = ch;
                        topOp++;
                        //否则将堆栈中运算符逐个取出，直到当前堆栈顶部运算符的优先级小于当前运算符
                    }else {
                        while (topOp > 0 && weight[topOp-1] >= weightTemp) {
                            switch (operator[topOp-1]) {
                                //取出数字数组的相应元素进行运算
                                case '+':
                                    number[topNum-2]+=number[topNum-1];
                                    break;
                                case '-':
                                    number[topNum-2]-=number[topNum-1];
                                    break;
                                case '×':
                                    number[topNum-2]*=number[topNum-1];
                                    break;
                                //判断除数为0的情况
                                case '÷':
                                    if (number[topNum-1] == 0) {

                                        return;
                                    }
                                    number[topNum-2]/=number[topNum-1];
                                    break;
                                case '√':
                                    if(number[topNum-1] == 0 || (number[topNum-2] < 0 &&
                                            number[topNum-1] % 2 == 0)) {

                                        return;
                                    }
                                    number[topNum-2] =
                                            Math.pow(number[topNum-2], 1/number[topNum-1]);
                                    break;
                                case '^':
                                    number[topNum-2] =
                                            Math.pow(number[topNum-2], number[topNum-1]);
                                    break;
                                //计算时进行角度弧度的判断及转换
                                //sin
                                case 's':
                                    if(drg_flag == true) {
                                        number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                                    } else {
                                        number[topNum-1] = Math.sin(number[topNum-1]);
                                    }
                                    topNum++;
                                    break;
                                //cos
                                case 'c':
                                    if(drg_flag == true) {
                                        number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                                    } else {
                                        number[topNum-1] = Math.cos(number[topNum-1]);
                                    }
                                    topNum++;
                                    break;
                                //tan
                                case 't':
                                    if(drg_flag == true) {
                                        if((Math.abs(number[topNum-1])/90)%2 == 1) {

                                            return;
                                        }
                                        number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                                    } else {
                                        if((Math.abs(number[topNum-1])/(pi/2))%2 == 1) {

                                            return;
                                        }
                                        number[topNum-1] = Math.tan(number[topNum-1]);
                                    }
                                    topNum++;
                                    break;
                                //log
                                case 'g':
                                    if(number[topNum-1] <= 0) {

                                        return;
                                    }
                                    number[topNum-1] = Math.log10(number[topNum-1]);
                                    topNum++;
                                    break;
                                //ln
                                case 'l':
                                    if(number[topNum-1] <= 0) {

                                        return;
                                    }
                                    number[topNum-1] = Math.log(number[topNum-1]);
                                    topNum++;
                                    break;
                                //阶乘
                                case '!':
                                    if(number[topNum-1] > 170) {

                                        return;
                                    } else if(number[topNum-1] < 0) {

                                        return;
                                    }
                                    number[topNum-1] = N(number[topNum-1]);
                                    topNum++;
                                    break;
                            }
                            //继续取堆栈的下一个元素进行判断
                            topNum--;
                            topOp--;
                        }
                        //将运算符如堆栈
                        weight[topOp] = weightTemp;
                        operator[topOp] = ch;
                        topOp++;
                    }
                }
                i++;
            }
            //依次取出堆栈的运算符进行运算
            while (topOp>0) {
                //+-x直接将数组的后两位数取出运算
                switch (operator[topOp-1]) {
                    case '+':
                        number[topNum-2]+=number[topNum-1];
                        break;
                    case '-':
                        number[topNum-2]-=number[topNum-1];
                        break;
                    case '×':
                        number[topNum-2]*=number[topNum-1];
                        break;
                    //涉及到除法时要考虑除数不能为零的情况
                    case '÷':
                        if (number[topNum-1] == 0) {

                            return;
                        }
                        number[topNum-2]/=number[topNum-1];
                        break;
                    case '√':
                        if(number[topNum-1] == 0 || (number[topNum-2] < 0 &&
                                number[topNum-1] % 2 == 0)) {

                            return;
                        }
                        number[topNum-2] =
                                Math.pow(number[topNum-2], 1/number[topNum-1]);
                        break;
                    case '^':
                        number[topNum-2] =
                                Math.pow(number[topNum-2], number[topNum-1]);
                        break;
                    //sin
                    case 's':
                        if(drg_flag == true) {
                            number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                        } else {
                            number[topNum-1] = Math.sin(number[topNum-1]);
                        }
                        topNum++;
                        break;
                    //cos
                    case 'c':
                        if(drg_flag == true) {
                            number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                        } else {
                            number[topNum-1] = Math.cos(number[topNum-1]);
                        }
                        topNum++;
                        break;
                    //tan
                    case 't':
                        if(drg_flag == true) {
                            if((Math.abs(number[topNum-1])/90)%2 == 1) {

                                return;
                            }
                            number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                        } else {
                            if((Math.abs(number[topNum-1])/(pi/2))%2 == 1) {

                                return;
                            }
                            number[topNum-1] = Math.tan(number[topNum-1]);
                        }
                        topNum++;
                        break;
                    //对数log
                    case 'g':
                        if(number[topNum-1] <= 0) {

                            return;
                        }
                        number[topNum-1] = Math.log10(number[topNum-1]);
                        topNum++;
                        break;
                    //自然对数ln
                    case 'l':
                        if(number[topNum-1] <= 0) {

                            return;
                        }
                        number[topNum-1] = Math.log(number[topNum-1]);
                        topNum++;
                        break;
                    //阶乘
                    case '!':
                        if(number[topNum-1] > 170) {

                            return;
                        } else if(number[topNum-1] < 0) {

                            return;
                        }
                        number[topNum-1] = N(number[topNum-1]);
                        topNum++;
                        break;
                }
                //取堆栈下一个元素计算
                topNum--;
                topOp--;
            }
            //如果是数字太大，提示错误信息
            if(number[0] > 7.3E306) {

                return;
            }
            //输出最终结果
            Max_Led.setText(String.valueOf(FP(number[0])));
         //   tip.setText("计算完毕，要继续请按归零键 C");
         //   mem.setText(str_old+"="+String.valueOf(FP(number[0])));
        }

        /*
         * FP = floating point 控制小数位数，达到精度
         * 否则会出现 0.6-0.2=0.39999999999999997的情况，用FP即可解决，使得数为0.4
         * 本格式精度为15位
         */
        public double FP(double n) {
            //NumberFormat format=NumberFormat.getInstance();  //创建一个格式化类f
            //format.setMaximumFractionDigits(18);    //设置小数位的格式
            DecimalFormat format = new DecimalFormat("0.#############");
            return Double.parseDouble(format.format(n));
        }

        /*
         * 阶乘算法
         */
        public double N(double n) {
            int i = 0;
            double sum = 1;
            //依次将小于等于n的值相乘
            for(i = 1;i <= n;i++) {
                sum = sum*i;
            }
            return sum;
        }

        /*
         * 错误提示，按了"="之后，若计算式在process()过程中，出现错误，则进行提示
         */
        public void showError(int code ,String str) {
            String message="";
            switch (code) {
                case 1:
                    message = "零不能作除数";
                    break;
                case 2:
                    message = "函数格式错误";
                    break;
                case 3:
                    message = "值太大了，超出范围";
            }
            Max_Led.setText("\""+str+"\""+": "+message);

        }
    }
}

