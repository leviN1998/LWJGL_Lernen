package blöcke;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.renderEngine.Renderer;
import vbo.entities.Camera;
import vbo.entities.Entity;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.shaders.StaticShader;
import vbo.textures.ModelTexture;

/**
 * Created by levin on 14.11.2016.
 */
@Deprecated
public class Block2 {

    private Entity[] entities;
    private Vector3f position;
    private Loader loader;
    private Renderer renderer;
    private StaticShader shader;
    private Camera camera;
    RawModel[] models;
    ModelTexture[] textures;
    TexturedModel[] texturedModels;

    private float[][] vertices;
    private float[][] textureCoords;
    private int[][] indices;

    private Vector3f[] entityPositions;

    private int[] indicesSmall = {
            0,1,3,
            3,1,2
    };

    private float[] textureCoordsSmall = {
            0, 0,
            0, 1,
            1, 1,
            1, 0
    };


    public Block2(Vector3f position, Loader loader, Renderer renderer, StaticShader shader, Camera camera, String[] imageNames, int[] imagePositions){

        this.loader = loader;
        this.renderer = renderer;
        this.shader = shader;
        this.camera = camera;

        Vector3f[] entityPositionsT = {
                new Vector3f(position),
                new Vector3f(position.x+1, position.y, position.z),
                new Vector3f(position.x+1, position.y, position.z-1),
                new Vector3f(position.x, position.y, position.z-1),
                new Vector3f(position.x, position.y, position.z-1),
                new Vector3f(position.x, position.y-1, position.z-1)
        };
        entityPositions = entityPositionsT;



        this.position = position;

        entities = new Entity[6];
        models = new RawModel[6];
        textures = new ModelTexture[6];
        texturedModels = new TexturedModel[6];

        setData(getVertexData(getVertices(position)));

        for(int i = 0; i < 6 ;i++) {
            //models[i] = loader.loadToVAO(vertices[i], textureCoords[i], indices[i]);
            textures[i] = new ModelTexture(loader.loadTextureSprite(imageNames[i], 16, 16, 1, 0, 64, 64, 1, imagePositions[i]));
            texturedModels[i] = new TexturedModel(models[i], textures[i]);
            entities[i] = new Entity(texturedModels[i], new Vector3f(0, 0, 0), 0, 0, 0, 1);
        }
    }

    public static Vector3f[][] getVertices(Vector3f position){
        Vector3f[][] vertex = new Vector3f[6][4];
        vertex[0][0] = new Vector3f(position);
        vertex[0][1] = new Vector3f(position.x, position.y-1,position.z);
        vertex[0][2] = new Vector3f(position.x+1, position.y-1,position.z); //Front
        vertex[0][3] = new Vector3f(position.x+1, position.y,position.z);

        vertex[1][0] = new Vector3f(position.x+1, position.y,position.z);
        vertex[1][1] = new Vector3f(position.x+1, position.y-1,position.z);
        vertex[1][2] = new Vector3f(position.x+1, position.y-1,position.z-1); //2. Rechts rum
        vertex[1][3] = new Vector3f(position.x+1, position.y,position.z-1);

        vertex[2][0] = new Vector3f(position.x+1, position.y,position.z-1);
        vertex[2][1] = new Vector3f(position.x+1, position.y-1,position.z-1);
        vertex[2][2] = new Vector3f(position.x, position.y-1,position.z-1); //Hinten
        vertex[2][3] = new Vector3f(position.x, position.y,position.z-1);

        vertex[3][0] = new Vector3f(position.x, position.y,position.z-1);
        vertex[3][1] = new Vector3f(position.x, position.y-1,position.z-1);
        vertex[3][2] = new Vector3f(position.x, position.y-1,position.z); //Links
        vertex[3][3] = new Vector3f(position.x, position.y,position.z);

        vertex[4][0] = new Vector3f(position.x, position.y,position.z-1);
        vertex[4][1] = new Vector3f(position.x, position.y,position.z);
        vertex[4][2] = new Vector3f(position.x+1, position.y,position.z); //Oben
        vertex[4][3] = new Vector3f(position.x+1, position.y,position.z-1);

        vertex[5][0] = new Vector3f(position.x, position.y-1,position.z-1);
        vertex[5][1] = new Vector3f(position.x, position.y-1,position.z);
        vertex[5][2] = new Vector3f(position.x+1, position.y-1,position.z); //Unten
        vertex[5][3] = new Vector3f(position.x+1, position.y-1,position.z-1);

        return vertex;
    }

    public static float[][] getVertexData(Vector3f[][] verticesRaw){
        float[][] output = new float[6][12];
        for(int y = 0; y<output.length;y++) {
            for (int x = 0; x<verticesRaw[0].length;x++){
                output[y][x*3] = verticesRaw[y][x].x;
                output[y][(x*3)+1] = verticesRaw[y][x].y;
                output[y][(x*3)+2] = verticesRaw[y][x].z;
            }
        }

        return output;

    }

    private void setData(float[][] data){
        vertices = data;
        indices = new int[6][12];
        textureCoords = new float[6][12];
        for(int i = 0; i<indices.length;i++) {
            textureCoords[i] = textureCoordsSmall;
            indices[i] = indicesSmall;
        }
    }

    public void render(){
        renderer.prepare();

        shader.start();
        shader.loadViewMatrix(camera);
        for(int i = 0; i<entities.length;i++){
            renderer.render(entities[i], shader);
        }
        shader.stop();
    }

    public void update(){
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
            increaseRotation(0,0,1);
        }
    }

    public void increasePosition(float dx, float dy, float dz){
        for(int i = 0; i<entities.length;i++) {
            entities[i].increasePosition(dx,dy,dz);
        }
    }

    public void increaseRotation(float dx, float dy, float dz){
        for(int i = 0; i<entities.length;i++) {
            entities[i].increaseRotation(dx,dy,dz);
        }
    }



}
