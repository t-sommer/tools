package org.eclipse.fx.tools;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

public class SearchForAction implements IEditorActionDelegate{
	
	private ISelection selection;

	@Override
	public void run(IAction action) {
		if(selection instanceof ITextSelection) {
			String text = ((ITextSelection) selection).getText();
			ToolsUtil.search(text);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// nothing to do
	}

}
