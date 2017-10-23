package Web;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "resources/example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator itA = person.listInstances();
		System.out.println("lista de individuos");
		
		while (itA.hasNext())
		{
			Individual inst = (Individual) itA.next();
			System.out.println("Instance of Person: "+inst.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator itB = person.listSubClasses();
		System.out.println("lista de subclases");
		while (itB.hasNext())
		{
			OntClass subclass = (OntClass) itB.next();
			System.out.println("Subclass of Person: "+subclass.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		ExtendedIterator<OntClass> itC = person.listSubClasses();
		ExtendedIterator<Individual> itD;
		
		OntModel modelA = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,model);
		OntClass personA = model.getOntClass(ns+"Person");
		System.out.println("instancias y subclases");
		

		//Imprimiremos las subclases y las instancias de cada subclase
		while (itC.hasNext()){
	        OntClass classes = (OntClass) itC.next();
	        itD = (ExtendedIterator<Individual>) classes.listInstances();
	        System.out.println("Instances: "+ classes.getURI());
	   		
	   		
	   		while(itD.hasNext()){
	   			System.out.println("Instances: "+ itD.next().getURI());
	   		}

			
		}
	
	}
}
