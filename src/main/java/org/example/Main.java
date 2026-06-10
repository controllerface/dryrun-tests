package org.example;

import org.example.fileutils.FileUtils_CopyUrlToFile_wTimeout;
import org.example.fileutils.FileUtils_FileGet;
import org.example.fileutils.FileUtils_FileGet_wDir;
import org.example.fileutils.FileUtils_UrlToFile;
import org.example.fileutils.FileUtils_UrlToFiles;
import org.example.ioutils.IOUtils_InputBuffer;
import org.example.ioutils.IOUtils_InputBuffer_wSize;
import org.example.ioutils.IOUtils_InputReader;
import org.example.ioutils.IOUtils_InputReader_wSize;

public class Main
{
    static void main(String args[]) throws Exception
    {
        new FileUtils_FileGet().run();
        new FileUtils_FileGet_wDir().run();
        new FileUtils_UrlToFile().run();
        new FileUtils_UrlToFiles().run();

        new IOUtils_InputBuffer().run();
        new IOUtils_InputReader().run();
        new IOUtils_InputBuffer_wSize().run();
        new IOUtils_InputReader_wSize().run();

        new FileUtils_CopyUrlToFile_wTimeout().run();
    }
}
