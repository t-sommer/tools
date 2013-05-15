/*******************************************************************************
 * Copyright (c) 2013 Torsten Sommer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Torsten Sommer <tsommer@tesis.de> - initial API and implementation
 *******************************************************************************/
package com.torstensommer.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ToolsUtil {

	static void search(String text) {
		try {
			URL url = new URL("http://www.google.com/search?q=" + text);
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser()
					.openURL(url);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void openExplorer(String path) {
		Runtime runtime = Runtime.getRuntime();
		try {
			String os = Platform.getOS();
			String command = null;
			
			if (Platform.OS_MACOSX.equals(os))
				command = "open " + path;
			else if (Platform.OS_WIN32.equals(os))
				command = "explorer " + path;

			if (command != null) {
				Process process = runtime.exec(command);
				process.waitFor();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void openCommandLine(String path) {
		Runtime runtime = Runtime.getRuntime();
		try {
			String os = Platform.getOS();
			String command = null;
			
			if (Platform.OS_MACOSX.equals(os))
				command = "open -a Terminal " + path;
			else if (Platform.OS_WIN32.equals(os))
				command = "explorer " + path;

			if (command != null) {
				Process process = runtime.exec(command);
				process.waitFor();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
