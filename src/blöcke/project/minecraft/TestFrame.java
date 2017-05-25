package blöcke.project.minecraft;

import org.lwjgl.util.vector.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by levin on 10.05.2017.
 */
public class TestFrame extends JFrame{

    private List<JLabel> labels;
    private JPanel panel;

    public TestFrame(){
        super("Debug");
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panel = new JPanel();
        add(panel);

        addLabel(String.format("%tT", new Date()));
        addLabel("bla");

        setVisible(true);
    }

    public void addLabel(String text){
        if(labels == null){
            labels = new ArrayList<>();
        }

        JLabel newLabel = new JLabel(text);

        labels.add(newLabel);
        panel.add(newLabel);
    }

    public void addLabel(JLabel label){
        panel.add(label);
    }

    public void changeText(int id, String text){
        labels.get(id).setText(text);
    }




}
