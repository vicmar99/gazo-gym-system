package com.gazo.gymsystem.service.impl;

import com.gazo.gymsystem.entity.Cliente;
import com.gazo.gymsystem.service.CredencialService;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;

@Service
public class CredencialServiceImpl implements CredencialService {

    @Override
    public byte[] generarCredencial(Cliente cliente) throws Exception {

        return generarQr(cliente.getIdCliente());

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