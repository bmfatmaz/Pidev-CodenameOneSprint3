/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class QRCode {
    
 public Image getQrCode(String c) {
    String text = c;
    int width = Display.getInstance().getDisplayWidth();
    int height = Display.getInstance().getDisplayHeight();
    Map<EncodeHintType, Object> hints = new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

    BitMatrix bitMatrix;
    try {
        bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
    } catch (WriterException e) {
        System.err.println("Error generating QR code: " + e.getMessage());
        return null;
    }

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
    } catch (IOException e) {
        System.err.println("Error writing QR code to output stream: " + e.getMessage());
        return null;
    }

    byte[] imageData = baos.toByteArray();
    Image qrImage = Image.createImage(imageData, 0, imageData.length);

    System.out.println("QR code generated successfully.");

    return qrImage;
}

    
    
}
