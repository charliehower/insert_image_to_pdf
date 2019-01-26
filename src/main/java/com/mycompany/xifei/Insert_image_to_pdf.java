/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xifei;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

//import com.itextpdf.kernel.pdf.PdfReader;

public class Insert_image_to_pdf {

    public static void main(String[] args) throws Exception {
        String directoryPath = "/Users/charlie/Desktop/pdf/";
        File file = new File(directoryPath);
        File[] fs = file.listFiles();
        //root directory
        for (File f : fs) {
            if (!f.isDirectory() && f.toString().contains(".pdf"))
                System.out.println(f);
            else if (f.isDirectory()) {
                //enter separate directory
                file = new File(f.getAbsolutePath());
                fs = file.listFiles();
                for (File pdf : fs) {
                    if (!pdf.isDirectory() && pdf.toString().contains(".pdf")) {

                        // 模板文件路径
                        String templatePath = pdf.getAbsolutePath();
                        // 生成的文件路径
                        String targetPath = pdf.getAbsolutePath();
                        // 图片路径
                        String imagePath = "/Users/charlie/Desktop/pdf/ym.png";
                        try {
                            // 读取模板文件
                            InputStream input = new FileInputStream(new File(templatePath));
                            PdfReader reader = new PdfReader(input);
                            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));

                            // 读图片
                            Image image = Image.getInstance(imagePath);
                            // 获取操作的页面
                            PdfContentByte under = stamper.getOverContent(1);

                            // 添加图片
                            image.setAbsolutePosition(0, 755);
                            image.scalePercent(25);
                            under.addImage(image);

                            stamper.close();
                            reader.close();
                        } catch (Exception e) {
                            System.err.println("Error" + e.getMessage());
                        }
                    }
                }

            }
        }
    }
    /*public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
        Document document = new Document();
        String templatePath = "/Users/charlie/Desktop/pdf/曹彬彬.pdf";
        InputStream input = new FileInputStream(new File(templatePath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, fos);

        //Se da la ruta del archivo
        PdfWriter.getInstance(document, new FileOutputStream("/Users/charlie/Desktop/pdf/曹彬彬.pdf"));
        document.open();
        try {
            //se loe ingresa la ruta de la imgen para insrtar
            Image img = Image.getInstance("/Users/charlie/Desktop/pdf/ym.png");
            //se le adiciona las palabras que desea ingresar al documento
//            document.add(new Paragraph("Sample 1: This is simple image demo y hice refactor y rename del proyecto."));
            //opcional si quiere cambiar la posicion de la imagen
            img.setAbsolutePosition(10, 700);
            img.scalePercent(25);
            //se adiciona al documento la imagen
            document.add(img);
            document.close();
        } catch (DocumentException | IOException e) {
            System.err.println("Error" + e.getMessage());
        }

    }*/
}
