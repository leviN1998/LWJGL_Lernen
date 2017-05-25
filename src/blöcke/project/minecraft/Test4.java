package blöcke.project.minecraft;


import blöcke.Block5;
import blöcke.IDManager3;
import blöcke.Updater;
import blöcke.project.minecraft.environment.Chunk;
import blöcke.project.minecraft.environment.Grid;
import font.FontLoader;
import font.two.zero.Font;
import font.two.zero.String;
import font.two.zero.StringRenderer;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import vbo.DisplayManager;
import vbo.Loader;
import vbo.entities.Camera;
import vbo.entities.Light;
import vbo.renderEngine.MasterRenderer;

/**
 * Created by levin on 30.01.2017.
 */
public class Test4 {
    public static void main(java.lang.String[] args){
        /**
         * Display erstellun und anfangswerte für OpenGl festlegen etc.
         */

        DisplayManager.createDisplay();


        /**
         * Utils initialisieren
         */

        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();
        Updater updater;

        /**
         * Hier ist Platz die Models zu erstellen
         */

        Grid grid = new Grid();



        //*************Font Stuff**************************************
        //TODO: LeerZeichen-Bug beheben
        Font font = new Font(FontLoader.loadFont("fonts/Font"), loader, "fonts/Font", "fonts/empty");
        String position = new String(new Vector2f(-0.9f,0.9f), font, "Position: ", 0);
        StringRenderer stringRenderer = new StringRenderer(loader);
        //***********************************************************


        /**
         * Lichter und Camera
         */

        Light light = new Light(new Vector3f(0,0,10), new Vector3f(4f,4f,4f));
        Camera camera = new Camera();
        //Der Updater wird erst hier initialisiert, da er die kamera braucht
        updater = new Updater(camera);
        //Damit das Mousehandling funktioniert
        Mouse.setGrabbed(true);


        //*********Der Renderer für das Grid*****************
        BlockMasterRenderer blockMasterRenderer = new BlockMasterRenderer(loader);
        //***************************************************


        /**
         * GameLoop
         */

        while(!Display.isCloseRequested()){

            /**
             * Updates
             */

            updater.update();
            camera.move(grid);
            grid.update(camera.getPosition());


            //*****Die Camera checkt ob es Kollisionen gibt*************
            //
            //**********************************************************

            //**********Font updates*****************
            position.setNewText("position: "+(int) camera.getPosition().getX() +
                    " / " + (int) camera.getPosition().getY() + " / " + (int) camera.getPosition().getZ());

            java.lang.String s1 = java.lang.String.valueOf(grid.getChunks().get(grid.getActiveChunks()[0]).getGridX());
            java.lang.String s2 = java.lang.String.valueOf(grid.getChunks().get(grid.getActiveChunks()[0]).getGridY());


            java.lang.String s3 = java.lang.String.valueOf((int)camera.getPosition().getX());
            java.lang.String s4 = java.lang.String.valueOf((int)camera.getPosition().getZ());

            position.setNewText(s1 + " / " + s2 + "    |   "+ s3+ " / " +s4);
            //******************************************


            /**
             * Rendering mit:
             * processEntity(Entity e);
             * und   render(Light l, Camera c);
             */

            //Pre - Rendering:

            grid.processBlocks(blockMasterRenderer);



            //Post - Rendering

            blockMasterRenderer.render(light, camera);


            //**********Fonts als letztes rendern****************
            stringRenderer.render(position);
            //***************************************************


            /**
             * Display updaten
             */

            DisplayManager.updateDisplay();
        }

        /**
         * CleanUp scheiss
         */

        stringRenderer.cleanUp();
        grid.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
