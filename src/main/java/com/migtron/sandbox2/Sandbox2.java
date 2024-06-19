package com.migtron.sandbox2;

import com.migtron.tron.utils.math.Covariance;
import com.migtron.tron.utils.math.Vec3f;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.ColourSpace;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.pixel.Pixel;
import org.openimaj.image.typography.hershey.HersheyFont;

public class Sandbox2 {

    public static void main(String[] args) {

        //testHello();
        //testPatches();
        //testStreams();
        testStreams2();
    }

    // create a HelloWorld image using openimaj lib
    private static void testHello() {
        try {
            MBFImage image = new MBFImage(320, 70, ColourSpace.RGB);

            //Fill the image with white
            image.fill(RGBColour.WHITE);

            //Render some test into the image
            image.drawText("Hello World", 10, 60, HersheyFont.CURSIVE, 50, RGBColour.BLACK);

            //Apply a Gaussian blur
            //image.processInplace(new FGaussianConvolve(2f));
            //Display the image
            DisplayUtilities.display(image);
        } catch (Exception e) {
            System.err.println("failed testing HelloWorld " + e);
        }
    }

    // fill an image with colored rectangular patches (using openimaj lib)
    private static void testPatches() {

        try {
            MBFImage image = ImageUtilities.readMBF(new File(ImagePool.IMAGE_PATH1));

            //MBFImage image2 = flipBands(image);
            MBFImage red = new MBFImage(100, 50, ColourSpace.RGB);
            red.fill(RGBColour.RED);
            MBFImage green = red.clone();
            green.fill(RGBColour.GREEN);
            MBFImage blue = red.clone();
            blue.fill(RGBColour.BLUE);

            MBFImage yellow = red.clone();
            yellow.fill(RGBColour.YELLOW);
            MBFImage cyan = red.clone();
            cyan.fill(RGBColour.CYAN);
            MBFImage magenta = red.clone();
            magenta.fill(RGBColour.MAGENTA);

            MBFImage white = red.clone();
            white.fill(RGBColour.WHITE);
            MBFImage black = red.clone();
            black.fill(RGBColour.BLACK);

            // R, G, B, white
            Pixel point = new Pixel(50, 20);
            int gap = 70;
            pushImage(red, image, point);
            point.y += gap;
            pushImage(green, image, point);
            point.y += gap;
            pushImage(blue, image, point);
            point.y += gap;
            pushImage(white, image, point);

            // Y, C, M, black
            point = new Pixel(600, 20);
            pushImage(yellow, image, point);
            point.y += gap;
            pushImage(cyan, image, point);
            point.y += gap;
            pushImage(magenta, image, point);
            point.y += gap;
            pushImage(black, image, point);

            DisplayUtilities.display(image);

        } catch (Exception e) {
            System.err.println("failed testing patches " + e);
        }
    }

    // test java streams
    private static void testStreams() {
        try {
            // create a list of integers
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i < 5; i++) {
                list.add(i);
                System.out.println("added " + i);
            }

            // sum them with a stream
            int sum = list.stream().reduce(0, (a, b) -> a + b);
            System.out.println("sum = " + sum);

        } catch (Exception e) {
            System.err.println("failed testing streams " + e);
        }
    }

    // test java streams
    private static void testStreams2() {
        try {
            // create a list of covariances
            Point2D.Float pos = new Point2D.Float(100, 100);
            Vec3f covs = new Vec3f(20000, 6000, 0);
            int mass = 100;
            Covariance covariance = new Covariance(pos, covs, mass);
            List<Covariance> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                list.add(covariance);
                System.out.println("added cov with mass " + mass);
            }

            // sum their masses with a stream
            int sum = list.stream().collect(Collectors.summingInt(Covariance::getMass));
            System.out.println("sum = " + sum);
        } catch (Exception e) {
            System.err.println("failed testing streams 2 " + e);
        }
    }

    private static void pushImage(MBFImage source, MBFImage destiny, Pixel point) {
        try {
            for (int band = 0; band < 3; band++) {
                destiny.getBand(band).overlayInplace(source.getBand(band), point.x, point.y);
            }
        } catch (Exception e) {
            System.err.println("failed testing streams " + e);
        }
    }

    private static MBFImage flipBands(MBFImage image) {
        try {
            MBFImage image2 = new MBFImage(ColourSpace.RGB, image.getBand(0), image.getBand(2), image.getBand(1));
//            image2.getBand(1).fill(0f);
            return image2;
        } catch (Exception e) {
            System.err.println("failed flipping bands " + e);
            return null;
        }
    }

}
