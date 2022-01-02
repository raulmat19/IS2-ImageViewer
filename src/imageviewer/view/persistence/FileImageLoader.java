package imageviewer.view.persistence;

import java.util.logging.Logger;
import imageviewer.model.Image;
import imageviewer.model.ProxyImage;
import imageviewer.model.RealImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;

public class FileImageLoader implements ImageLoader{
    
    private final File[] files;
    private int current;
    
    public FileImageLoader(File folder) {
        this.files = folder.listFiles(imageTypes());
        this.current = 0;
    }
    
    private FileFilter imageTypes() {
        return (File pathname) -> pathname.getName().endsWith(".jpg");
    }
    
    @Override
    public Image load() {
        return new ProxyImage(this.files[this.current]);
    }
    
    @Override
    public Image next() {
        if (this.current == this.files.length - 1){
            this.current = 0;
        } else {
            this.current++;
        }
        return this.load();
    }

    @Override
    public Image prev() {
        if (this.current == 0){
            this.current = this.files.length - 1;
        } else {
            this.current--;
        }
        return this.load();
    }
    
    
}
