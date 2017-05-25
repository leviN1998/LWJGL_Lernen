package vbo;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.BufferedImageUtil;
import vbo.entities.Entity;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.textures.ModelTexture;
import vbo.textures.SpriteSheet;
import vbo.toolbox.objConverter.ModelData;
import vbo.toolbox.objConverter.OBJFileLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 01.11.2016.
 */
public class Loader {

    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    private List<Integer> textures = new ArrayList<Integer>();
    public int highestNumber;

    public Loader(){
        highestNumber = 0;
    }

    public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0,3,positions);
        storeDataInAttributeList(1,2,textureCoords);
        storeDataInAttributeList(2,3,normals);
        unbindVAO();
        return new RawModel(vaoID,indices.length);
    }

    public RawModel loadToVAO(float[] positions, float[] textureCoords){
        int vaoID = createVAO();
        storeDataInAttributeList(0,2,positions);
        storeDataInAttributeList(1,2,textureCoords);
        unbindVAO();
        return new RawModel(vaoID,positions.length/2);
    }

    public RawModel loadToVAO(float[] positions){
        int vaoID = createVAO();
        this.storeDataInAttributeList(0, 2, positions);
        unbindVAO();
        return new RawModel(vaoID, positions.length/2);
    }

    public int loadTexture(String fileName){
        Texture texture = null;
        try{
            texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
        } catch (FileNotFoundException x){
            x.printStackTrace();
        } catch (IOException x){
            x.printStackTrace();
        }
        int textureID = texture.getTextureID();
        textures.add(textureID);
        return textureID;
    }

    public int loadTexture(BufferedImage img){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            ImageIO.write(img, "png", os);
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            System.out.println("Konnte die Texture wegen des InputStreams nicht laden!!");
        }

        Texture texture = null;
        try{
            texture = TextureLoader.getTexture("PNG", is);
        } catch (FileNotFoundException x){
            x.printStackTrace();
        } catch (IOException x){
            x.printStackTrace();
        }
        int textureID = texture.getTextureID();
        textures.add(textureID);
        return textureID;
    }

    public int loadTextureSprite(String path, int sizeX, int sizeY, int multiHEIGHT, int rand, int width, int height, float scaling, int imagePosition){
        SpriteSheet spriteSheet = new SpriteSheet(sizeX, sizeY, multiHEIGHT, rand, width, height, scaling, path);
        Texture texture = null;
        try {
            texture = BufferedImageUtil.getTexture("",spriteSheet.getSprites()[imagePosition]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int textureID = texture.getTextureID();
        textures.add(textureID);
        return textureID;

    }

    public void cleanUp(){
        for(int vao:vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo:vbos){
            GL15.glDeleteBuffers(vbo);
        }
        for(int texture:textures){
            GL11.glDeleteTextures(texture);
        }
    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

    }

    private void unbindVAO(){
        GL30.glBindVertexArray(0);
    }

    private void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;

    }


    //-----------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------

    public Entity loadEntity(String textureLocation, String objLocation, Vector3f position){
        ModelData data = OBJFileLoader.loadOBJ(objLocation);
        ModelTexture textureBlock = new ModelTexture(this.loadTexture(textureLocation));

        textureBlock.setShineDamper(10);
        textureBlock.setReflectivity(0.2f);

        return new Entity(new TexturedModel(this.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()),
                textureBlock), position, 0, 0, 0, 1);
    }

    public void overrideVaoData(int vaoID, int listID, float[] data, int coordinateSize){
        GL30.glBindVertexArray(vaoID);
        storeDataInAttributeList(listID, coordinateSize, data);
        unbindVAO();
    }

    public BufferedImage getTileImage(int x, int y, int width, int height, BufferedImage bigImage){
        BufferedImage img = null;
        try {
            img = bigImage.getSubimage(x, y, width, height);
        }catch (Exception e){
            System.out.println(x+"  |  "+y+"  |  "+width+"  |  "+height);
        }
        return img;
    }


    public BufferedImage getPowersofTwoImage(BufferedImage img, String pathEmptyImg){
        int size;
        if(img.getWidth() < img.getHeight()){
            size = img.getHeight();
        }else{
            size = img.getWidth();
        }
        BufferedImage output = new BufferedImage(getNextPowerOfTwo(size), getNextPowerOfTwo(size), BufferedImage.TYPE_INT_ARGB);
        BufferedImage transparent = null;
        try {
            transparent = ImageIO.read(new FileInputStream("res/"+pathEmptyImg+".png"));
        } catch (IOException e) {
            System.out.println("Fehler Beim laden des transparenten Bildes");
            System.out.println(getNextPowerOfTwo(img.getWidth())+"    "+getNextPowerOfTwo(img.getHeight()));
            e.printStackTrace();
        }

        Graphics g = output.getGraphics();
        g.drawImage(transparent, 0, 0, null);
        g.drawImage(img, 0, 0, null);
        return output;
    }

    public BufferedImage getPowersofTwoImage(BufferedImage img, String pathEmptyImg, int squareWidth){
        BufferedImage output = new BufferedImage(squareWidth, squareWidth, BufferedImage.TYPE_INT_ARGB);
        BufferedImage transparent = null;
        try {
            transparent = ImageIO.read(new FileInputStream("res/"+pathEmptyImg+".png"));
        } catch (IOException e) {
            System.out.println("Fehler Beim laden des transparenten Bildes");
            System.out.println(getNextPowerOfTwo(img.getWidth())+"    "+getNextPowerOfTwo(img.getHeight()));
            e.printStackTrace();
        }

        Graphics g = output.getGraphics();
        g.drawImage(transparent, 0, 0, null);
        g.drawImage(img, 0, 0, null);
        return output;
    }


    public int getNextPowerOfTwo(int number){
        int prevNum = 1;
        int num;
        while(true){
            num = prevNum*2;
            if(number <= num && number > prevNum){
                return num;
            }else if(number<prevNum){
                System.out.println("Failed to calculate next power of two for: "+ number);
                return -1;
            }
            prevNum = num;
        }
    }

}
