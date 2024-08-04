package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.File;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

// importing apache POI environment packages
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

@RestController
public class Mergeppt {

    @CrossOrigin
    @PostMapping("/mergePowerpoints")
    public String merge(@RequestBody ArrayList<String> powerpoints) throws IOException {
        String intropath = "/root/Dropbox/";
        System.out.println(powerpoints);

        // Creating empty presentation
        XMLSlideShow ppt = new XMLSlideShow();

        // Set the page size to 16:9 aspect ratio
        ppt.setPageSize(new java.awt.Dimension(960, 540));

        if (!powerpoints.isEmpty()) {

            for (String arg : powerpoints) {
                // String newFile = arg.replaceAll("/", "\\");
                FileInputStream inputstream = new FileInputStream(arg);
                // Getting current presentation file path in
                // a FileInputStream
                XMLSlideShow src = new XMLSlideShow(inputstream);
                // Getting all the slides of the
                // presentation file in a XMLSlideShow
                // object

                // Set the page size of the destination presentation to match the source
                // presentation
                ppt.setPageSize(src.getPageSize());

                for (XSLFSlide srcSlide : src.getSlides()) {
                    ppt.createSlide().importContent(srcSlide);
                    // Appending each presentation slide to
                    // empty presentation object ppt
                }
                src.close();
                inputstream.close();
            }

            String mergedFile = intropath + "/PowerPoints/result1.pptx";
            // Creating new file path
            FileOutputStream out = new FileOutputStream(mergedFile);
            // Creating the file object

            ppt.write(out);
            ppt.close();
            // Saving the changes to the new file
            System.out.println("All files merged successfully!");
            System.out.println(mergedFile);
            out.close();
        } else {
            System.out.println("No Presentation files found in current directory!");
        }
        return "";
    }

}
