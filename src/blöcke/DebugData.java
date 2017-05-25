package blöcke;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 10.05.2017.
 */
public class DebugData {

    private String title;
    private Float[] data;
    private int length;

    public DebugData(String title, int lenght){
        this.title = title;
        this.length = lenght;
        this.data = new Float[lenght];
    }

    public void addData(float data, int index){
        this.data[index] = data;
    }

    public JLabel getLabel(){
        JLabel output = new JLabel();
        String a = "";
        String b;
        for(int i = 0;i<data.length;i++){
            b = a + " / " + String.valueOf(data[i]);
            a = b;
        }
        String out = title + ":  " + a;
        output.setText(out);
        return output;
    }




}
