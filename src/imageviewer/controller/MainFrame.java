package imageviewer.controller;

import imageviewer.view.persistence.ImageLoader;
import imageviewer.view.ui.ImageDisplay;
import imageviewer.view.ui.SwingImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame{
    
    private ImageDisplay imageDisplay;
    final ImageLoader imageLoader;

    public MainFrame(ImageLoader imageLoader) {
        
        this.imageLoader = imageLoader;
        
        this.setTitle("ImageViewer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1280, 900);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imageDisplay());
        this.getContentPane().add(toolbar(), BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }
    
    private JPanel imageDisplay() {
            
        SwingImageDisplay sid = new SwingImageDisplay();
        this.imageDisplay = sid;
        return sid;
    }

    private Component toolbar() {
        
        JPanel panel = new JPanel();
        panel.add(prevbutton());
        panel.add(nextbutton());
        return panel;
    }

    private JButton nextbutton() {
        
        JButton button = new JButton(">");
        button.addActionListener(nextImage());
        return button;
    }

    private JButton prevbutton() {
        JButton button = new JButton("<");
        button.addActionListener(prevImage());
        return button;
    }

    private ActionListener prevImage() {
        
        return e -> 
            imageDisplay.show(imageLoader.prev());
    }

    private ActionListener nextImage() {
        
        return e -> 
            imageDisplay.show(imageLoader.next());
    }
    
    
}
