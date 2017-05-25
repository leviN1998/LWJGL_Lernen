package blöcke.project.minecraft;

import blöcke.IDManager3;
import blöcke.project.minecraft.environment.BlockChunk;
import blöcke.project.minecraft.environment.Chunk;
import blöcke.project.minecraft.environment.Grid;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by levin on 21.04.2017.
 */
public class Collisions {

    public Collisions(){

    }

    public static boolean updateCameraSides(Vector3f newPosition, Grid g){
        Chunk chunk = Chunk.getMyChunk(newPosition, g);
        Vector2f chunkPosition = Chunk.getPositionInChunk(newPosition, chunk);
        BlockChunk[] stand = new BlockChunk[2];
        try {
            stand[0] = chunk.getBlocks()[(int) chunkPosition.x][(int) Chunk.transformY(newPosition.y)-1]
                    [(int) chunkPosition.y];
            stand[1] = chunk.getBlocks()[(int) chunkPosition.x][(int) Chunk.transformY(newPosition.y)]
                    [(int) chunkPosition.y];
            //System.out.println(stand[0].getBlockID());
            if (stand[0].getBlockID() == IDManager3.AIR_ID && stand[1].getBlockID() == IDManager3.AIR_ID) {
                return false;
            }
        }catch (Exception x){
            x.printStackTrace();
            System.out.println(chunkPosition.x + "  |  " + Chunk.transformY(newPosition.y) + "  |  " + chunkPosition.y);
            System.exit(-1);
        }
        return true;
    }

    public static Vector2f[] getMaxDistance(Vector3f position, Grid g){
        Vector2f[] output = new Vector2f[2];
        Vector2f outPlus = new Vector2f();
        Vector2f outMinus = new Vector2f();

        Chunk chunk = Chunk.getMyChunk(position, g);
        Vector2f chunkPosition = Chunk.getPositionInChunk(position, chunk);

        // +x
        chunk = Chunk.getMyChunk(new Vector3f(position.x+1,position.y,position.z),g);
        chunkPosition = Chunk.getPositionInChunk(position, chunk);

        if(chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)-1][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID &&
                chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID){
            outPlus.x = 1;
        }else {
            outPlus.x = ((int)chunkPosition.x ) - chunkPosition.x;
            System.out.println("hallo");
        }

        // -x
        chunk = Chunk.getMyChunk(new Vector3f(position.x-1,position.y,position.z),g);
        chunkPosition = Chunk.getPositionInChunk(position, chunk);

        if(chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)-1][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID &&
                chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID){
            outMinus.x = -1;
        }else {
            outMinus.x = ((int)chunkPosition.x) - chunkPosition.x;
        }

        //+y
        chunk = Chunk.getMyChunk(new Vector3f(position.x,position.y,position.z+1),g);
        chunkPosition = Chunk.getPositionInChunk(position, chunk);

        if(chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)-1][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID &&
                chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID){
            outPlus.y = 1;
        }else {
            outPlus.y = ((int)chunkPosition.y) - chunkPosition.y;
        }

        //-y
        chunk = Chunk.getMyChunk(new Vector3f(position.x,position.y,position.z-1),g);
        chunkPosition = Chunk.getPositionInChunk(position, chunk);

        if(chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)-1][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID &&
                chunk.getBlocks()[(int)chunkPosition.x][(int) Chunk.transformY(position.y)][(int)chunkPosition.y].getBlockID() == IDManager3.AIR_ID){
            outMinus.y = -1;
        }else {
            outMinus.y = ((int)chunkPosition.y) - chunkPosition.y;
        }

        output[0] = outPlus;
        output[1] = outMinus;
        return output;


    }

    private static boolean compareChunks(Chunk a, Chunk b){
        if(a.getGridX() == b.getGridX() && a.getGridY() == b.getGridY()){
            return true;
        }
        return false;
    }

}


