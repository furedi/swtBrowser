package swtBrowser;
/**
 * SWT Browser Example
 * 
 * Developing SWT applications using Eclipse:
 * https://www.eclipse.org/swt/eclipse.php
 * 
 * @author John
 */

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class SwtBrowser {
	
	private Browser browser;

	public static void main(String[] args) {
		SwtBrowser sbrowser = new SwtBrowser();
		sbrowser.init();
	}
	
	private void init(){
		// SWT widget
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(4, false));
		shell.setText("SWT Browser Demo");
		
		Button bBack = new Button(shell, SWT.NONE);
		bBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				browser.back();
			}
		});
		bBack.setText("<");
        
        Button bForward = new Button(shell, SWT.NONE);
        bForward.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseDown(MouseEvent e) {
        		browser.forward();
        	}
        });
        bForward.setText(">");

		Text txt = new Text(shell, SWT.NONE);
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// JOptionPane.showMessageDialog(null, e.keyCode);
				// Enter was pressed.
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					browser.setUrl(txt.getText());
				}
			}
		});
		txt.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		txt.setText("http://www.eclipse.org");
        txt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));		
		
		Button bUrl = new Button(shell, SWT.NONE);
		bUrl.setText("URL");
		bUrl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				browser.setUrl(txt.getText());
			}
		});
		
		// SWT Browser
		browser=new Browser(shell,SWT.NONE);
		browser.setUrl(txt.getText());		
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
            else{
            	display.wake();
            }
        }

        display.close();		
	}
}

/*
 * 
 * Download SWT:
 * http://download.eclipse.org/eclipse/downloads/
 * -> Latest Release / SWT Binary and Source / swt-4.6.3-win32-win32-x86_64.zip
 * 
 * Don't forget before coding:
 * File / Import / Existing Project into Workspace
 * -> Select archive file: swt-4.6.3-win32-win32-x86_64.zip
 * 
 * Project / Properties / Java Build Path /
 * -> Right side panel [Projects] Add org.eclipse.swt
 * 
 * Useful Links:
 *   
 * http://www.vogella.com/tutorials/SWT/article.html#example-creating-a-plug-in-project-with-a-dependency-to-swt
 * https://www.eclipse.org/articles/Article-Understanding-Layouts/Understanding-Layouts.htm
 * http://www.java2s.com/Code/JavaAPI/org.eclipse.swt.browser/BrowsersetUrlStringurl.htm
 * 
 * keyCode: 
 * https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
 * http://www.java2s.com/Tutorial/Java/0280__SWT/Printkeystatecodeandcharacter.htm
 */