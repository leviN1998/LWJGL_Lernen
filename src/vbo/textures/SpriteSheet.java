package vbo.textures;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by levin on 14.11.2016.
 */
public class SpriteSheet {


    private BufferedImage[] sprites;
    private BufferedImage img;
    private float scaling;
    private int width;
    private int height;
    private int multiplier;
    private int rand;


    public SpriteSheet(int sizeX, int sizeY, int multiHEIGHT, int rand, int width, int height, float scaling, String path){
        this.multiplier = multiHEIGHT;
        this.rand = rand;
        this.width = width;
        this.height = height;
        this.scaling = scaling;

        sprites = new BufferedImage[sizeX*sizeY];
        BufferedImage spritesheet;

        try {
            spritesheet = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int i = 0;
        for(int y = 0; y<sizeY;y++){
            for(int x = 0; x<sizeX;x++){
                img = spritesheet.getSubimage(x*width, (y*height*multiplier)+ (rand*y), width, height*multiHEIGHT);
                int w = img.getWidth();
                int h = img.getHeight();
                BufferedImage after = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
                AffineTransform at = new AffineTransform();
                at.scale(scaling, scaling);
                AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                after = scaleOp.filter(img, after);
                sprites[i++] = after;
            }
        }
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public float getScaling() {
        return scaling;
    }

    public void setScaling(float scaling) {
        this.scaling = scaling;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }
}
