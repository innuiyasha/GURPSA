package gui;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class JTextFieldInputStream extends InputStream {
    byte[] contents = null;
    int pointer = 0;
    Object lock = new Object();

    public JTextFieldInputStream(final JTextField field) {

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar()=='\n'){
                	String text = field.getText();
                	text += "\r\n";
                    contents = text.getBytes();
                    pointer = 0;
                    field.setText("");
                    synchronized (lock) { lock.notify(); }
                }
                super.keyReleased(e);
            }
        });
    }

    @Override
    public int read() throws IOException {
    	while(contents == null) {
    		try {
    			synchronized (lock) {
    			    lock.wait();
    			} 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
        if(pointer >= contents.length) {
        	contents = null;
        	pointer = 0;
        	return -1;
        }
        return this.contents[pointer++];
    }

}