package blöcke.myObjStuff;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import vbo.entities.Entity;
import vbo.models.RawModel;
import vbo.models.TexturedModel;
import vbo.shaders.StaticShader;
import vbo.textures.ModelTexture;
import vbo.toolbox.Maths;

import java.util.List;
import java.util.Map;

/**
 * Created by levin on 13.05.2017.
 */
public class MaterialRenderer {

        private static final float FOV = 70;
        private static final float NEAR_PLANE = 0.1f;
        private static final float FAR_PLANE = 1000f;

        private Matrix4f projectionMatrix;
        private StaticShader shader;

        public MaterialRenderer(StaticShader shader){
            this.shader = shader;
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glCullFace(GL11.GL_BACK);
            createProjectionMatrix();
            shader.start();
            shader.loadProjectionMatrix(projectionMatrix);
            shader.stop();
        }

        public void prepare(){
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glClearColor(0,0.3f,0,1);

        }

        public void render(List<Map<TexturedModel, List<Entity>>> entities){
            for(Map<TexturedModel, List<Entity>> map : entities){
                for(TexturedModel model : map.keySet()){
                    //prepare
                    prepareTexturedModel(model);
                    List<Entity> batch = map.get(model);
                    for(Entity entity : batch){
                        prepareInstance(entity);
                        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
                    }
                    unbindTexturedModel();
                }
            }
        }

        private void prepareTexturedModel(TexturedModel model){
            RawModel rawModel = model.getRawModel();
            GL30.glBindVertexArray(rawModel.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);
            ModelTexture texture = model.getTexture();
            shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
        }

        private void unbindTexturedModel(){
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
        }

        private void prepareInstance(Entity entity){
            Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),
                    entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
            shader.loadTransformationMatrix(transformationMatrix);
        }

        @Deprecated
        public void render(Entity entity, StaticShader shader){
            TexturedModel texturedModel = entity.getModel();
            RawModel model = texturedModel.getRawModel();
            GL30.glBindVertexArray(model.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);
            Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),
                    entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
            shader.loadTransformationMatrix(transformationMatrix);
            ModelTexture texture = texturedModel.getTexture();
            shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
            GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
        }

        private void createProjectionMatrix(){
            float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
            float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
            float x_scale = y_scale / aspectRatio;
            float frustum_length = FAR_PLANE - NEAR_PLANE;

            projectionMatrix = new Matrix4f();
            projectionMatrix.m00 = x_scale;
            projectionMatrix.m11 = y_scale;
            projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
            projectionMatrix.m23 = -1;
            projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
            projectionMatrix.m33 = 0;
        }



}
