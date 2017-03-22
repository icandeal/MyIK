package org.wltea.analyzer.lucene;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;

/**
 * Created by yuchunfan on 2016/12/29.
 */
public class HDFSResourceLoader implements ResourceLoader {
    private final ResourceLoader loader;
    private final FileSystem fileSystem;
    private final String baseDirectory;

    public HDFSResourceLoader(String baseDirectory) throws IOException, URISyntaxException {
        this(baseDirectory, new ClasspathResourceLoader());
    }
    public HDFSResourceLoader(String baseDirectory, ResourceLoader delegate) throws IOException, URISyntaxException {
        if(baseDirectory == null) {
            throw new NullPointerException();
        }
        FileSystem fileSystem = FileSystem.get(new URI(baseDirectory), new Configuration());
        if(!fileSystem.isDirectory(new Path(baseDirectory))) {
            throw new IllegalArgumentException(baseDirectory + " is not a directory");
        } else if(delegate == null) {
            throw new IllegalArgumentException("delegate ResourceLoader may not be null");
        } else {
            this.baseDirectory = baseDirectory;
            this.fileSystem = fileSystem;
            this.loader = delegate;
        }
    }
    @Override
    public InputStream openResource(String s) throws IOException {
        try {
            return this.fileSystem.open(new Path(this.baseDirectory+s));
        } catch (NoSuchFileException | FileNotFoundException var3) {
            return this.fileSystem.open(new Path(s));
        }
    }

    @Override
    public <T> Class<? extends T> findClass(String s, Class<T> aClass) {
        return this.loader.findClass(s, aClass);
    }

    @Override
    public <T> T newInstance(String s, Class<T> aClass) {
        return this.newInstance(s, aClass);
    }
}
