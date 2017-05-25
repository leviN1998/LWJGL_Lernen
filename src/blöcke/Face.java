package blöcke;

import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.renderEngine.Renderer;
import vbo.entities.Entity;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.shaders.StaticShader;
import vbo.textures.ModelTexture;

/**
 * Created by levin on 14.11.2016.
 */
@Deprecated
public class Face {

    private Loader loader;
    private Renderer renderer;
    private StaticShader shader;
    private String imageName;
    private int imagePosition;

    private RawModel model;
    private ModelTexture texture;
    private TexturedModel texturedModel;
    private Entity entity;

    private float[] vertices;
    private float[] textureCoords;
    private int[] indices;
    private Vector3f position;

    public Face(Loader loader, Renderer renderer, StaticShader shader, String imageName){
        this.loader = loader;
        this.renderer = renderer;
        this.shader = shader;
        this.imageName = imageName;
    }

    public void face0(Vector3f position, int imagePosition){
        this.position = position;
        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();
        this.imagePosition = imagePosition;

        float[] verticesT = {
                x, y, z,
                x, y-1, z,
                x+1, y-1, z,
                x+1, y, z
        };
        vertices = verticesT;

        int[] indicesT = {

                0,1,3,
                3,1,2

        };
        indices = indicesT;

        float[] textureCoordsT = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        textureCoords = textureCoordsT;
    }
    float[] normals;

    public void face1(Vector3f position, int imagePosition){
        this.position = position;
        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();
        this.imagePosition = imagePosition;

        float[] verticesT = {
                x+1, y, z,
                x+1, y-1, z,
                x+1, y-1, z-1,
                x+1, y, z-1
        };
        vertices = verticesT;

        int[] indicesT = {

                0,1,3,
                3,1,2

        };
        indices = indicesT;

        float[] textureCoordsT = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        textureCoords = textureCoordsT;
    }

    public void face2(Vector3f position, int imagePosition){
        this.position = position;
        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();
        this.imagePosition = imagePosition;

        float[] verticesT = {
                x+1, y, z-1,
                x+1, y-1, z-1,
                x, y-1, z-1,
                x, y, z-1
        };
        vertices = verticesT;

        int[] indicesT = {

                0,1,3,
                3,1,2

        };
        indices = indicesT;

        float[] textureCoordsT = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        textureCoords = textureCoordsT;
    }

    public void face3(Vector3f position, int imagePosition){
        this.position = position;
        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();
        this.imagePosition = imagePosition;

        float[] verticesT = {
                x, y, z-1,
                x, y-1, z-1,
                x, y-1, z,
                x, y, z
        };
        vertices = verticesT;

        int[] indicesT = {

                0,1,3,
                3,1,2

        };
        indices = indicesT;

        float[] textureCoordsT = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        textureCoords = textureCoordsT;
    }

    public void face4(Vector3f position, int imagePosition){
        this.position = position;
        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();
        this.imagePosition = imagePosition;

        float[] verticesT = {
                x, y, z,
                x+1, y, z,
                x+1, y, z-1,
                x, y, z-1
        };
        vertices = verticesT;

        int[] indicesT = {

                0,1,3,
                3,1,2

        };
        indices = indicesT;

        float[] textureCoordsT = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        textureCoords = textureCoordsT;
    }

    public void face5(Vector3f position, int imagePosition){
        this.position = position;
        float x = position.getX();
        float y = position.getY();
        float z = position.getZ();
        this.imagePosition = imagePosition;

        float[] verticesT = {
                x, y-1, z,
                x+1, y-1, z,
                x+1, y-1, z-1,
                x, y-1, z-1
        };
        vertices = verticesT;

        int[] indicesT = {

                0,1,3,
                3,1,2

        };
        indices = indicesT;

        float[] textureCoordsT = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        textureCoords = textureCoordsT;
    }

    public void prepare(){
        model = loader.loadToVAO(vertices, textureCoords,normals, indices);
        texture = new ModelTexture(loader.loadTextureSprite(imageName, 4, 3, 1, 0, 384, 384, 1, imagePosition));
        texturedModel = new TexturedModel(model, texture);
        entity = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);
    }

    public void render(){
        shader.start();
        renderer.render(entity,shader);
        shader.stop();
    }


}
