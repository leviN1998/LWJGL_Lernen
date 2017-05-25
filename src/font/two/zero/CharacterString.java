package font.two.zero;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 23.03.2017.
 */
public class CharacterString {

    private Vector2f position;
    private Vector2f scale;

    private int asciiCode;
    private int texture;


    public CharacterString(Vector2f position, Vector2f scale, int asciiCode, int texture) {
        this.position = position;
        this.scale = scale;
        this.asciiCode = asciiCode;
        this.texture = texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public int getAsciiCode() {
        return asciiCode;
    }

    public int getTexture(){
        return texture;
    }
}
