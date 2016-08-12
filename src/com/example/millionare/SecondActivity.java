package com.example.millionare;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.*;
import android.view.View;
import android.widget.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Roston on 28/02/14.
 */
public class SecondActivity extends Activity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    CountDownTimer cdt,cdt1,cdt2,cdt3;
    TextView display;
    Button time;
    ImageView graph;

    String correctanswer;
    LinearLayout li;
    int j=0;

    Button b1,b2,b3,b4,b5,playnext;
    TextView resulttextview;

    MediaPlayer mpaudio,mpaudio1,mpaudio2;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);



        mpaudio= MediaPlayer.create(this,R.drawable.kbc_tune);
        mpaudio.start();

        mpaudio1= MediaPlayer.create(this,R.drawable.clock_tick);

        populatelistview();


        graph=(ImageView)findViewById(R.id.graph);
        time=(Button)findViewById(R.id.btictoc);
        mpaudio.setOnCompletionListener(this);

        b1=(Button)findViewById(R.id.bquestion);
        b2=(Button)findViewById(R.id.boptiona);
        b3=(Button)findViewById(R.id.boptionb);
        b4=(Button)findViewById(R.id.boptionc);
        b5=(Button)findViewById(R.id.boptiond);
        playnext=(Button)findViewById(R.id.nextplaybtn);

        li=(LinearLayout)findViewById(R.id.interior4);
        resulttextview=(TextView)findViewById(R.id.textresult);
        read();



    }

    private void setupCountDown()
    {
        cdt = new CountDownTimer(45000, 1000)
        {

            public void onTick(long millisUntilFinished)
            {
                time.setText("" + millisUntilFinished / 1000);
                  int s=(int)millisUntilFinished/1000;
                System.out.println("This is the time"+s);

                if(s==10)
                {
                    clap();
                }

            }

            public void onFinish()
            {
                mpaudio1.pause();
                setContentView(R.layout.second);
                Intent intent=new Intent(SecondActivity.this,MyActivity.class);
                startActivity(intent);
                SecondActivity.this.finish();
            }
        };
    }


    @Override
    public void onClick(View view)
    {

    }

    public void clap()
    {
        mpaudio2.pause();
        mpaudio1.start();
    }

     private void populatelistview()
     {

         String[] myitems={"15  £ 10 000 000","14    £ 5 000 000","13    £ 2 500 000","12    £ 1 000 000","11    £ 500 000",
                           "10    £ 100 000","9     £ 50 000","8     £ 30 000","7     £ 20 000","6     £ 10 000","5     £ 5000",
                 "4     £ 4000","3     £ 3000","2     £ 2000","1     £ 1000"};
       //  ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myitems);
         ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_view,myitems);
         ListView list=(ListView)findViewById(R.id.listViewMain);

         list.setAdapter(adapter);

     }


    public void onFiftyfifty(View view)
    {
        Button b=(Button)findViewById(R.id.bfifty);
        b.setBackgroundResource(R.drawable.custom_cancelfiftyfifty);
        b.setEnabled(true);

        String value2=b2.getText().toString();   String value3=b3.getText().toString(); String value4=b4.getText().toString(); String value5=b5.getText().toString();

        if(value2.equalsIgnoreCase(correctanswer))
        {
            b3.setText("");
            b4.setText("");
        }
        else if(value3.equalsIgnoreCase(correctanswer))
        {
            b2.setText("");
            b5.setText("");
        }
        else if(value4.equalsIgnoreCase(correctanswer))
        {
            b2.setText("");
            b5.setText("");
        }
        else if(value5.equalsIgnoreCase(correctanswer))
        {
            b3.setText("");
            b4.setText("");
        }

    }

    public void onPhoneAfriend(View view)
    {
        Button b=(Button)findViewById(R.id.bphone);
        b.setBackgroundResource(R.drawable.custom_cancelphoneafreind);
        b.setEnabled(true);


        String value2=b2.getText().toString();   String value3=b3.getText().toString(); String value4=b4.getText().toString(); String value5=b5.getText().toString();

        if(value2.equalsIgnoreCase(correctanswer))
        {
            resulttextview.setText("Option A is Correct Answer!");
        }
        else if(value3.equalsIgnoreCase(correctanswer))
        {
            resulttextview.setText("Option B is Correct Answer!");
        }
        else if(value4.equalsIgnoreCase(correctanswer))
        {
            resulttextview.setText("Option C is Correct Answer!");
        }
        else if(value5.equalsIgnoreCase(correctanswer))
        {
            resulttextview.setText("Option D is Correct Answer!");
        }

             timeout();
             cdt3.start();

    }

    private void timeout()
    {

        cdt3 = new CountDownTimer(5000, 1000)
        {
            // int s;

            public void onTick(long millisUntilFinished)
            {
                // time.setText("" + millisUntilFinished / 1000);
                // s=(int)millisUntilFinished/1000;
                // System.out.println("This is the time"+s);


            }

            public void onFinish()
            {
                playnext.setVisibility(View.GONE);
                resulttextview.setVisibility(View.GONE);
                cdt3.cancel();
            }
        };

    }

    public void onAudiencepoll(View view)
    {
        Button b=(Button)findViewById(R.id.baudiencepoll);
        b.setBackgroundResource(R.drawable.custom_cancelaudiencepoll);
        b.setEnabled(true);

        String value2=b2.getText().toString();   String value3=b3.getText().toString(); String value4=b4.getText().toString(); String value5=b5.getText().toString();

        if(value2.equalsIgnoreCase(correctanswer))
        {
            graph.setBackgroundResource(R.drawable.a);
            graph.setVisibility(View.VISIBLE);
              // li.setBackgroundResource(R.drawable.a);
        }
        else if(value3.equalsIgnoreCase(correctanswer))
        {
            graph.setBackgroundResource(R.drawable.b);
            graph.setVisibility(View.VISIBLE);
           // li.setBackgroundResource(R.drawable.b);
        }
        else if(value4.equalsIgnoreCase(correctanswer))
        {
            graph.setBackgroundResource(R.drawable.c);
            graph.setVisibility(View.VISIBLE);
            //li.setBackgroundResource(R.drawable.c);
        }
        else if(value5.equalsIgnoreCase(correctanswer))
        {
            graph.setBackgroundResource(R.drawable.d);
            graph.setVisibility(View.VISIBLE);
           // li.setBackgroundResource(R.drawable.d);
        }

            disapperaimage();
            cdt2.start();
    }

    private void disapperaimage()
    {
        cdt2 = new CountDownTimer(5000, 1000)
        {
            // int s;

            public void onTick(long millisUntilFinished)
            {
                // time.setText("" + millisUntilFinished / 1000);
                // s=(int)millisUntilFinished/1000;
                // System.out.println("This is the time"+s);


            }

           // @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onFinish()
            {

                graph.setVisibility(View.INVISIBLE);
                cdt2.cancel();

            }
        };
    }

    public void onFlip(View view)
    {
        Button b=(Button)findViewById(R.id.bflip);
        b.setBackgroundResource(R.drawable.custom_cancelflip);
        b.setEnabled(true);

        read();
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer)
    {
        mpaudio2= MediaPlayer.create(this,R.drawable.bleach_soundtrack);
        mpaudio2.start();
    }


    public void read()
    {
        setupCountDown();
        cdt.start();

        SAXBuilder builder = new SAXBuilder();

        //File xmlFile = new File(R.xml.test);
        AssetManager assets=getAssets();

        //  File xmlFile = new File(getFilesDir(),"assets/test.xml");

        try {
            InputStream in=assets.open("question.xml");

            Document document = (Document) builder.build(in);
            Element rootNode = document.getRootElement();
           // List list = rootNode.getChildren("staff");

            List list = rootNode.getChildren("Question");

           // for (int i = 0; i < list.size(); i++) {

                //Element node = (Element) list.get(i);
                Element node = (Element) list.get(j);

                System.out.println("Question : " + node.getChildText("question"));
                b1.setText(node.getChildText("question"));

                System.out.println("option A : " + node.getChildText("optiona"));
                b2.setText(node.getChildText("optiona"));

                System.out.println("option B : " + node.getChildText("optionb"));
                b3.setText(node.getChildText("optionb"));

                System.out.println("option C : " + node.getChildText("optionc"));
                b4.setText(node.getChildText("optionc"));

                System.out.println("option D : " + node.getChildText("optiond"));
                b5.setText(node.getChildText("optiond"));

            //System.out.println("Correct Answer : " + node.getChildText("correctanswer"));
               correctanswer=(node.getChildText("correctanswer")).toString();

                System.out.println(correctanswer);
          //  }

                    j++;

        }

        catch (Exception io)
        {
            System.out.println(io.getMessage());
        }


    }

    public void onOptionAClicked(View view)
    {
        cdt.cancel();
        mpaudio2.pause();
        String value2=b2.getText().toString();

        if(value2.equalsIgnoreCase(correctanswer))
        {
            b1.setText(""); b2.setText(""); b3.setText(""); b4.setText(""); b5.setText("");
            resulttextview.setText("Congratulations Correct Answer!");
            playnext.setVisibility(View.VISIBLE);
            resulttextview.setVisibility(View.VISIBLE);

        }
        else
        {
            resulttextview.setText("Wrong Answer!");
            resulttextview.setVisibility(View.VISIBLE);
        }
    }

    public void onOptionBClicked(View view)
    {
        cdt.cancel();
        mpaudio2.pause();
        String value3=b3.getText().toString();

        if(value3.equalsIgnoreCase(correctanswer))
        {
            b1.setText(""); b2.setText(""); b3.setText(""); b4.setText(""); b5.setText("");
            resulttextview.setText("Congratulations Correct Answer!");
            playnext.setVisibility(View.VISIBLE);
            resulttextview.setVisibility(View.VISIBLE);
        }
        else
        {
            resulttextview.setText("Wrong Answer!");
            resulttextview.setVisibility(View.VISIBLE);
        }
    }

    public void onOptionCClicked(View view)
    {
        cdt.cancel();
        mpaudio2.pause();
        String value4=b4.getText().toString();

        if(value4.equalsIgnoreCase(correctanswer))
        {
            b1.setText(""); b2.setText(""); b3.setText(""); b4.setText(""); b5.setText("");
            resulttextview.setText("Congratulations Correct Answer!");
            playnext.setVisibility(View.VISIBLE);
            resulttextview.setVisibility(View.VISIBLE);
        }
        else
        {
            resulttextview.setText("Wrong Answer!");
            resulttextview.setVisibility(View.VISIBLE);
        }
    }

    public void onOptionDClicked(View view)
    {
        cdt.cancel();
        mpaudio2.pause();
        String value5=b5.getText().toString();
        if(value5.equalsIgnoreCase(correctanswer))
        {
            b1.setText(""); b2.setText(""); b3.setText(""); b4.setText(""); b5.setText("");
            resulttextview.setText("Congratulations Correct Answer!");
            playnext.setVisibility(View.VISIBLE);
            resulttextview.setVisibility(View.VISIBLE);
        }
        else
        {
            resulttextview.setText("Wrong Answer!");
            resulttextview.setVisibility(View.VISIBLE);
        }

    }

    public void nextPlayClicked(View view)
    {
        read();
        mpaudio2.start();
        afterquestion();
        cdt1.start();
    }


    public void afterquestion()
    {

        cdt1 = new CountDownTimer(5000, 1000)
        {
           // int s;

            public void onTick(long millisUntilFinished)
            {
               // time.setText("" + millisUntilFinished / 1000);
               // s=(int)millisUntilFinished/1000;
               // System.out.println("This is the time"+s);


            }

            public void onFinish()
            {
                playnext.setVisibility(View.GONE);
                resulttextview.setVisibility(View.GONE);
                cdt1.cancel();
            }
        };
    }

}
