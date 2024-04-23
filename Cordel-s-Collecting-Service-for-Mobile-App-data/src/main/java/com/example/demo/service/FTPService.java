package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class FTPService {
    @Value("${ftp.server.host}")
    private String ftpHost;

    @Value("${ftp.server.port}")
    private int ftpPort;

    @Value("${ftp.server.username}")
    private String ftpUsername;

    @Value("${ftp.server.password}")
    private String ftpPassword;

    public boolean uploadFile(byte[] fileData, String remoteFilePath) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(this.ftpHost, this.ftpPort);
            if(!FTPReply.isPositiveCompletion((ftpClient.getReplyCode()))) {
                return false;
            }
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileData)) {
                return ftpClient.storeFile(remoteFilePath, inputStream);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                ftpClient.disconnect();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean downloadFile(String remoteFilePath, ByteArrayOutputStream outputStream) {
        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(this.ftpHost, this.ftpPort);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                return false;
            }
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            boolean retrieved = ftpClient.retrieveFile(remoteFilePath, outputStream);
            ftpClient.logout();

            return retrieved;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
