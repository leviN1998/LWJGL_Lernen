package vbo;

import blöcke.Block5;
import blöcke.IDManager3;
import blöcke.Updater;
import blöcke.project.minecraft.environment.Grid;
import font.FontLoader;
import font.two.zero.String;
import font.two.zero.StringRenderer;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import sketchup.files.ObjEntity;
import sketchup.loaders.SketchUpLoader;
import sketchup.render.engine.ObjMasterRenderer;
import vbo.entities.Camera;
import vbo.entities.Entity;
import vbo.entities.Light;
import vbo.guis.GuiTexture;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.renderEngine.GuiRenderer;
import vbo.renderEngine.MasterRenderer;
import vbo.textures.ModelTexture;
import vbo.toolbox.OBJLoader;
import vbo.toolbox.objConverter.ModelData;
import vbo.toolbox.objConverter.OBJFileLoader;


import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 01.11.2016.
 */
public class Loop {

    public static void main(java.lang.String[] args){

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();


        ModelData modelData = OBJFileLoader.loadOBJ("Baum2");
        RawModel model = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(),
                modelData.getNormals(), modelData.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("Baum2Texture"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        TexturedModel texturedModel = new TexturedModel(model, texture);



        Entity entity = new Entity(texturedModel, new Vector3f(0,0,-20),0,0,0,1);
        //Entity entity = new Block4(new Vector3f(0,0,-10),1,0,0,0,1).getEntity();

        /**
         * Veraltet
         */

        /*ModelData data = OBJFileLoader.loadOBJ("blocks/Grass");
        ModelTexture textureBlock = new ModelTexture(loader.loadTexture("Minecraft_31"));

        textureBlock.setShineDamper(10);
        textureBlock.setReflectivity(0.2f);

        Entity block5 = new Entity(new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()),
                textureBlock), new Vector3f(0,0,-20), 0, 0, 0, 1);
*/
        /**
         * Neu
         */

        Block5 block5 = new Block5(IDManager3.GRASS_ID,new Vector3f(0,0,-20), loader);
        ModelData data = OBJFileLoader.loadOBJ("Test");
        RawModel rawModel = OBJLoader.loadObjModel("Test", loader);
        TexturedModel model1 = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("White")));
        Entity entity1 = new Entity(model1, new Vector3f(0,0,-100), 0, 0, 0, 1);



        Light light = new Light(new Vector3f(0,0,-10), new Vector3f(1,1,1));
        Camera camera = new Camera();

        List<GuiTexture> guis = new ArrayList<>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("fonts/Font"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
        guis.add(gui);

        GuiRenderer guiRenderer = new GuiRenderer(loader);


        //***********************************************SketchUp Testen******************************************

        SketchUpLoader sketchUpLoader = new SketchUpLoader(loader);
        ObjMasterRenderer objMasterRenderer = new ObjMasterRenderer();

        ObjEntity objEntity = new ObjEntity(sketchUpLoader.loadSketchUpFile("objTest/Untitled"), new Vector3f(0,0,-20), 0, 0, 0, 1);
        objEntity = new ObjEntity(sketchUpLoader.loadSketchUpFile("objTest/ErsterGrundriss"), new Vector3f(0,0,-20), 0, 0, 0, 1);
        //***********************************************************************************************************




        //DEBUG-------------------------
        /*int texturetest = -1;
        try {
            texturetest = loader.loadTexture(loader.getPowersofTwoImage(loader.getTileImage(357, 346, 47, 58, ImageIO.read(new FileInputStream("res/fonts/Font.png"))), "res/fonts/empty.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //--------------------------------


        //TEXTE TESTEN-----------------------------

        font.two.zero.Font font = new font.two.zero.Font(FontLoader.loadFont("fonts/Font"), loader, "fonts/Font", "fonts/empty");
        font.two.zero.String string = new String(new Vector2f(0, 0), font, "hallo", 1);

        StringRenderer stringRenderer = new StringRenderer(loader);

        System.out.println(string.getCharacters().size());
        //System.out.println(string.getCharacters().get(0).getPosition().x + "  |  " + string.getCharacters().get(0).getPosition().y);
        //System.out.println(string.getCharacters().get(1).getPosition().x + "  |  " + string.getCharacters().get(0).getPosition().y);
        //System.out.println(string.getCharacters().get(2).getPosition().x + "  |  " + string.getCharacters().get(0).getPosition().y);

        System.out.println(Display.getHeight());

        //-----------------------------------------



        IDManager3 id = new IDManager3();
        camera.setPitch(camera.getPitch()-10);
        Updater updater = new Updater(camera);
        Grid g = new Grid();



        long oldTime = 0;
        long time;
        Mouse.setGrabbed(true);

        while(!Display.isCloseRequested()){
            //updater.update();
            //camera.move(g);

            //entity.increasePosition(0,0,0.01f);
            //entity.increaseRotation(0,0,0f);  //Edit dy //0.1f



            renderer.processEntity(entity);

            //renderer.processEntity(entity1);

            renderer.render(light, camera);



            //*************SketchUpRendering******************
            /*objEntity.increaseRotation(0.1f,0.2f,0);
            objMasterRenderer.processEntity(objEntity);

            objMasterRenderer.render(light, camera);*/
            //**************************************************


            //guiRenderer.render(guis);
            //guiRenderer.testRenderFont(texturetest, new Vector2f(-0f,-0f), new Vector2f(1f,1f));
            string.setNewText(java.lang.String.valueOf(oldTime++));

            //stringRenderer.render(string);




            DisplayManager.updateDisplay();
            oldTime = System.currentTimeMillis();
            //System.out.println();
        }


        sketchUpLoader.cleanUp();
        stringRenderer.cleanUp();
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }



}
