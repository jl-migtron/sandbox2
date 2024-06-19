package com.migtron.sandbox2;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jl@mitron.ai
 */
public class ImagePool {

    private static Logger logger = LogManager.getLogger(ImagePool.class);
    public static String IMAGE_PATH1 = "C:/Users/bea/TEST/images/sunflowers.jpg";
    public static String IMAGE_PATH2 = "C:/Users/bea/TEST/images/honda.jpg";
    public static String IMAGE_PATH3 = "C:/Users/bea/TEST/images/honda2.jpg";
    public static String IMAGE_PATH4 = "C:/Users/bea/TEST/images/honda3.jpg";
    public static String IMAGE_PATH5 = "C:/Users/bea/TEST/images/ager.jpg";
    public static String IMAGE_PATH6 = "C:/Users/bea/TEST/images/formentera.jpg";
    public static String VIDEO_PATH1 = "C:/Users/bea/TEST/videos/video9-lions.mp4";
    public static String VIDEO_PATH2 = "C:/Users/bea/TEST/videos/video8-cars.mp4";
    private List<BufferedImage> images; // images list
    private int cursor;    // index inside images list

    public ImagePool() {
        try {
            // load all images
            images = new ArrayList<>();
            List<String> paths = new ArrayList<>();
            paths.addAll(Arrays.asList(IMAGE_PATH1, IMAGE_PATH2,
                IMAGE_PATH3, IMAGE_PATH4,
                IMAGE_PATH5, IMAGE_PATH6));
            //paths.forEach(path -> images.add(UtilImageIO.loadImage(path)));
            cursor = 0;
        } catch (Exception e) {
            logger.error("failed ImagePool construct " + e);
        }
    }

    // get a shuffled list of images
    public List<BufferedImage> getImages() {
        return images;
    }

    // get a shuffled list of images
    public List<BufferedImage> getShuffledImages() {
        try {
            // clone images list and shuffle it
            List<BufferedImage> images2 = new ArrayList<>();
            images2.addAll(images);
            Collections.shuffle(images2);
            return images2;
        } catch (Exception e) {
            logger.error("failed images shuffle " + e);
            return null;
        }
    }

    // get next image of the list (in sequential order)
    // when list end reached roll back from first again
    public BufferedImage getNextImage() {
        try {
            if (cursor < images.size()) {
                return images.get(cursor++);
            } else {
                cursor = 0;
                return getNextImage();
            }
        } catch (Exception e) {
            logger.error("failed get next image " + e);
            return null;
        }
    }
}
