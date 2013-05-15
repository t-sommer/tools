package com.torstensommer.tools;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class ShowInExplorerAction implements IViewActionDelegate {

	private ISelection selection;

	@Override
	public void run(IAction action) {

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

	}

	static String getContainerPath(IResource resource) {
		IPath location = resource.getLocation();
		String osString = location.toOSString();
		File file = new File(osString);
		return file.isFile() ? file.getParent() : osString;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void init(IViewPart view) {
		// nothing to do
	}

}
