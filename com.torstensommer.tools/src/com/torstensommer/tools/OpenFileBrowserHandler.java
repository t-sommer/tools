package com.torstensommer.tools;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenFileBrowserHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage().getSelection();
		
		if (selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection).getFirstElement();

			String path = null;

			if (firstElement instanceof IResource) {
				IResource resource = (IResource) firstElement;
				path = getContainerPath(resource);

			} else if (firstElement instanceof IAdaptable) {
				IAdaptable ad = (IAdaptable) firstElement;

				IResource resource = (IResource) ad.getAdapter(IResource.class);
				if (resource != null)
					path = getContainerPath(resource);
			}

			if (path != null)
				ToolsUtil.openExplorer(path);
		}
		
		return null;
	}
	
	static String getContainerPath(IResource resource) {
		IPath location = resource.getLocation();
		String osString = location.toOSString();
		File file = new File(osString);
		return file.isFile() ? file.getParent() : osString;
	}

}
