package contact1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

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
		Hashtable<String, String> props = new Hashtable<String, String>();
        props.put("Structure", "Hospital");
        context.registerService(ContactService.class.getName(), new Contact1Impl(), props);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	 private static class Contact1Impl implements ContactService
	    {
		 
		 public List<String> readInformation(){
			 List<String> informations = new ArrayList<String>(); 
			 try {
					
				 BufferedReader IN = new BufferedReader (new FileReader("C:\\Users\\aurel\\Desktop\\JEEWorkspace\\plugInFelix\\ContactPlugin\\Hopital.txt"));
				 String ligne;
				 String[] mots;	 
				 while ((ligne = IN.readLine ())!= null){			 
					 informations.add(ligne);
				 }
				 IN.close();
				 }
				 catch (IOException ex) {
			            ex.printStackTrace();
			        }
			 
			 return  informations;
		 }
		 
		 public String printInformation() {
			 String info = null;
			 // les informations sont dans l'ordre adresse - téléphone - docteur
			 List<String> informations = readInformation();
			 
			 info = "Adresse : " + informations.get(0) + " Téléphone : " + informations.get(1) + "Docteur : " + informations.get(2) ;
			 
			 return info;
		 }
	    }

}
