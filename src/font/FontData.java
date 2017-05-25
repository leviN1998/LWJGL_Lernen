package font;

/**
 * Created by levin on 07.03.2017.
 */
public class FontData {

    private int[] asciiCode;
    private int[] x;
    private int[] y;
    private int[] width;
    private int[] height;
    private int[] xOffset;
    private int[] yOffset;
    private int[] xAdvance;

    public FontData(int[] asciiCode, int[] x, int[] y, int[] width, int[] height, int[] xOffset, int[] yOffset, int[] xAdvance) {
        this.asciiCode = asciiCode;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xAdvance = xAdvance;
    }

    public int[] getAsciiCode() {
        return asciiCode;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int[] getWidth() {
        return width;
    }

    public int[] getHeight() {
        return height;
    }

    public int[] getxOffset() {
        return xOffset;
    }

    public int[] getyOffset() {
        return yOffset;
    }

    public int[] getxAdvance() {
        return xAdvance;
    }
}
