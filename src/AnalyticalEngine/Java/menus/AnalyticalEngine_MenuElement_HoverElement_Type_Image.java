package AnalyticalEngine.Java.menus;

import AnalyticalEngine.AnalyticalEngine;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnalyticalEngine_MenuElement_HoverElement_Type_Image implements MenuElement_HoverElement_Type {
    public int height = 0;
    public String image_file_path = "";
    public int offset_left = 0;
    public int offset_right = 0;
    public int width = 0;

    public Image loaded_img;

    public AnalyticalEngine_MenuElement_HoverElement_Type_Image (String arg0_image_file_path) {
        //Convert from parameters
        String image_file_path = arg0_image_file_path;

        //Call this.init()
        this.init(image_file_path, 0, CFG.PADDING);
    }

    public AnalyticalEngine_MenuElement_HoverElement_Type_Image (String arg0_image_file_path, int arg1_offset_left) {
        //Convert from parameters
        String image_file_path = arg0_image_file_path;
        int offset_left = arg1_offset_left;

        //Call this.init()
        this.init(image_file_path, offset_left, CFG.PADDING);
    }

    public AnalyticalEngine_MenuElement_HoverElement_Type_Image (String arg0_image_file_path, int arg1_offset_left, int arg2_offset_right) {
        //Convert from parameters
        String image_file_path = arg0_image_file_path;
        int offset_left = arg1_offset_left;
        int offset_right = arg2_offset_right;

        //Call this.init()
        this.init(image_file_path, offset_left, offset_right);
    }

    public void draw (SpriteBatch arg0_oSB, int arg1_pos_x, int arg2_pos_y, float arg3_alpha, int arg4_max_width) {
        //Convert from parameters
        SpriteBatch oSB = arg0_oSB;
        int pos_x = arg1_pos_x;
        int pos_y = arg2_pos_y;
        float alpha = arg3_alpha;
        int max_width = arg4_max_width;

        //System.out.println("Drawing image with pos_x: " + pos_x + " pos_y: " + pos_y + " alpha: " + alpha + " max_width: " + max_width);
        //System.out.println("Offset left: " + offset_left + " height: " + height + " width: " + width);

        //Set colour, draw
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, alpha));
        this.loaded_img.draw(oSB, pos_x + this.offset_left, pos_y + CFG.PADDING + CFG.PADDING/2 + CFG.TEXT_HEIGHT/2 - this.height/2, this.width, this.height);
    }

    public int getHeight () {
        //Return statement
        return CFG.TEXT_HEIGHT + CFG.PADDING;
    }

    public final float getImageScale () { //[WIP] - Finish function body
        //Return statement
        return (float) CFG.TEXT_HEIGHT*0.9F/(float) this.loaded_img.getHeight()*1.2F;
    }

    public int getWidth () {
        //Return statement
        return this.offset_left + this.offset_right + this.width;
    }

    public void loadDefaultImage () {
        //Load default image
        this.loaded_img = new Image(
            ImageManager.loadTexture("ui/interface/" + CFG.getRescouresPath_Short() + "world.png"),
            Texture.TextureFilter.Linear,
            Texture.TextureWrap.ClampToEdge
        );
    }

    public void loadImage () { //[WIP] - Finish function body
        //1. Make sure this.loaded_img is currently disposed of
        if (this.loaded_img != null) {
            this.loaded_img.dispose();
            this.loaded_img = null;
        }

        //2. Attempt to load the specified image with a default fallback
        try {
            if (FileManager.loadFile(this.image_file_path).exists()) {
                this.loaded_img = new Image(
                    ImageManager.loadTexture(this.image_file_path),
                    Texture.TextureFilter.Linear,
                    Texture.TextureWrap.ClampToEdge
                );
            } else {
                loadDefaultImage();
            }
        } catch (Exception ex) {
            loadDefaultImage();
            CFG.exceptionStack(ex);
        }
    }

    public final void init (String arg0_image_file_path, int arg1_offset_left, int arg2_offset_right) {
        //Convert from parameters
        String image_file_path = arg0_image_file_path;
        int offset_left = arg1_offset_left;
        int offset_right = arg2_offset_right;

        //Set this fields
        this.image_file_path = image_file_path;
        this.offset_left = offset_left;
        this.offset_right = offset_right;

        //Call loadImage(); populate this.height, this.width
        loadImage();
        this.height = (int) (this.loaded_img.getHeight()*this.getImageScale());
        this.width = (int) (this.loaded_img.getWidth()*this.getImageScale());
    }
}
