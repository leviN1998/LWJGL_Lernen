package sketchup.files;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 17.05.2017.
 */
public class RawSpriteSheet {

    private List<RawSprite> sprites;

    private String imagePath;

    private List<String> fileNames;
    private List<Vector4f> frames;
    private List<Boolean> rotateds;
    private List<Boolean> trimmeds;
    private List<Vector4f> spriteResourceSizes;
    private List<Vector2f> sourceSizes;
    private List<Vector2f> pivots;


    public RawSpriteSheet(String path){
        this.sprites = new ArrayList<>();
        this.imagePath = path+".png";

        fileNames = new ArrayList<>();
        frames = new ArrayList<>();
        rotateds = new ArrayList<>();
        trimmeds = new ArrayList<>();
        spriteResourceSizes = new ArrayList<>();
        sourceSizes = new ArrayList<>();
        pivots = new ArrayList<>();
    }

    public void calcSprites(){
        for(int i = 0; i<fileNames.size();i++){
            RawSprite s = new RawSprite(fileNames.get(i), frames.get(i), rotateds.get(i), trimmeds.get(i),
                    sourceSizes.get(i), pivots.get(i));
            addToList(s);
        }
    }


    public void addToList(RawSprite sprite){
        sprites.add(sprite);
    }

    public List<RawSprite> getSprites() {
        return sprites;
    }

    public String getImagePath() {
        return imagePath;
    }

    public List<Vector2f> getPivots() {
        return pivots;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public List<Vector4f> getFrames() {
        return frames;
    }

    public List<Boolean> getRotateds() {
        return rotateds;
    }

    public List<Boolean> getTrimmeds() {
        return trimmeds;
    }

    public List<Vector4f> getSpriteResourceSizes() {
        return spriteResourceSizes;
    }

    public List<Vector2f> getSourceSizes() {
        return sourceSizes;
    }
}
