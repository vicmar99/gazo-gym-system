package com.gazo.gymsystem.service.impl;

import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.service.CredencialService;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.io.ByteArrayOutputStream;

@Service
public class CredencialServiceImpl implements CredencialService {

    @Override
    public byte[] generarCredencial(Cliente cliente) throws Exception {

        InputStream plantillaStream = getClass()
                .getResourceAsStream("/static/credenciales/plantilla.png");

        if (plantillaStream == null) {
            throw new RuntimeException("No se encontró plantilla.png");
        }

        BufferedImage plantilla = ImageIO.read(plantillaStream);

        byte[] qrBytes = generarQr(cliente.getIdCliente());

        BufferedImage qr = ImageIO.read(
                new ByteArrayInputStream(qrBytes)
        );

        Graphics2D g = plantilla.createGraphics();

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        /*
         * QR
         */
        int qrSize = 520;

        int qrX = (plantilla.getWidth() - qrSize) / 2;

        int qrY = 550;

        g.drawImage(
                qr,
                qrX,
                qrY,
                qrSize,
                qrSize,
                null
        );

        /*
         * Nombre
         */
        g.setColor(Color.WHITE);

        g.setFont(
                new Font("Arial", Font.BOLD, 56)
        );

        String nombre = cliente.getNombre().toUpperCase();

        FontMetrics nombreMetrics =
                g.getFontMetrics();

        int nombreX =
                (plantilla.getWidth()
                        - nombreMetrics.stringWidth(nombre))
                        / 2;

        int nombreY = 1350;

        g.drawString(
                nombre,
                nombreX,
                nombreY
        );

        /*
         * ID
         */
        g.setFont(
                new Font("Arial", Font.BOLD, 42)
        );

        String idTexto =
                "ID SOCIO: " + cliente.getIdCliente();

        FontMetrics idMetrics =
                g.getFontMetrics();

        int idX =
                (plantilla.getWidth()
                        - idMetrics.stringWidth(idTexto))
                        / 2;

        int idY = 1480;

        g.drawString(
                idTexto,
                idX,
                idY
        );

        g.dispose();

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        ImageIO.write(
                plantilla,
                "PNG",
                outputStream
        );

        return outputStream.toByteArray();
    }

    private byte[] generarQr(String contenido) throws Exception {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(
                contenido,
                BarcodeFormat.QR_CODE,
                500,
                500
        );

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(
                bitMatrix,
                "PNG",
                outputStream
        );

        return outputStream.toByteArray();

    }

}