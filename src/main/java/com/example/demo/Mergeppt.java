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

// Importing Apache POI environment packages
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;

@RestController
public class Mergeppt {

    @CrossOrigin
    @PostMapping("/mergePowerpoints")
    public String merge(@RequestBody ArrayList<String> powerpoints) throws IOException {
        String intropath = "/root/Dropbox/";
        System.out.println(powerpoints);

        // Creating an empty presentation
        XMLSlideShow ppt = new XMLSlideShow();

        if (!powerpoints.isEmpty()) {
            for (String arg : powerpoints) {
                FileInputStream inputstream = new FileInputStream(arg);
                XMLSlideShow src = new XMLSlideShow(inputstream);
                ppt.setPageSize(src.getPageSize());
                // Copy slides along with their layouts
                for (XSLFSlide srcSlide : src.getSlides()) {
                    ppt.createSlide().importContent(srcSlide);
                }

                src.close();
                inputstream.close();
            }

            String mergedFile = intropath + "PowerPoints/result1.pptx";
            FileOutputStream out = new FileOutputStream(mergedFile);
            ppt.write(out);
            ppt.close();
            out.close();

            System.out.println("All files merged successfully!");
            System.out.println(mergedFile);
        } else {
            System.out.println("No Presentation files found in current directory!");
        }
        return "";
    }
}
