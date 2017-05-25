package font.two.zero;

import vbo.DisplayManager;

/**
 * Created by levin on 23.03.2017.
 */
public class Character {

    private int asciiCode;
    private int x;
    private int y;
    private int width;
    private int height;
    private float xOffset;
    private float yOffset;
    private float xAdvance;

    private int texture;

    public Character(int asciiCode, int x, int y, int width, int height, float offsetx, float offsety, float advancex) {
        this.asciiCode = asciiCode;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = offsetx;
        this.yOffset = offsety;
        this.xAdvance = advancex;
        this.texture = -1;

        //System.out.println(Advancex+"  |  " + xAdvance);
    }

    public int getAsciiCode() {
        return asciiCode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public float getxAdvance() {
        return xAdvance;
    }

    public int getTexture(){
        return texture;
    }

    public void setTexture(int texture){
        this.texture = texture;
    }
}
