package blöcke;

import blöcke.project.minecraft.TestFrame;

import java.util.Date;

/**
 * Created by levin on 10.05.2017.
 */
public class Testing2 {
    public static void main(String[] args){
        TestFrame frame = new TestFrame();

        DebugData data = new DebugData("positions", 3);

        data.addData(14.3f,0);
        data.addData(73.8f,1);
        data.addData(92.1f,2);

        frame.addLabel(data.getLabel());


        while (true){
            frame.changeText(0,String.format("%tT",new Date()));



            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
