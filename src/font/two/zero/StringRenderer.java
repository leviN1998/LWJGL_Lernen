package font.two.zero;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import vbo.Loader;
import vbo.guis.GuiShader;
import vbo.models.RawModel;
import vbo.toolbox.Maths;


/**
 * Created by levin on 23.03.2017.
 */
public class StringRenderer {

        private RawModel quad;
        private GuiShader shader;
        private Loader loader;

        public StringRenderer(Loader loader){
            float[] positions = { -1, 1, -1, -1, 1, 1, 1, -1};
            quad = loader.loadToVAO(positions);
            shader = new GuiShader();
            this.loader = loader;
        }

        public void render(String string){
            shader.start();
            GL30.glBindVertexArray(quad.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_DEPTH_TEST);

            for(CharacterString c : string.getCharacters()){
                //System.out.println(c.getPosition());
                GL13.glActiveTexture(GL13.GL_TEXTURE0);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, c.getTexture());
                Matrix4f matrix = Maths.createTransformationMatrix(Maths.getNormalizedDeviceCoords(c.getPosition()), c.getScale());
                shader.loadTransformation(matrix);
                GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());

            }
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
            shader.stop();
        }

        public void testRenderFont(int texture, Vector2f position, Vector2f scale){
            shader.start();
            GL30.glBindVertexArray(quad.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_DEPTH_TEST);


            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
            Matrix4f matrix = Maths.createTransformationMatrix(position, scale);
            shader.loadTransformation(matrix);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());


            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
            shader.stop();
        }


        public void cleanUp(){
            shader.cleanUp();
            loader.cleanUp();
        }




}
