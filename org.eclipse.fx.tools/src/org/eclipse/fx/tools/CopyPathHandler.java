package org.eclipse.fx.tools;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;

public class CopyPathHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage().getSelection();

		if (selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection)
					.getFirstElement();

			String path = null;

			if (firstElement instanceof IResource) {
				IResource resource = (IResource) firstElement;
				IPath location = resource.getLocation();
				path = location.toOSString();
			} else if (firstElement instanceof IAdaptable) {
				IAdaptable ad = (IAdaptable) firstElement;
				IResource resource = (IResource) ad.getAdapter(IResource.class);
				IPath location = resource.getLocation();
				path = location.toOSString();
			}

			if (path != null) {
				Clipboard clipboard = null;
				try {
					clipboard = new Clipboard(HandlerUtil.getActiveShell(event)
							.getDisplay());
					clipboard.setContents(new Object[] { path },
							new Transfer[] { TextTransfer.getInstance() });
				} finally {
					if (clipboard != null) {
						clipboard.dispose();
					}
				}
			}
		}

		return null;
	}

}
