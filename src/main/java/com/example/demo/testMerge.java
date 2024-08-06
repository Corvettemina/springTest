package com.example.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;

public class testMerge {
    public static void main(String[] args) throws IOException {
        String intropath = "C:/Users/Mina Hanna/Dropbox/";
        String filePath = "C:\\Users\\Mina Hanna\\Documents\\springTest\\demo\\src\\main\\java\\com\\example\\demo\\ppts.txt";
        ArrayList<String> powerpoints = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                powerpoints.add(intropath + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(powerpoints);
        XMLSlideShow ppt = new XMLSlideShow();
        if (powerpoints.size() > 0) {
            for (String arg : powerpoints) {
                try (FileInputStream inputstream = new FileInputStream(arg);
                        XMLSlideShow src = new XMLSlideShow(inputstream)) {

                    ppt.setPageSize(src.getPageSize());

                    // Copy slides
                    for (XSLFSlide srcSlide : src.getSlides()) {
                        ppt.createSlide().importContent(srcSlide);
                    }
                }
            }

            String mergedFile = intropath + "PowerPoints/result1.pptx";
            try (FileOutputStream out = new FileOutputStream(mergedFile)) {
                ppt.write(out);
            }

            ppt.close();
            System.out.println("All files merged successfully!");
            System.out.println(mergedFile);
        } else {
            System.out.println("No Presentation files found in current directory!");
        }

    }
}
