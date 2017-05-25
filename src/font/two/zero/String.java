package font.two.zero;



import org.lwjgl.util.Display;
import org.lwjgl.util.vector.Vector2f;
import vbo.DisplayManager;
import vbo.toolbox.Maths;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 23.03.2017.
 */
public class String {

    private java.lang.String text;
    private List<CharacterString> characters;
    private font.two.zero.Font font;
    private Vector2f scale;
    private Vector2f cursorPosition;
    private Vector2f position;
    private int size;


    public String(Vector2f position, Font font, java.lang.String text, int size){
        this.text = text;
        this.font = font;
        this.position = position;
        this.size = size;
        initScale();
        initString();
    }

    private void initString(){
        characters = new ArrayList<>();
        cursorPosition = Maths.getDisplayCoords(position);
        addString(text);
    }

    private void initScale(){
        this.scale = new Vector2f(scaleX[size], scaleY[size]);
    }

    private CharacterString parseCharacter(java.lang.String s){
        int ascii = s.charAt(0);
        int w = font.getCharacters()[ascii].getWidth();
        int h = font.getCharacters()[ascii].getHeight();
        if(w == 0 && h == 0){
            return null;
        }else {
            return new CharacterString(getCharacterPosition(font.getCharacters()[ascii]), scale, ascii, font.getCharacters()[ascii].getTexture());
        }
    }

    private CharacterString checkForParsing(java.lang.String s){
        int ascii = s.charAt(0);
        int w = font.getCharacters()[ascii].getWidth();
        int h = font.getCharacters()[ascii].getHeight();
        if(w == 0 && h == 0){
            return null;
        }
        return new CharacterString(new Vector2f(0,0), new Vector2f(0,0), 0 ,0);
    }

    private List<java.lang.String> splitString(java.lang.String s){
        List<java.lang.String> list = new ArrayList<>();
        int i = 0;
        while(true){
            try{
                java.lang.String string = java.lang.String.valueOf(s.charAt(i));
                list.add(string);
            }catch (Exception x){
                break;
            }
            i++;
        }
        return list;
    }

    private void addString(java.lang.String s){
        List<java.lang.String> list = splitString(s);
        for(java.lang.String string : list){
            if(checkForParsing(string) != null){
                characters.add(parseCharacter(string));
            }

        }
    }

    private Vector2f getCharacterPosition(Character c){
        float x = cursorPosition.getX();
        float y = cursorPosition.getY() - c.getyOffset() * yOffsets[size];
        cursorPosition.x += c.getxAdvance() / xStuff[size];
        //System.out.println(java.lang.Character.toString((char) c.getAsciiCode()) + "  |  " + c.getxAdvance());
        return new Vector2f(x,y);
    }


    public java.lang.String getText() {
        return text;
    }

    public List<CharacterString> getCharacters() {
        return characters;
    }

    public Font getFont() {
        return font;
    }

    public Vector2f getScale(){
        return scale;
    }

    public void setNewText(java.lang.String text){
        this.text = text;
        initString();
    }


    /**
     * Das hier sind arrays für die einzelnen textgrößen (bissle getrickst ;)
     *
     * new Vector2f(0.05f,0.1f)  / 2
     */

    private float[] scaleX = {0.03f , 0.05f};

    private float[] scaleY = {0.05f , 0.1f};

    private float[] xStuff = {3 , 2};

    private float[] yOffsets = {0.27f , 0.5f};

}
