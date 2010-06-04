package org.radrails.rails.internal.ui.commands;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.radrails.rails.ui.RailsUIPlugin;

public class SendFeedbackCommandHandler extends AbstractHandler
{
	private static final String SEND_FEEDBACK_URL_STRING = "https://radrails.tenderapp.com/discussions/radrails-3-prerelease#new_topic_form"; //$NON-NLS-1$
	private static URL SEND_FEEDBACK_URL;

	static
	{
		try
		{
			SEND_FEEDBACK_URL = new URL(SEND_FEEDBACK_URL_STRING);
		}
		catch (MalformedURLException e)
		{
			RailsUIPlugin.logError(e);
		}
	}

	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		if (SEND_FEEDBACK_URL == null)
		{
			return null;
		}

		try
		{
			IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();

			if (support.isInternalWebBrowserAvailable())
			{
				support.createBrowser(
						IWorkbenchBrowserSupport.NAVIGATION_BAR | IWorkbenchBrowserSupport.LOCATION_BAR
								| IWorkbenchBrowserSupport.AS_EDITOR | IWorkbenchBrowserSupport.STATUS, "SendFeedback", //$NON-NLS-1$
						null, // Set the name to null. That way the browser tab will display the title of page loaded in the browser.
						null).openURL(SEND_FEEDBACK_URL);
			}
			else
			{
				support.getExternalBrowser().openURL(SEND_FEEDBACK_URL);
			}
		}
		catch (PartInitException e)
		{
			RailsUIPlugin.logError(e);
		}

		return null;
	}

}
