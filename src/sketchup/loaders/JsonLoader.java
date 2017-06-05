package sketchup.loaders;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;
import sketchup.files.RawSprite;
import sketchup.files.RawSpriteSheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by levin on 17.05.2017.
 */
public class JsonLoader {


    public List<RawSprite> loadFile(String path) throws FileNotFoundException{
        String newPath = "res/"+path;
        return this.loadFile(new File("res/"+path+".json"), newPath);
    }

    public List<RawSprite> loadFile(File f, String path) throws FileNotFoundException{
        return this.loadFile(new Scanner(f), path);
    }

    public List<RawSprite> loadFile(Scanner sc, String path){

        RawSpriteSheet spriteSheet = new RawSpriteSheet(path);

        while (sc.hasNextLine()){
            String ln = sc.nextLine();
            if(ln == null || ln.equals("") || ln.startsWith("{") || ln.startsWith("}")){

            }else {

                String[] split = ln.split(" ");

                if(split[0].startsWith("\t\"filename\"")){

                    String[] s = split[1].split("\"");
                    spriteSheet.getFileNames().add(s[1]);

                }else if(split[0].startsWith("\t\"frame\"")){

                    String[] s = split[1].replace("}", "").split("\"");

                    int x = Integer.parseInt(s[2].replace(":", "").replace(",", ""));
                    int y = Integer.parseInt(s[4].replace(":", "").replace(",", ""));
                    int z = Integer.parseInt(s[6].replace(":", "").replace(",", ""));
                    int w = Integer.parseInt(s[8].replace(":", "").replace(",", ""));

                    spriteSheet.getFrames().add(new Vector4f(x,y,z,w));

                }else if(split[0].startsWith("\t\"rotated\"")){

                    String[] s = split[1].split("\"");
                    boolean b = Boolean.parseBoolean(s[0].replace(",", ""));

                    spriteSheet.getRotateds().add(b);

                }else if(split[0].startsWith("\t\"trimmed\"")){

                    String[] s = split[1].split("\"");
                    boolean b = Boolean.parseBoolean(s[0].replace(",", ""));

                    spriteSheet.getTrimmeds().add(b);

                }else if(split[0].startsWith("\t\"spriteSourceSize\"")){

                    String[] s = split[1].replace("}", "").split("\"");

                    int x = Integer.parseInt(s[2].replace(":", "").replace(",", ""));
                    int y = Integer.parseInt(s[4].replace(":", "").replace(",", ""));
                    int z = Integer.parseInt(s[6].replace(":", "").replace(",", ""));
                    int w = Integer.parseInt(s[8].replace(":", "").replace(",", ""));

                    spriteSheet.getSpriteResourceSizes().add(new Vector4f(x,y,z,w));

                }else if(split[0].startsWith("\t\"sourceSize\"")){

                    String[] s = split[1].replace("}", "").split("\"");

                    int x = Integer.parseInt(s[2].replace(":", "").replace(",", ""));
                    int y = Integer.parseInt(s[4].replace(":", "").replace(",", ""));

                    spriteSheet.getSourceSizes().add(new Vector2f(x,y));

                }else if(split[0].startsWith("\t\"pivot\"")){

                    String[] s = split[1].replace("}", "").split("\"");

                    float x = Float.parseFloat(s[2].replace(":", "").replace(",", ""));
                    float y = Float.parseFloat(s[4].replace(":", "").replace(",", ""));

                    spriteSheet.getPivots().add(new Vector2f(x,y));

                }
            }
        }

        spriteSheet.calcSprites();

        return spriteSheet.getSprites();


    }



}
