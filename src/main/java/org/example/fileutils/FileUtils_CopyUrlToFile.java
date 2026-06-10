package org.example.fileutils;

import org.example.VulnerableServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class FileUtils_CopyUrlToFile extends VulnerableServlet
{
    private static final FileUtils_CopyUrlToFile servlet = new FileUtils_CopyUrlToFile();

    private static final File DOWNLOADS = new File("/home/safeuiser/downloads");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        var bytes = req.getInputStream().readAllBytes();
        var url_string = new String(bytes, Charset.defaultCharset());
        var url = new URL(url_string);
        FileUtils.copyURLToFile(url, DOWNLOADS);
        resp.setStatus(200);
        resp.getOutputStream().println("File copied");
    }

    public void run() throws IOException
    {
        servlet.service(new VulnerableRequest(), new VulnerableResponse());
    }
}
