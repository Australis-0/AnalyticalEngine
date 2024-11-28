//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.Renderer;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import aoc.kingdoms.lukasz.jakowski.zOther.XY;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.map.MapScale;
import aoc.kingdoms.lukasz.map.map.Map_Data;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.pieChart.PieChart_Renderer;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.utilities.FPS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Renderer {
    public static Viewport viewport;
    public static OrthographicCamera camera;
    public static SpriteBatch oSB;
    public static FPS uFPS;
    public static PieChart_Renderer pieChartRenderer;
    public static SpriteBatch oSBBorder;
    public static TextureRegion drawerPix;
    public static ShapeDrawer shapeDrawer;
    public static float shaderTime;
    public static float shaderTime2;
    public static ShaderProgram shaderDefault;
    public static ShaderProgram shaderDefault_FBO;
    public static ShaderProgram shaderWater;
    public static ShaderProgram shaderBlackWhite;
    public static ShaderProgram shaderDefaultProvince;
    public static ShaderProgram shaderAlpha;
    public static ShaderProgram shaderAlpha2;
    public static ShaderProgram shaderAlpha_Pattern;
    public static ShaderProgram shaderAlpha_Map;
    public static ShaderProgram shaderAlpha_MapSea;
    public static ShaderProgram shaderWater2;
    public static ShaderProgram shaderOutline;
    public static ShaderProgram shaderBlur;
    public static ShaderProgram shaderWater3;
    public static boolean drawArmyInProvince = true;
    private static int numOfScissors = 0;
    public static Rectangle peekBounds = null;
    public static Color BACKGROUND_COLOR = new Color(0.0509F, 0.1215F, 0.1921F, 1.0F);
    public static boolean updateBackgroundColor = true;
    public static CopyOnWriteArrayList<Game.SimpleTask> simpleTasksCivNames = new CopyOnWriteArrayList();
    public static List<SimpleTaskArmyText> simpleTasks_ArmyWidth = Collections.synchronizedList(new ArrayList());
    public static int boxBGExtraY;
    public static int iBoxCornerX;
    public static long BOX_CORNER_TIME;
    public static long BOX_CORNER_TIMER;
    public static GlyphLayout_Game glyphLayout;
    public static String charset;
    public static List<BitmapFont> fontMain;
    public static int fontMainSize;
    public static BitmapFont fontArmy_GlyphLayout;
    public static List<BitmapFont> fontBorder;
    public static int fontBorderSize;
    public static final Vector3 textRotatedVector3;
    public static String sLoadingText;
    public static int iLoadingTextWidth;
    public static long loadingTime;
    public static final int LOADING_CHANGE_TEXT_TIME = 4000;

    public static final int getHover_ExtraPosX() {
        return 25;
    }

    public static final int getHover_ExtraPosY() {
        return 30;
    }

    public Renderer(int nGameWidth, int nGameHeight) {
        CFG.GAME_WIDTH = nGameWidth;
        CFG.GAME_HEIGHT = nGameHeight;
        camera = new OrthographicCamera((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
        camera.setToOrtho(false, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
        viewport = new FitViewport((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT, camera);
        this.oSB = new SpriteBatch();
        uFPS = new FPS();
        oSBBorder = new SpriteBatch();
        Images.pix = ImageManager.buildPix_IMG();
        Images.pix2 = ImageManager.buildPix();
        drawerPix = new TextureRegion(Images.pix.getTexture());
        shapeDrawer = new ShapeDrawer(oSBBorder, drawerPix);
        this.loadShaders();
    }

    private final void loadShaders() {
        String defaultVertex = FileManager.loadFile("game/shader/default_vertex.glsl").readString();
        shaderDefault = new ShaderProgram(defaultVertex, FileManager.loadFile("game/shader/default_fragment.glsl").readString());
        shaderBlackWhite = new ShaderProgram(defaultVertex, FileManager.loadFile("game/shader/blackWhite_fragment.glsl").readString());
        shaderWater = new ShaderProgram(defaultVertex, FileManager.loadFile("game/shader/water_fragment.glsl").readString());
        String defaultFragment_Province = "#ifdef GL_ES\n    precision mediump float;\n#endif\n\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\n\nvoid main() {\nvec4 mask = texture2D(u_texture, v_texCoords);\n    gl_FragColor = vec4(v_color.rgb, v_color.a * mask.a);\n}";
        shaderDefaultProvince = new ShaderProgram(defaultVertex, defaultFragment_Province);
        String defaultFragment_FBO = "#ifdef GL_ES\n    precision mediump float;\n#endif\n\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\n\nvoid main() {\n   gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n   gl_FragColor.rgb *= glFragColor.a;\n}";
        shaderDefault_FBO = new ShaderProgram(defaultVertex, defaultFragment_FBO);
        String flagFragment = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform sampler2D u_texture2;\nvoid main()    \n{\n vec4 mask = texture2D(u_texture2, v_texCoords);\n gl_FragColor = vec4(mask.rgb, mask.a * (v_color.a * texture2D(u_texture, v_texCoords).a));\n}";
        String vertexShader = "attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n";
        shaderAlpha = new ShaderProgram(vertexShader, flagFragment);
        ShaderProgram var10000 = shaderAlpha;
        ShaderProgram.pedantic = false;
        shaderAlpha.bind();
        shaderAlpha.setUniformi("u_texture", 0);
        shaderAlpha.setUniformi("u_texture2", 1);
        shaderAlpha2 = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/alpha_shader.glsl").readString());
        var10000 = shaderAlpha2;
        ShaderProgram.pedantic = false;
        shaderAlpha2.bind();
        shaderAlpha2.setUniformi("u_texture", 0);
        shaderAlpha2.setUniformi("u_texture2", 1);
        shaderAlpha2.setUniformf("u_useMask", 1.0F);
        shaderAlpha2.setUniformf("u_maskScale", 20.0F);
        shaderAlpha2.setUniformf("u_maskOffset", 0.0F, 0.0F);
        shaderAlpha_Pattern = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/alpha_shader_pattern.glsl").readString());
        var10000 = shaderAlpha_Pattern;
        ShaderProgram.pedantic = false;
        shaderAlpha_Pattern.bind();
        shaderAlpha_Pattern.setUniformi("u_texture", 0);
        shaderAlpha_Pattern.setUniformi("u_texture2", 1);
        shaderAlpha_Pattern.setUniformf("u_useMask", 1.0F);
        shaderAlpha_Pattern.setUniformf("u_maskScale", 20.0F);
        shaderAlpha_Pattern.setUniformf("u_maskOffset", 0.0F, 0.0F);
        shaderAlpha_Map = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/map_overlay_fragment.glsl").readString());
        var10000 = shaderAlpha_Map;
        ShaderProgram.pedantic = false;
        shaderAlpha_Map.bind();
        shaderAlpha_Map.setUniformi("u_texture", 0);
        shaderAlpha_Map.setUniformi("u_texture2", 1);
        shaderAlpha_Map.setUniformf("u_useMask", 1.0F);
        shaderAlpha_Map.setUniformf("u_maskScale", 20.0F);
        shaderAlpha_Map.setUniformf("u_maskOffset", 0.0F, 0.0F);
        shaderAlpha_MapSea = new ShaderProgram(vertexShader, FileManager.loadFile("game/shader/map_overlay_sea_fragment.glsl").readString());
        var10000 = shaderAlpha_MapSea;
        ShaderProgram.pedantic = false;
        shaderAlpha_MapSea.bind();
        shaderAlpha_MapSea.setUniformi("u_texture", 0);
        shaderAlpha_MapSea.setUniformi("u_texture2", 1);
        shaderAlpha_MapSea.setUniformf("u_useMask", 1.0F);
        shaderAlpha_MapSea.setUniformf("u_maskScale", 20.0F);
        shaderAlpha_MapSea.setUniformf("u_maskOffset", 0.0F, 0.0F);
        String testFragment = "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP\n#endif\n\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\n\n\nuniform sampler2D u_texture;\nuniform float time;\nuniform vec2 resolution;\n\n\nconst float PI = 3.1415;\n// 速度\nconst float speed = 0.025;\nconst float speed_x = 0.05;\nconst float speed_y = 0.05;\n\n// 折射角\nconst float emboss = 0.3; \t\t// 凹凸强度\nconst float intensity = 2.4;\t// 强度\nconst int steps = 8;  \t\t\t// 波纹密度\nconst float frequency = 4.0;  \t// 频率\nconst float angle = 7.0;\n\nconst float delta = 50.0;  \t\t// 增幅（越小越激烈）\nconst float intence = 200.0;   \t// 明暗强度\n\n// 高光\nconst float reflectionCutOff = 0.012;\nconst float reflectionIntence = 80000.0;\n\nfloat col(vec2 coord)\n{\n    float delta_theta = 2.0 * PI / angle;\n    float col = 0.0;\n    float theta = 0.0;\n    for (int i = 0; i < steps; i++)\n    {\n        vec2 adjc = coord;\n        theta = delta_theta * float(i);\n        adjc.x += cos(theta)*time*speed + time * speed_x;\n        adjc.y -= sin(theta)*time*speed - time * speed_y;\n        col = col + cos((adjc.x * cos(theta) -\n            adjc.y * sin(theta)) * frequency) * intensity;\n    }\n    return cos(col);\n}\n\n\nvoid main()\n{\n    vec2 p = v_texCoords, c1 = p, c2 = p;\n    float cc1 = col(c1);\n\n    c2.x += resolution.x/delta;\n    float dx = emboss*(cc1-col(c2))/delta;\n\n    c2.x = p.x;\n    c2.y += resolution.y/delta;\n    float dy = emboss*(cc1-col(c2))/delta;\n    c1.x = c1.x +dx;\n    c1.y =  c1.y+dy;\n\n    float alpha = 1.0+dot(dx,dy)*intence;\n\n\n    vec4 col = texture2D(u_texture,c1);\n    col *= alpha;\n    gl_FragColor =  col;\n}";
        shaderWater2 = new ShaderProgram(defaultVertex, testFragment);
        testFragment = "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP\n#endif\n\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\n\n\nuniform sampler2D u_texture;\nuniform sampler2D u_texture2;\nuniform float time;\nuniform vec2 resolution;\nuniform float u_maskScale;\nuniform float u_maskScaleY;\nuniform float u_useMask;\nuniform vec2 u_maskOffset;\n\n\nconst float PI = 3.1415;\n// 速度\nconst float speed = 0.03;\nconst float speed_x = 0.06;\nconst float speed_y = 0.06;\n\n// 折射角\nconst float emboss = 0.3; \t\t// 凹凸强度\nconst float intensity = 2.4;\t// 强度\nconst int steps = 8;  \t\t\t// 波纹密度\nconst float frequency = 4.0;  \t// 频率\nconst float angle = 7.0;\n\nconst float delta = 50.0;  \t\t// 增幅（越小越激烈）\nconst float intence = 200.0;   \t// 明暗强度\n\n// 高光\nconst float reflectionCutOff = 0.012;\nconst float reflectionIntence = 80000.0;\n\nfloat col(vec2 coord)\n{\n    float delta_theta = 2.0 * PI / angle;\n    float col = 0.0;\n    float theta = 0.0;\n    for (int i = 0; i < steps; i++)\n    {\n        vec2 adjc = coord;\n        theta = delta_theta * float(i);\n        adjc.x += cos(theta)*time*speed + time * speed_x;\n        adjc.y -= sin(theta)*time*speed - time * speed_y;\n        col = col + cos((adjc.x * cos(theta) -\n            adjc.y * sin(theta)) * frequency) * intensity;\n    }\n    return cos(col);\n}\n\n\nvoid main()\n{\n    vec2 p = v_texCoords, c1 = p, c2 = p;\n    float cc1 = col(c1);\n\n    c2.x += resolution.x/delta;\n    float dx = emboss*(cc1-col(c2))/delta;\n\n    c2.x = p.x;\n    c2.y += resolution.y/delta;\n    float dy = emboss*(cc1-col(c2))/delta;\n    c1.x = c1.x +dx;\n    c1.y =  c1.y+dy;\n\n    float alpha = 1.0+dot(dx,dy)*intence;\n\n\n    vec4 col = texture2D(u_texture,c1);\n vec2 newCoords = vec2(v_texCoords.x * u_maskScale, v_texCoords.y * u_maskScaleY);\n vec4 mask = vec4(1.0, 1.0, 1.0, 1.0); \n\tmask = texture2D(u_texture2, v_texCoords);\n  gl_FragColor = vec4(col.rgb, mask.a * col.a);\n}";
        shaderWater3 = new ShaderProgram(defaultVertex, testFragment);
        shaderWater3.bind();
        shaderWater3.setUniformi("u_texture", 0);
        shaderWater3.setUniformi("u_texture2", 1);
        shaderWater3.setUniformf("u_useMask", 1.0F);
        shaderWater3.setUniformf("u_maskScale", 20.0F);
        shaderWater3.setUniformf("u_maskOffset", 0.0F, 0.0F);
        testFragment = "#ifdef GL_ES\nprecision mediump float;\n#endif\n\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nuniform sampler2D u_texture;\nuniform float outlineSize;\nuniform vec3 outlineColor;\nuniform vec2 textureSize;\n\nconst float PI = 0.01745329252;\n\n\nint getIsStrokeWithAngel(float angel)\n{\n    int stroke = 0;\n    float rad = angel * PI;\n    vec2 unit = 1.0 / textureSize.xy;\n    vec2 offset = vec2(outlineSize * cos(rad) * unit.x, outlineSize * sin(rad) * unit.y);\n    float a = texture2D(u_texture, v_texCoords + offset).a;\n    if (a >= 0.5)\n    {\n        stroke = 1;\n    }\n    return stroke;\n}\n\nvoid main()\n{\n    vec4 myC = texture2D(u_texture, v_texCoords);\n    if (myC.a >= 0.5)\n    {\n        gl_FragColor = v_color * myC;\n        return;\n    }\n\n    int strokeCount = 0;\n    strokeCount += getIsStrokeWithAngel(0.0);\n    strokeCount += getIsStrokeWithAngel(30.0);\n    strokeCount += getIsStrokeWithAngel(60.0);\n    strokeCount += getIsStrokeWithAngel(90.0);\n    strokeCount += getIsStrokeWithAngel(120.0);\n    strokeCount += getIsStrokeWithAngel(150.0);\n    strokeCount += getIsStrokeWithAngel(180.0);\n    strokeCount += getIsStrokeWithAngel(210.0);\n    strokeCount += getIsStrokeWithAngel(240.0);\n    strokeCount += getIsStrokeWithAngel(270.0);\n    strokeCount += getIsStrokeWithAngel(300.0);\n    strokeCount += getIsStrokeWithAngel(330.0);\n\n    if (strokeCount > 0)\n    {\n        myC.rgb = outlineColor;\n        myC.a = 1.0;\n    }\n\n    gl_FragColor = v_color * myC;\n}";
        shaderOutline = new ShaderProgram(defaultVertex, testFragment);
        testFragment = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec2 v_texCoords;\nvarying vec4 v_color;\nuniform float widthStep;\nuniform float heightStep;\nuniform float strength;\nuniform sampler2D u_texture;\nconst float blurRadius = 5.0;\nconst float blurPixels = (blurRadius * 2.0 + 1.0) * (blurRadius * 2.0 + 1.0);\n\nvoid main()\n{\n    vec4 v = texture2D(u_texture, v_texCoords);\n    vec3 sumColor = vec3(0.0, 0.0, 0.0);\n    for(float fy = -blurRadius; fy <= blurRadius; ++fy)\n    {\n        for(float fx = -blurRadius; fx <= blurRadius; ++fx)\n        {\n            vec2 coord = vec2(fx * widthStep, fy * heightStep);\n            sumColor += texture2D(u_texture, v_texCoords + coord).rgb;\n        }\n    }\n    gl_FragColor = vec4(mix(v.rgb, sumColor / blurPixels, strength), v.a*v_color.a);\n}";
        shaderBlur = new ShaderProgram(defaultVertex, testFragment);
    }

    public void render() {
        Exception ex;
        try {
            this.update();
        } catch (Exception var9) {
            ex = var9;
            CFG.exceptionStack(ex);
        }

        this.clearScreen();
        this.renderer_SetToCurrentScale();

        try {
            this.oSB.setColor(Color.WHITE);
            Game.map.drawMap(this.oSB);
        } catch (Exception var8) {
            ex = var8;
            CFG.exceptionStack(ex);
        }

        RendererGame.rendererGame.drawCurrentScale_Provinces(this.oSB);
        this.renderer_resetScale2();
        RendererGame.rendererGame.drawWithoutScale_Provinces(this.oSB);
        this.renderer_SetToCurrentScale2();

        try {
            RendererGame.rendererGame.drawCurrentScale(this.oSB);
        } catch (Exception var7) {
            ex = var7;
            CFG.exceptionStack(ex);
        }

        try {
            this.oSB.end();
        } catch (Exception var6) {
            ex = var6;
            CFG.exceptionStack(ex);
        }

        this.renderer_resetScale();

        try {
            RendererGame.rendererGame.drawWithoutScale(this.oSB);
        } catch (Exception var5) {
            ex = var5;
            CFG.exceptionStack(ex);
        }

        try {
            this.renderUI();
        } catch (Exception var4) {
            ex = var4;
            CFG.exceptionStack(ex);
        }

        try {
            this.oSB.end();
        } catch (Exception var3) {
            ex = var3;
            CFG.exceptionStack(ex);
        }

        try {
            oSBBorder.end();
        } catch (Exception var2) {
            ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public void renderUI() {
        try {
            Game.menuManager.draw(this.oSB);
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

        while(numOfScissors > 0) {
            clipView_End(this.oSB);
        }

        this.drawKeyboardText(this.oSB);
        uFPS.drawFPS(this.oSB);
    }

    public final void drawKeyboardText(SpriteBatch oSB) {
        if (Keyboard.keyboardMode && (!CFG.isDesktop() || GameValues.info.DESKTOP_KEYBOARD_DRAW_EXTRA_TEXT)) {
            int textW = getTextWidth(Keyboard.keyboardMessage, CFG.FONT_REGULAR);
            drawBox(oSB, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.BUTTON_HEIGHT - CFG.PADDING * 2, textW + CFG.PADDING * 4, CFG.TEXT_HEIGHT + CFG.PADDING * 4, 1.0F);
            oSB.setColor(Color.WHITE);
            drawText(oSB, CFG.FONT_REGULAR, Keyboard.keyboardMessage, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, Colors.HOVER_GOLD);
        }

    }

    public static final void setShaderDefault(SpriteBatch oSB) {
        oSB.setShader(shaderDefault);
    }

    public static final void setBlackWhite(SpriteBatch oSB) {
        oSB.setShader(shaderBlackWhite);
    }

    public static final void setShaderWater(SpriteBatch oSB) {
        shaderTime += Gdx.graphics.getDeltaTime();
        oSB.setShader(shaderWater);
        shaderWater.bind();
        shaderWater.setUniformf("u_amount", 25.0F);
        shaderWater.setUniformf("u_speed", 0.15F);
        shaderWater.setUniformf("u_py", 0.15F);
        shaderWater.setUniformf("u_px", 0.55F);
        shaderWater.setUniformf("u_time", shaderTime);
    }

    public static final void setShaderWater2(SpriteBatch oSB) {
        shaderTime2 += Gdx.graphics.getDeltaTime();
        oSB.setShader(shaderWater2);
        shaderWater2.setUniformf("time", shaderTime2);
        shaderWater2.setUniformf("resolution", new Vector2((float)ImageManager.getImage(Images.waves).getWidth(), (float)ImageManager.getImage(Images.waves).getHeight()));
    }

    public static final void setShaderWater3(SpriteBatch oSB) {
        oSB.setShader(shaderWater3);
        shaderWater3.setUniformf("time", shaderTime2);
        shaderWater3.setUniformf("resolution", new Vector2((float)ImageManager.getImage(Images.flagBG).getWidth(), (float)ImageManager.getImage(Images.flagBG).getHeight()));
    }

    public static final void setShaderBlur(SpriteBatch oSB) {
        oSB.setShader(shaderBlur);
        shaderBlur.setUniformf("widthStep", 0.0F);
        shaderBlur.setUniformf("heightStep", 0.0F);
        shaderBlur.setUniformf("strength", 1.0F);
    }

    public static final void setShaderOutline(SpriteBatch oSB) {
        if (Game.menuManager.getInNewGame()) {
            oSB.setShader(shaderOutline);
            shaderOutline.setUniformf("outlineColor", 0.0F, 1.0F, 0.0F);
            shaderOutline.setUniformf("outlineSize", 2.0F);
            shaderOutline.setUniformf("textureSize", (float)Game.getProvince(0).getProvinceBG().getWidth(), (float)Game.getProvince(0).getProvinceBG().getHeight());
            Game.getProvince(0).getProvinceBG().draw(oSB, 150, 150);
            oSB.setShader(shaderDefault);
        }

    }

    public static final void drawLineShape(int iX, int iY, int iX2, int iY2) {
        shapeDrawer.line((float)iX, (float)(-iY), (float)iX2, (float)(-iY2));
    }

    private final void renderer_SetToCurrentScale() {
        try {
            camera.setToOrtho(false, (float)CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), (float)(-CFG.GAME_HEIGHT) / Game.mapScale.getCurrentScale());
            viewport.setWorldSize((float)CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), (float)CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
            viewport.apply();
            this.oSB.setProjectionMatrix(camera.combined);
            oSBBorder.setProjectionMatrix(camera.combined);
            this.oSB.begin();
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    private final void renderer_resetScale() {
        try {
            camera.setToOrtho(false, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
            viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
            viewport.apply();
            this.oSB.setProjectionMatrix(camera.combined);
            oSBBorder.setProjectionMatrix(camera.combined);
            this.oSB.begin();
            oSBBorder.begin();
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    private final void renderer_SetToCurrentScale2() {
        try {
            camera.setToOrtho(false, (float)CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), (float)(-CFG.GAME_HEIGHT) / Game.mapScale.getCurrentScale());
            viewport.setWorldSize((float)CFG.GAME_WIDTH / Game.mapScale.getCurrentScale(), (float)CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
            viewport.apply();
            this.oSB.setProjectionMatrix(camera.combined);
            oSBBorder.setProjectionMatrix(camera.combined);
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    private final void renderer_resetScale2() {
        try {
            camera.setToOrtho(false, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
            viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
            viewport.apply();
            this.oSB.setProjectionMatrix(camera.combined);
            oSBBorder.setProjectionMatrix(camera.combined);
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static final void clearUnclearedScissors(SpriteBatch oSB) {
        while(numOfScissors > 0) {
            clipView_End(oSB);
        }

    }

    public static final void clipViewPeek() {
        try {
            peekBounds = ScissorStack.peekScissors();
        } catch (Exception var1) {
            Exception ex = var1;
            CFG.exceptionStack(ex);
        }

    }

    public static final void clipViewPeek_Add(SpriteBatch oSB) {
        try {
            clearUnclearedScissors(oSB);
            if (peekBounds != null) {
                ScissorStack.pushScissors(peekBounds);
                ++numOfScissors;
            }
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static final boolean clipView_Start(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        try {
            Rectangle clipBounds = new Rectangle((float)nPosX, (float)nPosY, (float)nWidth, (float)nHeight);
            oSB.flush();
            ++numOfScissors;
            return ScissorStack.pushScissors(clipBounds);
        } catch (Exception var6) {
            Exception ex = var6;
            CFG.exceptionStack(ex);
            return false;
        }
    }

    public static final void clipView_End(SpriteBatch oSB) {
        try {
            numOfScissors = Math.max(numOfScissors - 1, 0);
            oSB.flush();
            ScissorStack.popScissors();
        } catch (Exception var2) {
        }

    }

    protected final void clearScreen() {
        this.updateBackgroundColor();
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(16640);
        this.oSB.setColor(Color.WHITE);
    }

    private final void updateBackgroundColor() {
        if (updateBackgroundColor) {
            updateBackgroundColor = false;
            if (Game.mapScale.getCurrentScale() == 1.0F) {
                BACKGROUND_COLOR = new Color(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[0], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[1], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[2], 1.0F);
            } else {
                float numOfSteps;
                float currentStep;
                if (Game.mapScale.getCurrentScale() > 1.0F) {
                    MapScale var10000 = Game.mapScale;
                    numOfSteps = MapScale.defScales.definedScales[1] - 1.0F;
                    currentStep = Game.mapScale.getCurrentScale() - 1.0F;
                    BACKGROUND_COLOR = new Color(CFG.getColorStep(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[0], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor_ZoomIn[0], currentStep, numOfSteps), CFG.getColorStep(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[1], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor_ZoomIn[1], currentStep, numOfSteps), CFG.getColorStep(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[2], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor_ZoomIn[2], currentStep, numOfSteps), 1.0F);
                } else {
                    MapScale var10001 = Game.mapScale;
                    numOfSteps = 1.0F - MapScale.defScales.definedScales[Game.mapScale.definedScalesLength - 1];
                    currentStep = 1.0F - Game.mapScale.getCurrentScale();
                    BACKGROUND_COLOR = new Color(CFG.getColorStep(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[0], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor_ZoomOut[0], currentStep, numOfSteps), CFG.getColorStep(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[1], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor_ZoomOut[1], currentStep, numOfSteps), CFG.getColorStep(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor[2], ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.BackgroundColor_ZoomOut[2], currentStep, numOfSteps), 1.0F);
                }
            }
        }

    }

    public void update() {
        CFG.currentTimeMillis = System.currentTimeMillis();
        Game.updateSimpleTask();
        Game.map.update();
        Game.menuManager.update();
        uFPS.countFPS();
        Game.ambienceManager.update();
        Game.animationManager.update();
        Keyboard.updateKeyboardVerticalLine();

        Exception ex;
        int i;
        try {
            for(i = Math.min(1, simpleTasksCivNames.size() - 1); i >= 0; --i) {
                ((Game.SimpleTask)simpleTasksCivNames.get(i)).update();
                simpleTasksCivNames.remove(i);
            }
        } catch (Exception var4) {
            ex = var4;
            CFG.exceptionStack(ex);
        }

        try {
            for(i = Math.min(50, simpleTasks_ArmyWidth.size() - 1); i >= 0; --i) {
                ((SimpleTaskArmyText)simpleTasks_ArmyWidth.get(i)).update();
                simpleTasks_ArmyWidth.remove(i);
            }
        } catch (Exception var3) {
            ex = var3;
            CFG.exceptionStack(ex);
        }

        try {
            SteamManager.updateSteam_runCallbacks();
        } catch (Exception var2) {
            ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static void addSimpleTaskCivsNames(Game.SimpleTask newTask) {
        if (!simpleTasksCivNames.contains(newTask)) {
            simpleTasksCivNames.add(newTask);
        }
    }

    public static void addSimpleTask_ArmyWidth(SimpleTaskArmyText newTask) {
        if (!simpleTasks_ArmyWidth.contains(newTask)) {
            simpleTasks_ArmyWidth.add(newTask);
        }
    }

    public final void drawLine(SpriteBatch oSB, int nPosX, int nPosY, int nPosX2, int nPosY2) {
        this.drawLine(oSB, nPosX, nPosY, (int)Math.ceil(Math.sqrt((double)((nPosX2 - nPosX) * (nPosX2 - nPosX) + (nPosY - nPosY2) * (nPosY - nPosY2)))), (float)(Math.atan2((double)(nPosY - nPosY2), (double)(-nPosX + nPosX2)) * 180.0 / Math.PI));
    }

    public final void drawLine(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, float fAngle) {
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, Images.pix.getHeight(), fAngle);
    }

    public static final void drawRect(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, 1);
        Images.pix.draw(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        Images.pix.draw(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw(oSB, nPosX + nWidth, nPosY, 1, nHeight);
    }

    public static final void drawLine(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
        oSB.setColor(new Color(Colors.HOVER_LINE1.r, Colors.HOVER_LINE1.g, Colors.HOVER_LINE1.b, 1.0F));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, 1);
        oSB.setColor(new Color(Colors.HOVER_LINE2.r, Colors.HOVER_LINE2.g, Colors.HOVER_LINE2.b, 1.0F));
        Images.pix.draw(oSB, nPosX, nPosY + 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawMenusBox(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipX) {
        ImageManager.getImage(Images.insideTop).draw2(oSB, nPosX, nPosY, nWidth, nHeight - ImageManager.getImage(Images.insideBot).getHeight(), flipX, false);
        ImageManager.getImage(Images.insideBot).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(Images.insideBot).getHeight(), nWidth, ImageManager.getImage(Images.insideBot).getHeight(), flipX, false);
    }

    public static final void drawMenusBox(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipX, int imageIDTop, int imageIDBot) {
        ImageManager.getImage(imageIDTop).draw2(oSB, nPosX, nPosY, nWidth, nHeight - ImageManager.getImage(imageIDBot).getHeight(), flipX, false);
        ImageManager.getImage(imageIDBot).draw2(oSB, nPosX, nPosY + nHeight - ImageManager.getImage(imageIDBot).getHeight(), nWidth, ImageManager.getImage(imageIDBot).getHeight(), flipX, false);
    }

    public static final void drawMenusBoxTopOnly(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipX, int imageIDTop) {
        ImageManager.getImage(imageIDTop).draw2(oSB, nPosX, nPosY, nWidth, nHeight, flipX, false);
    }

    public static final void drawBoxCorner(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.85F));
        ImageManager.getImage(Images.boxBGLinePix).draw(oSB, nPosX, nPosY, nWidth, nHeight);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - boxBGExtraY, nPosY - boxBGExtraY);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY - boxBGExtraY, true, false);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX - boxBGExtraY, nPosY, boxBGExtraY, nHeight);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX + nWidth, nPosY, boxBGExtraY, nHeight, true, false);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY - boxBGExtraY, nWidth, boxBGExtraY);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, boxBGExtraY, false, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxCornerAlpha(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        ImageManager.getImage(Images.boxBGLinePix).draw(oSB, nPosX, nPosY, nWidth, nHeight);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - boxBGExtraY, nPosY - boxBGExtraY);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY - boxBGExtraY, true, false);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX - boxBGExtraY, nPosY, boxBGExtraY, nHeight);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX + nWidth, nPosY, boxBGExtraY, nHeight, true, false);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY - boxBGExtraY, nWidth, boxBGExtraY);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, boxBGExtraY, false, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxCornerEmpty(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - boxBGExtraY, nPosY - boxBGExtraY);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY - boxBGExtraY, true, false);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX - boxBGExtraY, nPosY, boxBGExtraY, nHeight);
        ImageManager.getImage(Images.boxBGLine).draw2(oSB, nPosX + nWidth, nPosY, boxBGExtraY, nHeight, true, false);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY - boxBGExtraY, nWidth, boxBGExtraY);
        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, boxBGExtraY, false, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxCorner2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX - boxBGExtraY, nPosY + nHeight, false, true);
        ImageManager.getImage(Images.boxBGCorner).draw(oSB, nPosX + nWidth, nPosY + nHeight, true, true);
        if (BOX_CORNER_TIME <= CFG.currentTimeMillis - BOX_CORNER_TIMER) {
            BOX_CORNER_TIME = CFG.currentTimeMillis;
            ++iBoxCornerX;
        }

        ImageManager.getImage(Images.boxBGLineH).draw2(oSB, nPosX, nPosY + nHeight, nWidth, boxBGExtraY, -iBoxCornerX, 0, false, true);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.75F));
        Images.gradientXY.draw(oSB, nPosX - CFG.PADDING, nPosY, nWidth + CFG.PADDING * 2, nHeight);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 1.0F));
        Images.gradientXY.draw(oSB, nPosX - CFG.PADDING, nPosY + (int)Math.ceil((double)((float)nHeight / 2.0F)), nWidth + CFG.PADDING * 2, nHeight / 2);
        Images.gradientXY.draw(oSB, nPosX - CFG.PADDING, nPosY + (int)Math.ceil((double)((float)nHeight / 2.0F)), nWidth + CFG.PADDING * 2, nHeight / 2);
    }

    public static final void drawBox(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        drawBox(oSB, Images.boxEdge, nPosX, nPosY, nWidth, nHeight, fAlpha);
    }

    public static final void drawBox(SpriteBatch oSB, int imageID, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        int iHCeil = (nHeight + 1) / 2;
        int iHFloor = nHeight / 2;
        Image img = ImageManager.getImage(imageID);
        nWidth = Math.max(nWidth, img.getWidth() * 2);
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), iHCeil);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), iHCeil, true);
        img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth - img.getWidth(), iHFloor, false, true);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY + iHCeil, img.getWidth(), iHFloor, true, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBox(SpriteBatch oSB, Image img, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        int iHCeil = (nHeight + 1) / 2;
        int iHFloor = nHeight / 2;
        nWidth = Math.max(nWidth, img.getWidth() * 2);
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), iHCeil);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), iHCeil, true);
        img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth - img.getWidth(), iHFloor, false, true);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY + iHCeil, img.getWidth(), iHFloor, true, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxProgress(SpriteBatch oSB, Image img, int nPosX, int nPosY, int nWidth, int nHeight, int maxWidth) {
        int iHCeil = (nHeight + 1) / 2;
        int iHFloor = nHeight / 2;
        if (img.getWidth() * 2 > nWidth) {
            img.draw2(oSB, nPosX, nPosY, nWidth, iHCeil);
            img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth, iHFloor, false, true);
        } else {
            img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), iHCeil);
            img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), iHCeil, true);
            img.draw2(oSB, nPosX, nPosY + iHCeil, nWidth - img.getWidth(), iHFloor, false, true);
            img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY + iHCeil, img.getWidth(), iHFloor, true, true);
        }

        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxTOP(SpriteBatch oSB, int imageID, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxTOP(SpriteBatch oSB, Image img, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), nHeight);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), nHeight, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxBOT(SpriteBatch oSB, int imageID, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight, false, true);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxBOT(SpriteBatch oSB, Image img, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        img.draw2(oSB, nPosX, nPosY, nWidth - img.getWidth(), nHeight, false, true);
        img.draw2(oSB, nPosX + nWidth - img.getWidth(), nPosY, img.getWidth(), nHeight, true, true);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBlueBox(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_INFO.r, Colors.COLOR_GRADIENT_BG_INFO.g, Colors.COLOR_GRADIENT_BG_INFO.b, 0.9F));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_INFO.r, Colors.COLOR_GRADIENT_OVER_INFO.g, Colors.COLOR_GRADIENT_OVER_INFO.b, 0.5F));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_INFO.r, Colors.COLOR_GRADIENT_OVER_INFO.g, Colors.COLOR_GRADIENT_OVER_INFO.b, 0.25F));
        Images.gradientXY.draw(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_INFO.r, Colors.COLOR_GRADIENT_OVER_INFO.g, Colors.COLOR_GRADIENT_OVER_INFO.b, 1.0F));
        Images.gradientFull.draw(oSB, nPosX, nPosY + 1, nWidth, 1);
        Images.gradientFull.draw(oSB, nPosX, nPosY + nHeight - 2, nWidth, 1);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 1.0F));
        Images.gradientFull.draw(oSB, nPosX, nPosY, nWidth, 1);
        Images.gradientFull.draw(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBox2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        Images.pix.draw2(oSB, nPosX, nPosY, nWidth, 1);
        Images.pix.draw2(oSB, nPosX + nWidth - 1, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxLineFrame(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, Color color) {
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.75F));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY, nWidth, CFG.PADDING + CFG.PADDING / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + nHeight - CFG.PADDING - CFG.PADDING / 2, nWidth, CFG.PADDING + CFG.PADDING / 2, false, true);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
        Images.gradientXY.draw(oSB, nPosX, nPosY, nWidth, CFG.PADDING + CFG.PADDING / 2, false, true);
        Images.gradientXY.draw(oSB, nPosX, nPosY + nHeight - CFG.PADDING - CFG.PADDING / 2, nWidth, CFG.PADDING + CFG.PADDING / 2);
        oSB.setColor(color);
        Images.pix.draw2(oSB, nPosX, nPosY, nWidth, 1);
        Images.pix.draw2(oSB, nPosX + nWidth - 1, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + 1, 1, nHeight - 2);
        Images.pix.draw2(oSB, nPosX, nPosY + nHeight - 1, nWidth, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBoxHover(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        drawBoxHover(oSB, Images.boxEdge, nPosX, nPosY, nWidth, nHeight, fAlpha);
    }

    public static final void drawBoxHover(SpriteBatch oSB, int imageID, int nPosX, int nPosY, int nWidth, int nHeight, float fAlpha) {
        int iHCeil = (int)Math.ceil((double)((float)nHeight / 2.0F));
        int iHFloor = (int)Math.floor((double)((float)nHeight / 2.0F));
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), iHCeil);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), iHCeil, true);
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY + iHCeil, nWidth - ImageManager.getImage(imageID).getWidth(), iHFloor, false, true);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY + iHCeil, ImageManager.getImage(imageID).getWidth(), iHFloor, true, true);
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.05F * fAlpha));
        ImageManager.getImage(Images.noise).draw2(oSB, nPosX, nPosY, nWidth, nHeight);
        oSB.setColor(new Color(Colors.HOVER_LINE2.r, Colors.HOVER_LINE2.g, Colors.HOVER_LINE2.b, Colors.HOVER_LINE2.a * fAlpha));
        Images.gradientFull.draw(oSB, nPosX + 1, nPosY + 1, nWidth - 2, 1);
        Images.gradientFull.draw(oSB, nPosX + 1, nPosY + nHeight - 2, nWidth - 2, 1);
        oSB.setColor(Color.WHITE);
    }

    public static final void drawBox_EDGE_TOP_LR(SpriteBatch oSB, int imageID, int nPosX, int nPosY, int nWidth, int nHeight) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true);
    }

    public static final void drawBox_EDGE_TOP_LR(SpriteBatch oSB, int imageID, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipY) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth - ImageManager.getImage(imageID).getWidth(), nHeight, false, flipY);
        ImageManager.getImage(imageID).draw2(oSB, nPosX + nWidth - ImageManager.getImage(imageID).getWidth(), nPosY, ImageManager.getImage(imageID).getWidth(), nHeight, true, flipY);
    }

    public static final void drawBox_EDGE_LorR(SpriteBatch oSB, int imageID, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipX, boolean flipY) {
        ImageManager.getImage(imageID).draw2(oSB, nPosX, nPosY, nWidth, nHeight, flipX, flipY);
    }

    public static final int getDarker(int iColor, int iMod) {
        return Math.round((float)Math.max(0, iColor - iMod));
    }

    public static final Color getDarker(Color nColor, int iMod, float nAlpha) {
        return new Color((float)Math.round(Math.max(0.0F, nColor.r * 255.0F - (float)iMod) / 255.0F), (float)Math.round(Math.max(0.0F, nColor.g * 255.0F - (float)iMod) / 255.0F), (float)Math.round(Math.max(0.0F, nColor.b * 255.0F - (float)iMod) / 255.0F), nAlpha);
    }

    public static final float getColorStep(int iOld, int iNew, int iColorStepID, int numOfSteps) {
        return ((float)iOld + (iOld > iNew ? (float)((iNew - iOld) * iColorStepID) / (float)numOfSteps : (float)((iNew - iOld) * iColorStepID) / (float)numOfSteps)) / 255.0F;
    }

    public static final Color getColorStep(Color iOld, Color iNew, int iColorStepID, int numOfSteps, float fAlpha) {
        return new Color(iOld.r + (iOld.r > iNew.r ? (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps : (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps), iOld.g + (iOld.g > iNew.g ? (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps : (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps), iOld.b + (iOld.b > iNew.b ? (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps : (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps), fAlpha);
    }

    public static final Color getColorStep_WithAlpha(Color iOld, Color iNew, int iColorStepID, int numOfSteps) {
        return new Color(iOld.r + (iOld.r > iNew.r ? (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps : (iNew.r - iOld.r) * (float)iColorStepID / (float)numOfSteps), iOld.g + (iOld.g > iNew.g ? (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps : (iNew.g - iOld.g) * (float)iColorStepID / (float)numOfSteps), iOld.b + (iOld.b > iNew.b ? (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps : (iNew.b - iOld.b) * (float)iColorStepID / (float)numOfSteps), iOld.a + (iOld.a > iNew.a ? (iNew.a - iOld.a) * (float)iColorStepID / (float)numOfSteps : (iNew.a - iOld.a) * (float)iColorStepID / (float)numOfSteps));
    }

    public static final Color getColorMixed(Color iOld, Color iNew) {
        float tA = 1.0F - (1.0F - iOld.a) * (1.0F - iNew.a);
        return new Color(iNew.r * iNew.a / tA + iOld.r * iOld.a * (1.0F - iNew.a) / tA, iNew.g * iNew.a / tA + iOld.g * iOld.a * (1.0F - iNew.a) / tA, iNew.b * iNew.a / tA + iOld.b * iOld.a * (1.0F - iNew.a) / tA, iOld.a);
    }

    public static final void clearFonts() {
        for(int i = 0; i < fontMainSize; ++i) {
            ((BitmapFont)fontMain.get(i)).dispose();
            fontMain.set(i, null);
        }

        fontMain.clear();
        fontMainSize = 0;
    }

    public static final void loadFont(String sFont, String charset, int fontSize) {
        FreeTypeFontGenerator generator = null;
        if (fontSize < 0) {
            fontSize = (int)((float)GameValues.value.DEFAULT_FONT_SIZE * CFG.GUI_SCALE);
        }

        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        } catch (Exception var5) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = charset;
        params.size = Math.max(fontSize, 6);
        params.color = Color.WHITE;
        params.minFilter = TextureFilter.Linear;
        params.magFilter = TextureFilter.Linear;
        fontMain.add(generator.generateFont(params));
        fontMainSize = fontMain.size();
        generator.dispose();
    }

    public static final void loadFontArmy_GlyphLayout(String sFont, String charset, int fontSize) {
        FreeTypeFontGenerator generator = null;
        if (fontSize < 0) {
            fontSize = (int)((float)GameValues.value.DEFAULT_FONT_SIZE * CFG.GUI_SCALE);
        }

        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        } catch (Exception var5) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = charset;
        params.size = Math.max(fontSize, 6);
        params.color = Color.WHITE;
        params.minFilter = TextureFilter.Linear;
        params.magFilter = TextureFilter.Linear;
        fontArmy_GlyphLayout = generator.generateFont(params);
        generator.dispose();
    }

    public static final synchronized void loadFont_UpdateTextHeight() {
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText((BitmapFont)fontMain.get(0), "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890");
        CFG.TEXT_HEIGHT = (int)glyphLayout.height;
        ProvinceDrawArmy.updateArmyHeight();
    }

    public static final synchronized void loadFont_UpdateTextHeightSmall() {
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText((BitmapFont)fontMain.get(CFG.FONT_BOLD_SMALL), "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890");
        CFG.TEXT_HEIGHT_SMALL = (int)glyphLayout.height;
    }

    public static final void loadFontBorder(String sFont, String charset) {
        FreeTypeFontGenerator generator = null;

        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        } catch (Exception var4) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.characters = charset;
        params.size = Game.settingsManager.FONT_BORDER_SIZE;
        params.color = new Color(Game.settingsManager.civNamesFontColor_R, Game.settingsManager.civNamesFontColor_G, Game.settingsManager.civNamesFontColor_B, Game.settingsManager.civNamesFontColor_A);
        params.minFilter = TextureFilter.Linear;
        params.magFilter = TextureFilter.Linear;
        params.kerning = false;
        params.borderColor = new Color(Game.settingsManager.civNamesFontColorBorder_R, Game.settingsManager.civNamesFontColorBorder_G, Game.settingsManager.civNamesFontColorBorder_B, Game.settingsManager.civNamesFontColorBorder_A);
        params.borderWidth = (float)Game.settingsManager.FONT_BORDER_WIDTH_OF_BORDER;
        fontBorder.add(generator.generateFont(params));
        fontBorderSize = fontBorder.size();
        ((BitmapFont)fontBorder.get(0)).setFixedWidthGlyphs(charset);
        generator.dispose();
    }

    public static final void loadFontBorder2(String sFont, String charset) {
    }

    public static final void drawText_CacheBegin(int fontID, Color color) {
        ((BitmapFont)fontMain.get(fontID)).getCache().clear();
        ((BitmapFont)fontMain.get(fontID)).setColor(color);
    }

    public static final void drawText_Cache(int fontID, String sText, int nPosX, int nPosY) {
        try {
            if (sText != null) {
                ((BitmapFont)fontMain.get(fontID)).getCache().addText(sText, (float)nPosX, (float)(-nPosY));
            }
        } catch (Exception var5) {
        }

    }

    public static final void drawText_CacheEnd(SpriteBatch oSB, int fontID) {
        ((BitmapFont)fontMain.get(fontID)).getCache().draw(oSB);
        ((BitmapFont)fontMain.get(fontID)).getCache().clear();
    }

    public static final void drawText(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
        drawText(oSB, 0, sText, nPosX, nPosY, color);
    }

    public static final void drawText(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color) {
        try {
            if (sText != null) {
                ((BitmapFont)fontMain.get(fontID)).setColor(color);
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, (float)nPosX, (float)(-nPosY));
            }
        } catch (Exception var7) {
        }

    }

    public static final void drawText(SpriteBatch oSB, String sText, float fontScale, int nPosX, int nPosY, Color color) {
        drawText(oSB, 0, sText, fontScale, nPosX, nPosY, color);
    }

    public static final void drawText(SpriteBatch oSB, int fontID, String sText, float fontScale, int nPosX, int nPosY, Color color) {
        try {
            if (sText != null) {
                setFontScale(fontScale);
                ((BitmapFont)fontMain.get(fontID)).setColor(color);
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, (float)nPosX, (float)(-nPosY));
                resetFontScale();
            }
        } catch (Exception var8) {
        }

    }

    public static final void drawTextWithShadow(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
        drawTextWithShadow(oSB, 0, sText, nPosX, nPosY, color);
    }

    public static final void drawTextWithShadow(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color) {
        try {
            if (sText != null) {
                ((BitmapFont)fontMain.get(fontID)).setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
                ((BitmapFont)fontMain.get(fontID)).setColor(color);
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, (float)nPosX, (float)(-nPosY));
            }
        } catch (Exception var7) {
        }

    }

    public static final void drawTextWithShadowScale(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color, float fScale) {
        try {
            if (sText != null) {
                ((BitmapFont)fontMain.get(fontID)).getData().setScale(fScale);
                ((BitmapFont)fontMain.get(fontID)).setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
                ((BitmapFont)fontMain.get(fontID)).setColor(color);
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, (float)nPosX, (float)(-nPosY));
                ((BitmapFont)fontMain.get(fontID)).getData().setScale(1.0F);
            }
        } catch (Exception var8) {
        }

    }

    public static final void drawTextWithShadowRotated(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color, float rotate) {
        drawTextWithShadowRotated(oSB, 0, sText, nPosX, nPosY, color, rotate);
    }

    public static final void drawTextWithShadowRotated(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color, float rotate) {
        if (sText != null) {
            Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();

            try {
                Matrix4 mx4Font = new Matrix4();
                mx4Font.rotate(textRotatedVector3, rotate);
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                oSB.setTransformMatrix(mx4Font);
                ((BitmapFont)fontMain.get(fontID)).setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, -1.0F, -1.0F);
                ((BitmapFont)fontMain.get(fontID)).setColor(color);
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, 0.0F, 0.0F);
            } catch (Exception var12) {
            } finally {
                oSB.setTransformMatrix(oldTransformMatrix);
            }
        }

    }

    public static final void drawTextRotated(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color, float rotate) {
        drawTextRotated(oSB, 0, sText, nPosX, nPosY, color, rotate);
    }

    public static final void drawTextRotated(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color, float rotate) {
        if (sText != null) {
            Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();

            try {
                Matrix4 mx4Font = new Matrix4();
                mx4Font.rotate(new Vector3(0.0F, 0.0F, 1.0F), rotate);
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                oSB.setTransformMatrix(mx4Font);
                ((BitmapFont)fontMain.get(fontID)).setColor(color);
                ((BitmapFont)fontMain.get(fontID)).draw(oSB, sText, 0.0F, 0.0F);
            } catch (Exception var12) {
            } finally {
                oSB.setTransformMatrix(oldTransformMatrix);
            }
        }

    }

    public static final synchronized void drawTextRotatedBorder(SpriteBatch oSB, int nFontID, String sText, int nPosX, int nPosY, Color color, float rotate) {
        if (sText != null) {
            Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();

            try {
                Matrix4 mx4Font = new Matrix4();
                mx4Font.rotate(textRotatedVector3, rotate);
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                oSB.setTransformMatrix(mx4Font);
                ((BitmapFont)fontBorder.get(nFontID)).setColor(color);
                ((BitmapFont)fontBorder.get(nFontID)).draw(oSB, sText, 0.0F, 0.0F);
            } catch (Exception var12) {
            } finally {
                oSB.setTransformMatrix(oldTransformMatrix);
            }
        }

    }

    public static final synchronized void drawTextRotatedBorder_2(SpriteBatch oSB, int nFontID, String sText, int nPosX, int nPosY, Color color, float rotate) {
        try {
            if (sText != null) {
                try {
                    Matrix4 mx4Font = new Matrix4();
                    mx4Font.rotate(textRotatedVector3, rotate);
                    mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                    oSB.setTransformMatrix(mx4Font);
                    ((BitmapFont)fontBorder.get(nFontID)).setColor(color);
                    ((BitmapFont)fontBorder.get(nFontID)).draw(oSB, sText, 0.0F, 0.0F);
                } catch (Exception var8) {
                }
            }
        } catch (Exception var9) {
        }

    }

    public static final synchronized void drawTextRotatedBorder_2(SpriteBatch oSB, int nFontID, String sText, int nPosX, int nPosY, float rotate) {
        try {
            if (sText != null) {
                try {
                    Matrix4 mx4Font = new Matrix4();
                    mx4Font.rotate(textRotatedVector3, rotate);
                    mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                    oSB.setTransformMatrix(mx4Font);
                    ((BitmapFont)fontBorder.get(nFontID)).draw(oSB, sText, 0.0F, 0.0F);
                } catch (Exception var7) {
                }
            }
        } catch (Exception var8) {
        }

    }

    public static final synchronized void drawTextRotatedBorder(SpriteBatch oSB, int nFontID, String sText, int nPosX, int nPosY, Matrix4 mx4Font) {
        try {
            if (sText != null) {
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                oSB.setTransformMatrix(mx4Font);
                ((BitmapFont)fontBorder.get(nFontID)).draw(oSB, sText, 0.0F, 0.0F);
            }
        } catch (Exception var7) {
        }

    }

    public static final synchronized void drawTextBorder(SpriteBatch oSB, int nFontID, String sText, int nPosX, int nPosY) {
        Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();

        try {
            if (sText != null) {
                Matrix4 mx4Font = new Matrix4();
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                oSB.setTransformMatrix(mx4Font);
                ((BitmapFont)fontBorder.get(nFontID)).draw(oSB, sText, 0.0F, 0.0F);
            }
        } catch (Exception var10) {
        } finally {
            oSB.setTransformMatrix(oldTransformMatrix);
        }

    }

    public static final int getTextWidth(String sText) {
        return getTextWidth(sText, 0);
    }

    public static final synchronized int getTextWidth(String sText, int fontID) {
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText((BitmapFont)fontMain.get(fontID), sText);
        return (int)glyphLayout.width;
    }

    public static final int getTextHeight(String sText) {
        return getTextHeight(sText, 0);
    }

    public static final synchronized int getTextHeight(String sText, int fontID) {
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText((BitmapFont)fontMain.get(fontID), sText);
        return (int)glyphLayout.height;
    }

    public static final XY getText_WidthHeight(String sText) {
        return getText_WidthHeight(sText, 0);
    }

    public static final synchronized XY getText_WidthHeight(String sText, int fontID) {
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText((BitmapFont)fontMain.get(fontID), sText);
        return new XY((int)glyphLayout.width, (int)glyphLayout.height);
    }

    public static final XY getText_WidthHeight(String sText, float fFontScale) {
        return getText_WidthHeight(sText, 0, fFontScale);
    }

    public static final synchronized XY getText_WidthHeight(String sText, int fontID, float fFontScale) {
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText((BitmapFont)fontMain.get(fontID), sText);
        return new XY((int)(glyphLayout.width * fFontScale), (int)(glyphLayout.height * fFontScale));
    }

    public static final void setFontScale(float fontScale) {
        for(int i = 0; i < fontMainSize; ++i) {
            ((BitmapFont)fontMain.get(i)).getData().setScale(fontScale);
        }

    }

    public static final void resetFontScale() {
        for(int i = 0; i < fontMainSize; ++i) {
            ((BitmapFont)fontMain.get(i)).getData().setScale(1.0F);
        }

    }

    public final void resetFontMainScale() {
        this.setFontMainScale(1.0F);
    }

    public final void setFontMainScale(float fontScale) {
        this.setFontMainScale(fontScale, 0);
    }

    public final void setFontMainScale(float fontScale, int fontID) {
        ((BitmapFont)fontMain.get(fontID)).getData().setScale(fontScale);
    }

    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    public void dispose() {
        this.oSB.dispose();
    }

    public static final void drawLoading(SpriteBatch oSB, int iTranslateX, int iTranslateY, float nProgress) {
        int nHeight = ImageManager.getImage(Images.logo).getHeight() + CFG.BUTTON_HEIGHT * 2;
        if (CFG.currentTimeMillis - 4000L > loadingTime) {
            try {
                sLoadingText = Game.lang.getLoading("L" + Game.oR.nextInt(Game.lang.iLoading_NumOfTexts)) + "..";
                loadingTime = CFG.currentTimeMillis;
                GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                glyphLayout.setText((BitmapFont)fontMain.get(CFG.FONT_REGULAR), sLoadingText);
                iLoadingTextWidth = (int)glyphLayout.width;
            } catch (Exception var9) {
                Exception ex = var9;
                CFG.exceptionStack(ex);
            }
        }

        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11, CFG.GAME_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
        Images.gradientXY.draw(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - (iLoadingTextWidth + CFG.PADDING * 6) / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11, iLoadingTextWidth + CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11 + 1, CFG.GAME_WIDTH, 1);
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11 + CFG.TEXT_HEIGHT + CFG.PADDING * 6 - 2, CFG.GAME_WIDTH, 1);
        drawText(oSB, CFG.FONT_REGULAR, sLoadingText, iTranslateX + CFG.GAME_WIDTH / 2 - iLoadingTextWidth / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 8, new Color(Colors.COLOR_LOGO.r, Colors.COLOR_LOGO.g, Colors.COLOR_LOGO.b, 0.75F));
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
        ImageManager.getImage(Images.logo).draw(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.logo).getWidth() / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 14 - ImageManager.getImage(Images.logo).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.logo).draw2(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.logo).getWidth() / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 14 - ImageManager.getImage(Images.logo).getHeight(), Math.min(ImageManager.getImage(Images.logo).getWidth(), (int)((float)ImageManager.getImage(Images.logo).getWidth() * nProgress)), ImageManager.getImage(Images.logo).getHeight());
    }

    public static final void drawEditorMenuBG(SpriteBatch oSB, int iX, int iY, int iWidth, int iHeight, int iTranslateX, int iTranslateY) {
        drawBox_EDGE_TOP_LR(oSB, Images.mainBox, iX + iTranslateX, iY + iTranslateY, iWidth, iHeight, true);
    }

    static {
        boxBGExtraY = CFG.PADDING;
        iBoxCornerX = 0;
        BOX_CORNER_TIME = 0L;
        BOX_CORNER_TIMER = 56L;
        glyphLayout = new GlyphLayout_Game();
        charset = "";
        fontMain = new ArrayList();
        fontMainSize = 0;
        fontBorder = new ArrayList();
        fontBorderSize = 0;
        textRotatedVector3 = new Vector3(0.0F, 0.0F, 1.0F);
        sLoadingText = "";
        iLoadingTextWidth = 0;
        loadingTime = 0L;
    }

    public static class SimpleTaskArmyText {
        public String taskKey;
        public ArmyDivision armyDivision;
        public boolean updateShift;

        public SimpleTaskArmyText(String taskKey, ArmyDivision armyDivision, boolean updateShift) {
            this.taskKey = taskKey;
            this.armyDivision = armyDivision;
            this.updateShift = updateShift;
        }

        public void update() {
            this.armyDivision.updateArmyWidth_Just(this.updateShift);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (!(o instanceof SimpleTaskArmyText)) {
                return false;
            } else {
                SimpleTaskArmyText that = (SimpleTaskArmyText)o;
                return Objects.equals(this.taskKey, that.taskKey);
            }
        }

        public int hashCode() {
            return 0;
        }
    }
}
