package org.example;


import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Start {
    public static void start(String path,int priceCell,int linkCell) throws IOException, InterruptedException {
        Double theirPrice = null;
        priceCell--;
        linkCell--;


        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        fileInputStream.close();
        int rows = sheet.getLastRowNum()+1;

        for (int r = 0; r < rows; r++) {
            XSSFRow row = sheet.getRow(r);
            XSSFCell cell1 = row.getCell(linkCell);
            XSSFCell cell2 = row.getCell(priceCell);
            String link = String.valueOf(cell1);
            try {
                Document document = Jsoup.connect(link).get();
                Elements e = document.getElementsByClass("item-price");
                Element element = e.get(0);
                Elements elements = element.getElementsByTag("div");
                Element element1 = elements.get(2);
                Node node = element1.childNode(0);
                Node node2 = element1.childNode(1);
                theirPrice = Double.parseDouble(String.valueOf(node));
                double decimal = Double.parseDouble(String.valueOf(node2.childNode(0)));
                decimal = decimal / 100;
                theirPrice += decimal;
            }catch (Exception e){
                e.printStackTrace();
            }
            if(theirPrice != null){
                cell2.setCellValue(theirPrice);
            }
        }
        FileOutputStream out = new FileOutputStream(path);
        workbook.write(out);
        out.close();
    }
}
