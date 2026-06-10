package org.example.fileutils;

import org.example.VulnerableServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

public class FileUtils_CopyUrlToFile_variant extends VulnerableServlet
{
    private static final FileUtils_CopyUrlToFile_variant servlet = new FileUtils_CopyUrlToFile_variant();

    private static final File DOWNLOADS = new File("/home/safeuiser/downloads");

    private static URL url_string_as_url(String filename)
    {
        try
        {
            return URI.create(filename).toURL();
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        var bytes = req.getInputStream().readAllBytes();
        var url_string = new String(bytes, Charset.defaultCharset());
        var url = url_string_as_url(url_string);
        FileUtils.copyURLToFile(url, DOWNLOADS);
        resp.setStatus(200);
        resp.getOutputStream().println("File copied");
    }

    public void run() throws IOException
    {
        servlet.service(new VulnerableRequest(), new VulnerableResponse());
    }
}
