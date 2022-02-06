package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CellImage {
    BufferedImage child, childPlaying, present, santa, childSleeping;
    static HashMap<CellImageEnum, BufferedImage> images;

    public CellImage() {
        images = new HashMap<>();
        try {
            images.put(CellImageEnum.CHILD, ImageIO.read(new File("./src/view/images/child.png")));
            images.put(CellImageEnum.CHILDPLAYING, ImageIO.read(new File("./src/view/images/playing.png")));
            images.put(CellImageEnum.PRESENT, ImageIO.read(new File("./src/view/images/present.png")));
            images.put(CellImageEnum.SANTA, ImageIO.read(new File("./src/view/images/santa.png")));
            images.put(CellImageEnum.CHILDSLEEPING, ImageIO.read(new File("./src/view/images/sleep.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getImage(CellImageEnum imageEnum) {
        return images.get(imageEnum);
    }


}
