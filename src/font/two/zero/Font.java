package font.two.zero;



import font.FontData;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Display;
import vbo.DisplayManager;
import vbo.Loader;
import vbo.toolbox.Maths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.*;


/**
 * Created by levin on 23.03.2017.
 */
public class Font {

    private font.two.zero.Character[] characters;

    private BufferedImage texture;

    private java.lang.String pathEmpty;

    private FontData data;

    public int highestPowerOfTwo;






    public Font(FontData data, Loader loader, java.lang.String textureLocation, java.lang.String pathEmpty){
        highestPowerOfTwo = 0;
        texture = loadImage(textureLocation);
        this.pathEmpty = pathEmpty;
        this.data = data;
        initPowerOfTwo(loader);
        init(loader);
    }

    public void initPowerOfTwo(Loader loader){
        for(int i = 0; i<data.getHeight().length;i++){
            if(data.getHeight()[i] > highestPowerOfTwo){
                highestPowerOfTwo = loader.getNextPowerOfTwo(data.getHeight()[i]);
            }
        }
    }

    public void init(Loader loader){
        characters = new Character[data.getAsciiCode()[data.getAsciiCode().length-1]];
        int counter = 0;
        for(int i = 0; i<characters.length;i++){
            if(i == data.getAsciiCode()[counter]) {

                characters[i] = new Character(data.getAsciiCode()[counter], data.getX()[counter], data.getY()[counter], data.getWidth()[counter],
                        data.getHeight()[counter], data.getxOffset()[counter], data.getyOffset()[counter],
                        data.getxAdvance()[counter]);

                try {
                    if(characters[i].getWidth() == 0 && characters[i].getHeight() == 0){
                        characters[i].setTexture(-2);
                    }else {
                        characters[i].setTexture(createTexture(characters[i], loader));
                    }
                }catch (Exception x){
                    x.printStackTrace();
                }
                counter++;
            }
        }
    }


    public int createTexture(Character c, Loader loader){
        BufferedImage textureImage = null;
        BufferedImage step1 = loader.getTileImage(c.getX(), c.getY(), c.getWidth(), c.getHeight(), texture);

        textureImage = loader.getPowersofTwoImage(step1, pathEmpty, highestPowerOfTwo);


        return loader.loadTexture(textureImage);

    }

    public BufferedImage loadImage(java.lang.String location){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new FileInputStream("res/"+location+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public Character[] getCharacters() {
        return characters;
    }
}
