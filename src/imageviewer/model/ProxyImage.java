package imageviewer.model;

import imageviewer.view.persistence.FileImageLoader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AccessCounter;

public class ProxyImage implements Image{
    
    private final Image realImage;

    public ProxyImage(File file) {
        this.realImage = new RealImage(file.getName(), this.getStream(file));
    }
    
    private InputStream getStream(File file) {
        try {
            return new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            Logger.getLogger(FileImageLoader.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public String name() {
        return this.realImage.name();
    }

    @Override
    public InputStream stream() {
        Integer count = AccessCounter.getInstance().increment(this.name());
        System.out.printf("File '%s' accessed '%d' times\n", this.name(), count);
        return this.realImage.stream();
    }

}
