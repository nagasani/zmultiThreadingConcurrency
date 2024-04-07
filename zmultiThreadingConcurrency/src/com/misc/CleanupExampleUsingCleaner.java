package com.misc;

import java.lang.ref.Cleaner;

public class CleanupExampleUsingCleaner 
{
	private static final Cleaner cleaner = Cleaner.create();

	private static class ResourceCleaningAction implements Runnable 
	{
		private final Resource resource;

		ResourceCleaningAction(Resource resource) 
		{
			this.resource = resource;
		}

		@Override
		public void run() 
		{
			resource.cleanUp();
			System.out.println("Resource cleaned up");
		}
	}

	private static class Resource 
	{
		// Dummy resource that needs cleanup
		private boolean cleaned = false;

		public void cleanUp() 
		{
			// cleanup logic here
			this.cleaned = true;
		}

		public boolean isCleaned() 
		{
			return cleaned;
		}
	}

	public static void main(String[] args) 
	{
		Resource resource = new Resource();
		// Registering the resource with the cleaner
		cleaner.register(resource, new ResourceCleaningAction(resource));
		

		System.out.println("Resource cleanup registered with Cleaner");
		
		try 
		{
			Thread.sleep(11110);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		// Cleaner will clean up the resource when it is no longer reachable,
		// no need to explicitly call cleanUp
	}
}
