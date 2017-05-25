package blöcke.project.minecraft.environment;

/**
 * Created by levin on 31.01.2017.
 */


import blöcke.Block;
import blöcke.Block5;
import blöcke.IDManager3;
import blöcke.project.minecraft.BlockMasterRenderer;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.entities.Camera;
import vbo.entities.Light;
import vbo.renderEngine.MasterRenderer;
import vbo.toolbox.Maths;

import java.security.Key;

/**
 * Class which handels a 6x6 grid of blocks and is a part of the whole map
 */

public class Chunk {

    /**
     * The Blocks which are to render
     */

    BlockChunk[][][] blocks;

    /**
     * The GridPosition
     */

    int gridX;
    int gridY;

    /**
     * The Grid Object to call methods
     */


    Grid grid;

    /**
     * A boolean to know if its Loaded or not;
     */

    boolean loaded;


    /**
     * Wert für die Chunk-Größe
     */

    public static final int chunkSize = 15;


    /**
     * Variable die anzeigt ob der Spieler sich im Chunk befindet
     */

    boolean accessed;


    /**
     *Constructor: needs to know the position an if it hast to be loaded
     * TODO: implement Biomes and smaller Environment types
     */

    public Chunk(int gridX, int gridY, Grid grid, boolean loaded, Loader loader){
        this.gridX = gridX;
        this.gridY = gridY;
        this.loaded = loaded;
        this.grid = grid;
        accessed = false;

        create(loader);
    }


    /**
     * Method called by GRid class to create the blocks
     * Maybe called in constructor
     */

    public void create(Loader loader){
        blocks = new BlockChunk[chunkSize][chunkSize][chunkSize];

        int start = (chunkSize-1)/2;

        //Provisorisch
        for(int x = 0;x<chunkSize;x++){
            for(int y = 0;y<chunkSize;y++){
                Vector3f position = new Vector3f((gridX * chunkSize) + x - start, -2, (gridY * chunkSize) + y - start);
                blocks[x][0][y] = new BlockChunk(position, new Vector3f(0, 0, 0), 1, IDManager3.GRASS_ID);
            }
        }
        for(int x = 0;x<chunkSize;x++){
            for(int y = 1;y<4;y++){
                for(int z = 0;z<chunkSize;z++){
                    Vector3f position = new Vector3f((gridX * chunkSize) + x -start, y-2, (gridY * chunkSize) + z - start);
                    blocks[x][y][z] = new BlockChunk(position, new Vector3f(0,0,0), 1, IDManager3.AIR_ID);
                }
            }
        }


        addBlock(new Vector3f(4,1,4),IDManager3.GRASS_ID);



    }

    /**
     * Method which will be used if the player exits the Chunk
     * Tells the Grid in which Direction the Player exits to handle the Loading
     */

    public void onExit(float xDiff, float yDiff){
        accessed = false;
        grid.onExit(this, xDiff, yDiff);
    }

    /**
     * Method called by Grid class to tell that a player entered the Chunk
     */

    public void onEnter(){
        accessed = true;
    }

    /**
     * Update Method to check if the player exits the chunk
     * will include Collision Detection when implemented
     */



    @Deprecated
    public void update(int cameraGridX, int cameraGridY, float cameraChunkX, float cameraChunkY, float cameraChunkZ){
        if(accessed && cameraGridX != gridX || accessed && cameraGridY != gridY){
            //System.out.println(((float) cameraGridX - gridX) + "  |  " + ((float) cameraGridY - gridY));
            onExit((float) cameraGridX - gridX, (float) cameraGridY - gridY);
        }

    }

    public void update(Vector3f cameraPosition){
        int x = getNext15((int)cameraPosition.getX());
        int y = getNext15((int)cameraPosition.getZ());

        x = x/15;
        y = y/15;

        if(accessed){
            if(x < gridX){
                onExit(-1,0);
            }
            if(x > gridX){
                onExit(1,0);
            }
            if(y < gridY){
                onExit(0,-1);
            }
            if(y > gridY){
                onExit(0,1);
            }
        }
    }


    /**
     * Render Method (just processes the entitys)
     */

    public void process(BlockMasterRenderer mr){
        //provisorisch
        if(loaded) {
            for (int x = 0; x < chunkSize; x++) {
                for (int y = 0; y < chunkSize; y++) {
                    mr.processBlock(blocks[x][0][y]);
                }
            }
            for(int x = 0; x < chunkSize;x++){
                for(int z = 0; z < chunkSize; z++){
                    mr.processBlock(blocks[x][1][z]);
                }
            }
        }
    }


    public int getGridY() {
        return gridY;
    }

    public int getGridX() {
        return gridX;
    }

    /**
     * Hilfs-Methode um die Grid-Position der Camera zu bestimmen
     */

    public int getNext15(int zahl){
        for (int i = 0;i<8;i++){
            if((zahl+i) % 15 == 0){
                return zahl+i;
            }
            if((zahl-i) % 15 == 0){
                return zahl-i;
            }
        }
        System.err.println("Couldn't find next 15-row");
        System.exit(-1);
        return 0;
    }

    public void addBlock(Vector3f chunkPosition, int id){
        //provisorisch
        int start = (chunkSize-1)/2;
        Vector3f position = new Vector3f((gridX * chunkSize) + chunkPosition.x -start, chunkPosition.y-2, (gridY * chunkSize) + chunkPosition.z - start);

        if(blocks[(int)chunkPosition.x][(int)chunkPosition.y][(int)chunkPosition.z] == null){
            blocks[(int)chunkPosition.x][(int)chunkPosition.y][(int)chunkPosition.z] = new BlockChunk(position, new Vector3f(0,0,0), 1, IDManager3.AIR_ID);
            System.out.println("Fehler bei 'addBlock()' Chunk.class");
        }
        blocks[(int)chunkPosition.x][(int)chunkPosition.y][(int)chunkPosition.z].setBlockID(id);
    }

    public BlockChunk[][][] getBlocks(){
        return blocks;
    }

    public static Chunk getMyChunk(Vector3f position, Grid g){
        int x = Maths.getNext15((int)position.x);
        int y = Maths.getNext15((int)position.z);

        Chunk c = g.getChunks().get(g.getChunkID(x/chunkSize,y/chunkSize));
        return c;
    }

    public static Vector2f getPositionInChunk(Vector3f position, Chunk c){
        float middleX = Maths.getNext15((int) position.x);
        float middleY = Maths.getNext15((int) position.z);

        float relativeX = position.x - middleX;
        float relativeY = position.z - middleY;

        //System.out.println(position.x + "   " + position.z + "  |  " + middleX + "   " + middleY + "  |  " + relativeX + "  "+ relativeY);
        //System.out.println(c.gridX + "   " + c.getGridY());

        return new Vector2f(7 - relativeX, 7 - relativeY);
    }

    /**
     * ganz wichtig für später, da die höhen im 3d system zum Minecraft System verschoben werden müssen
     */

    public static float transformY(float y){
        return y+2;
    }
}
