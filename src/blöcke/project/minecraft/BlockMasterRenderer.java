package blöcke.project.minecraft;

import blöcke.project.minecraft.environment.BlockChunk;
import blöcke.project.minecraft.environment.BlockManager;
import vbo.Loader;
import vbo.entities.Camera;
import vbo.entities.Light;
import vbo.shaders.StaticShader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by levin on 30.03.2017.
 */
public class BlockMasterRenderer {

    private BlockManager manager;
    private List<BlockChunk> toRender;

    private StaticShader shader;
    private BlockRenderer renderer;

    public BlockMasterRenderer(Loader loader){
        manager = new BlockManager();
        toRender = new ArrayList<>();
        shader = new StaticShader();
        renderer = new BlockRenderer(shader, loader);
    }

    public void render(Light sun, Camera camera){
        renderer.prepare();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(toRender);
        shader.stop();
        toRender.clear();
    }

    public void processBlock(List<BlockChunk> blocks) {
        toRender.addAll(blocks);
    }

    public void processBlock(BlockChunk blockChunk){
        toRender.add(blockChunk);
    }


    public void cleanUp(){
        renderer.cleanUp();
        shader.cleanUp();
    }
}


