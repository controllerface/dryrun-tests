package org.example.ioutils;

import org.example.VulnerableServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class IOUtils_InputReader_wSize extends VulnerableServlet
{
    private static final IOUtils_InputReader_wSize servlet = new IOUtils_InputReader_wSize();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        var buffer = IOUtils.buffer(req.getReader(), 256);
        var filename = buffer.readLine();
        var file = new File(filename);
        resp.setStatus(200);
        resp.getOutputStream().println(Files.readString(file.toPath()));
    }

    public void run() throws IOException
    {
        servlet.service(new VulnerableRequest(), new VulnerableResponse());
    }
}
