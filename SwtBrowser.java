package swtBrowser;
/**
 * SWT Browser Example
 * 
 * Developing SWT applications using Eclipse:
 * https://www.eclipse.org/swt/eclipse.php
 * 
 * @author John
 * @version 0.2
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class SwtBrowser {
	
	private Combo combo;
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
		
		// Controls
		Button bBack = new Button(shell, SWT.NONE);
		bBack.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		bBack.setToolTipText("Back");
		bBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				browser.back();
			}
		});
		bBack.setText("<");
        
	        Button bForward = new Button(shell, SWT.NONE);
	        bForward.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
	        bForward.setToolTipText("Forward");
	        bForward.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseDown(MouseEvent e) {
        			browser.forward();
        		}
        	});
	        bForward.setText(">");

        	combo = new Combo(shell, SWT.NONE);
        	combo.addKeyListener(new KeyAdapter() {
        		@Override
        		public void keyPressed(KeyEvent e) {
				// Enter was pressed.
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					browser.setUrl(combo.getText());
				}        		
        		}
        	});
        	combo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        	combo.setText("http://www.eclipse.org");
        	combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button bUrl = new Button(shell, SWT.NONE);
		bUrl.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		bUrl.setToolTipText("Go to URL");
		bUrl.setText("URL");
		bUrl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				browser.setUrl(combo.getText());
			}
		});
		
		// SWT Browser
		browser=new Browser(shell,SWT.NONE);
		browser.addLocationListener(new LocationAdapter() {
			@Override
			public void changed(LocationEvent event) {
				if(!browser.getUrl().equals(combo.getText())){
					// Synchronize URL in combo 
					int idx = combo.indexOf(browser.getUrl());
					if (idx<0){
						combo.add(browser.getUrl(),0);
						combo.select(0);
					} else {
						combo.select(idx);
					}
					//System.out.println(event.location);
				}
			}
		});
		browser.setUrl(combo.getText());
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
 * --- How to download SWT?
 * http://download.eclipse.org/eclipse/downloads/
 * -> Latest Release / SWT Binary and Source / swt-4.6.3-win32-win32-x86_64.zip
 * 
 * --- How to import the downloaded .zip file into Workspace?
 * File / Import / Existing Project into Workspace
 * -> Select archive file: swt-4.6.3-win32-win32-x86_64.zip
 * 
 * Project / Properties / Java Build Path /
 * -> Right side panel [Projects] Add org.eclipse.swt
 * 
 * --- Useful Links:
 * http://www.vogella.com/tutorials/SWT/article.html#example-creating-a-plug-in-project-with-a-dependency-to-swt
 * https://www.eclipse.org/articles/Article-Understanding-Layouts/Understanding-Layouts.htm
 * http://www.java2s.com/Code/JavaAPI/org.eclipse.swt.browser/BrowsersetUrlStringurl.htm
 * http://www.java2s.com/Code/JavaAPI/org.eclipse.swt.browser/Browserback.htm
 * https://www.eclipse.org/swt/faq.php#browserspecifydefault
 * 
 * --- keyCode listener and example snippet: 
 * https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
 * http://www.java2s.com/Tutorial/Java/0280__SWT/Printkeystatecodeandcharacter.htm
 * 
 */
