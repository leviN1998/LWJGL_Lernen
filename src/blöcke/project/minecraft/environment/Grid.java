package blöcke.project.minecraft.environment;

/**
 * Created by levin on 31.01.2017.
 */


import blöcke.project.minecraft.BlockMasterRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import vbo.Loader;
import vbo.entities.Camera;
import vbo.entities.Light;
import vbo.renderEngine.MasterRenderer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class created for handling the Chunks in a big Grid
 */

public class Grid {

    /**
     * List of Chunks to make sure that the world can be expanded infinitely
     */

    List<Chunk> chunks;

    /**
     * Array of activated Chunks for Rendering (in this version there will be 9 active Chunks)
     * includes the ids for the chunks to find it in the List above
     */

    int[] activeChunks;

    /**
     * LOader class for loading the Blocks
     */

    Loader loader;

    /**
     * Data Construct for getting the ChunkIDs by putting in Coordinates
     * ID = -1 = empty chunk (not created)
     */

    //List for the ChunkIDs in Quadrant I (x+ ; y+)        First List includes X-Values, second Y-Values
    List<ArrayList<Integer>> chunkIDq1;
    //List for the ChunkIDs in Quadrant II (x- ; y+)
    List<ArrayList<Integer>> chunkIDq2;
    //List for the ChunkIDs in Quadrant III ( x- ; y-)
    List<ArrayList<Integer>> chunkIDq3;
    //List for the ChunkIDs in Quadrant IV (x+ ; y-)
    List<ArrayList<Integer>> chunkIDq4;

    /**
     * Constructor (not sure yet what it needs) TODO: FILL
     */

    public Grid(){
        init();
        initChunks();
    }

    /**
     * Init Method (Für Variablen)
     */

    private void init(){
        chunks = new ArrayList<Chunk>();
        activeChunks = new int[9];
        loader = new Loader();
        chunkIDq1 = new ArrayList<ArrayList<Integer>>();
        chunkIDq2 = new ArrayList<ArrayList<Integer>>();
        chunkIDq3 = new ArrayList<ArrayList<Integer>>();
        chunkIDq4 = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * Methode um das erste mal Chunks zu spawnen
     */

    public void initChunks(){
        setUpNewRaster(0,0);
    }

    /**
     * Method to spawn a Chunk (needs to fill the List with its IDs)
     */

    public void spawnChunk(int gridX, int gridY){
        if(checkForSpace(gridX, gridY)) {

            Chunk chunk = new Chunk(gridX, gridY, this, true, loader);
            chunks.add(chunk);
            addIDToQuadrant(chunks.size() - 1, gridX, gridY);

        }
    }

    /**
     * Methode um zu checken ob der platz in der liste schon belegt ist
     */

    public boolean checkForSpace(int gridX, int gridY){
        boolean b;
        try{
            if(getChunkID(gridX, gridY) != -1){
                b = false;
            }else {
                b = true;
            }
        }catch (Exception x){
            b = true;
        }

        return b;
    }

    /**
     * Update Method for updating loaded chunks
     */

    //TODO:: BUGFIXES

    public void update(Vector3f cameraPosition){
        for(int i = 0; i<9;i++){
            chunks.get(activeChunks[i]).update(cameraPosition);
        }

    }

    /**
     * Method to handle exiting chunks needs to reCalculate rendered Chunks and maybe spawn new ones
     */

    public void onExit(Chunk c, float xDiff, float yDiff){
        int gridX = c.gridX;
        int gridY = c.gridY;

        if(xDiff > 0){
            gridX++;
        }else if(xDiff < 0){
            gridX--;
        }

        if(yDiff > 0){
            gridY++;
        }else if(yDiff < 0){
            gridY--;
        }

        setUpNewRaster(gridX, gridY);
    }


    /**
     * Method for setting up a new 3*3 field of chunks (called in exit method)
     */

    public void setUpNewRaster(int gridX, int gridY){
        spawnChunk(gridX, gridY);
        activateChunk(getChunkID(gridX, gridY), 0);

        spawnChunk(gridX, gridY+1);
        activateChunk(getChunkID(gridX, gridY+1), 1);

        spawnChunk(gridX-1, gridY+1);
        activateChunk(getChunkID(gridX-1, gridY+1), 2);

        spawnChunk(gridX-1, gridY);
        activateChunk(getChunkID(gridX-1, gridY), 3);

        spawnChunk(gridX-1, gridY-1);
        activateChunk(getChunkID(gridX-1, gridY-1), 4);

        spawnChunk(gridX, gridY-1);
        activateChunk(getChunkID(gridX, gridY-1), 5);

        spawnChunk(gridX+1, gridY-1);
        activateChunk(getChunkID(gridX+1, gridY-1), 6);

        spawnChunk(gridX+1, gridY);
        activateChunk(getChunkID(gridX+1, gridY), 7);

        spawnChunk(gridX+1, gridY+1);
        activateChunk(getChunkID(gridX+1, gridY+1), 8);

        chunks.get(activeChunks[0]).onEnter();
    }


    /**
     * Method to get the Chunk ID by putting in its Coords
     */

    public int getChunkID(int gridX, int gridY){
        int id = -1;
        if(gridX >=0){
            if(gridY >= 0){
                //Quadrant 1 einschließlich 0/0
                id = chunkIDq1.get(gridX).get(gridY);
            }else if(gridY < 0){
                //Quadrant IV
                id = chunkIDq4.get(gridX).get(gridY*-1);
            }
        }else if(gridX < 0){
            if(gridY >= 0){
                //Quadrant II
                id = chunkIDq2.get(gridX*-1).get(gridY);
            }else if(gridY < 0){
                //Quadrant III
                id = chunkIDq3.get(gridX*-1).get(gridY*-1);
            }
        }
        return id;
    }

    /**
     * Method which renders all Loaded Chunks
     */

    public void processBlocks(BlockMasterRenderer mr){
        //chunks.get(activeChunks[0]).process(mr);
        //return;
        for(int i = 0;i<9;i++){
            chunks.get(activeChunks[i]).process(mr);
        }

    }


    /**
     * Method to add an ID to the Quadrant list
     */

    public void addIDToQuadrant(int id, int gridX, int gridY){
        if(gridX >=0){
            if(gridY >= 0){

                //Quadrant 1 einschließlich 0/0
                putDataInToList(chunkIDq1, gridX, gridY, id);

            }else if(gridY < 0){
                //Quadrant IV
                putDataInToList(chunkIDq4, gridX, gridY, id);
            }
        }else if(gridX < 0){
            if(gridY >= 0){
                //Quadrant II
                putDataInToList(chunkIDq2, gridX, gridY, id);

            }else if(gridY < 0){
                //Quadrant III
                putDataInToList(chunkIDq3, gridX, gridY, id);

            }
        }
    }


    /**
     * kleine Methode etwas in eine Liste einzufügen
     */

    public List<ArrayList<Integer>> putDataInToList(List<ArrayList<Integer>> list, int gridX, int gridY, int id ){
        if(gridX < 0){
            gridX = gridX * (-1);
        }
        if(gridY < 0){
            gridY = gridY * (-1);
        }

        if(gridX > (list.size()-1)){   //index noch nicht erstellt

            for(int i = list.size(); i <= gridX;i++){
                list.add(i,new ArrayList<>());
            }

        }

        if(gridY > list.get(gridX).size()-1){
            for(int i = list.get(gridX).size(); i <= gridY; i++){
                list.get(gridX).add(i,-1);
            }
        }


        list.get(gridX).add(gridY, id);


        return list;

    }


    /**
     * TODO: Big Question is now how to Fill the chunks
     */


    /**
     * Methode um die aktiven Chunks von aussen zu beinflussen
     */

    public void activateChunk(int chunkID, int listPosition){
        activeChunks[listPosition] = chunkID;
    }


    /**
     * Methode um alles aufzuräumen -> Ende des Spieles
     */

    public void cleanUp(){
        loader.cleanUp();
    }


    public int[] getActiveChunks() {
        return activeChunks;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }
}
