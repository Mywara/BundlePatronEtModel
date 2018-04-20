package checkconstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import layer.applications.ApplicationImpl;
import service.ContactService;






public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		ServiceReference[] refs = context.getServiceReferences(ContactService.class.getName(), "(Structure=*)");

	        if (refs != null)
	        {
	            try
	            {
	                System.out.println("Enter a blank line to exit.");
	                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	                String word = "";

	                // Loop endlessly.
	                while (true)
	                {
	                    // Ask the user to enter a word.
	                    System.out.print("Enter const: ");
	                    word = in.readLine();

	                    // If the user entered a blank line, then
	                    // exit the loop.
	                    if (word.length() == 0)
	                    {
	                        break;
	                    }

	                    // get a contact service 
	                    ContactService contact = (ContactService) context.getService(refs[0]);
	                    if(Float.parseFloat(word) >= 40 ) {
	                    	//change
	                    	String c = getS(); // ApplicationImpl.
	                    	
	                    	System.out.println(contact.printInformation() + "info app : "/* + c*/);
	                    }else {
	                    	System.out.println("everything ok");
	                    }

	                    context.ungetService(refs[0]);
	                }
	            } catch (IOException ex) { }
	        }
	        else
	        {
	            System.out.println("Couldn't find any contact service...");
	        }
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
}
