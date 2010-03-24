package com.aptana.ruby.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class RubyDebugUIPlugin extends AbstractUIPlugin
{

	// The plug-in ID
	private static final String PLUGIN_ID = "com.aptana.ruby.debug.ui"; //$NON-NLS-1$

	public static final String IMG_EVIEW_ARGUMENTS_TAB = "icons/full/eview16/arguments_tab.gif"; //$NON-NLS-1$

	// The shared instance
	private static RubyDebugUIPlugin plugin;

	/**
	 * The constructor
	 */
	public RubyDebugUIPlugin()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static RubyDebugUIPlugin getDefault()
	{
		return plugin;
	}

	public static String getPluginId()
	{
		return PLUGIN_ID;
	}

	public static void logError(String msg, Throwable e)
	{
		log(new Status(IStatus.ERROR, getPluginId(), msg, e));
	}

	public static void logError(CoreException e)
	{
		log(e.getStatus());
	}

	public static void logWarning(String warning)
	{
		log(new Status(IStatus.WARNING, getPluginId(), warning));
	}

	public static void logInfo(String string)
	{
		if (getDefault() != null && getDefault().isDebugging())
			log(new Status(IStatus.INFO, getPluginId(), string));
	}

	public static void trace(String string)
	{
		if (getDefault() != null && getDefault().isDebugging())
			log(new Status(IStatus.OK, getPluginId(), string));
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg)
	{
		super.initializeImageRegistry(reg);
		reg.put(IMG_EVIEW_ARGUMENTS_TAB, imageDescriptorFromPlugin(PLUGIN_ID, IMG_EVIEW_ARGUMENTS_TAB));
	}

	public static void log(IStatus status)
	{
		if (getDefault() != null)
			getDefault().getLog().log(status);
	}

	public static String getUniqueIdentifier()
	{
		return getPluginId();
	}

}
