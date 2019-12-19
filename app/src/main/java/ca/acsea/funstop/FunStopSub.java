package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class FunStopSub extends Fragment {
    View view;
    TextView textView;
    CheckBox checkButton1;

    public FunStopSub() {
        System.out.println("Working funstopsub?");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_fun_stop_sub, container, false);

        onClickQR();
        // Inflate the layout for this fragment
        return view;
    }



    public void onClickQR(){
        textView = view.findViewById(R.id.qrScanner);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), QrCodeScanner.class);
                startActivity(i);

            }
        });
    }


}

