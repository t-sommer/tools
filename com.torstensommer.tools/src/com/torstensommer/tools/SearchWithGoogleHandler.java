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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class SearchWithGoogleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage().getSelection();
		
		if(selection instanceof ITextSelection) {
			String text = ((ITextSelection) selection).getText();
			ToolsUtil.search(text);
		}
		
		return null;
	}

}
