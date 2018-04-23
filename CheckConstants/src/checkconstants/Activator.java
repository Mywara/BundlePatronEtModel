package checkconstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
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
		int cpt = 0;
	        if (refs != null)
	        {
	        //    try
	         //   {
	              //  System.out.println("Enter a blank line to exit.");
	              //  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	               // String word = "";

	                // Loop endlessly.
	                while (cpt < 100)
	                {
	                    // get a contact service 
	                    ContactService contact = (ContactService) context.getService(refs[0]);
	                    String data = ReceiveConnection();
	                   
	                    if (data != null) {
	                    	System.out.println("data receive " + data);
	                        String[] datas = new String[10];	                    
		                    datas = data.split(" ");
		                    if(datas[4].equals("temperature")) {
		                    	if(Float.parseFloat(datas[6]) > 41) {
		                    		System.out.println("High temperature");
		                    		System.out.println(contact.printInformation());
		                    	}else {
		  	                    	System.out.println("everything ok");
		  	                    }
		                    }
		                    else if(datas[4].equals("cardio")) {
		                    	if(Float.parseFloat(datas[6]) > 180) {
		                    		System.out.println("High pulsation");
		                    		System.out.println(contact.printInformation());
		                    	}else {
		  	                    	System.out.println("everything ok");
		  	                    }
		                    }
		                    else {
		                    	System.out.println("error split " + datas[4]);
		                    }
	                    }
	                    context.ungetService(refs[0]);
	                    cpt++;
	                }
	          //  } catch (IOException ex) { }
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
	
	 public static String ReceiveConnection(){
	
		 try {
			// System.out.println("StartClient");
	         Socket skt = new Socket("localhost", 1234);
	         BufferedReader in = new BufferedReader(new
	            InputStreamReader(skt.getInputStream()));
	        // System.out.print("Received string: ");

	         while (!in.ready()) {}
	         String data = in.readLine();
	      //   System.out.println(data);

	       //  System.out.print("\n");
	         in.close();
	         skt.close();
	         return data;
	      }
	      catch(Exception e) {
	         //System.out.print("Whoops! It didn't work!\n");
	      }
		return null;
		 
	 }
	
}
