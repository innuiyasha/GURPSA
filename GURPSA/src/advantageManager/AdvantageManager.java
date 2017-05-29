package advantageManager;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import dataContainers.CharacterFields.Advantage;

@XmlRootElement
public class AdvantageManager {

	@XmlElementWrapper(name = "advantageList")
	private Map<String, Advantage> advantageMap;
	
	public AdvantageManager() {
		advantageMap = new HashMap<String, Advantage>();
	}

	private Map<String,Advantage> getMap()
	{
		return advantageMap;
	}
	
	private void setMap(Map<String,Advantage> newMap)
	{
		advantageMap = newMap;
	}
	
	public void toXMLFile() {
		try {

			File file = new File("resources\\advantageList.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(AdvantageManager.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(this, file);
			jaxbMarshaller.marshal(this, System.out);

		      } catch (JAXBException e) {
		    	  e.printStackTrace();
		      }
	}

	public void GenerateAdvantages(File XMLInput) {
		try {	
			JAXBContext jaxbContext = JAXBContext.newInstance(AdvantageManager.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			this.setMap(((AdvantageManager) jaxbUnmarshaller.unmarshal(XMLInput)).getMap());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		String output = "";
		
		Iterator<Entry<String, Advantage>> i = advantageMap.entrySet().iterator();
		while(i.hasNext())
		{
			Map.Entry<String, Advantage> pair = (Entry<String, Advantage>)i.next();
			output += pair.getKey() + " corresponds to " + pair.getValue().toString() + "\r\n";
		}
		return output;
	}
	
	private String getDescription(String name) {
		return advantageMap.get(name).getDescription();
	}
	
	private String getCost(String name) {
		return advantageMap.get(name).getCost();
	}
	
	private String getNums(String name) {
		return advantageMap.get(name).getNums();
	}

	public String request(String advantageName, String element) {
		advantageName = formatAdvantageName(advantageName);
		element = element.toLowerCase();
		
		switch(element) {
		case "cost":
			return getCost(advantageName);
		case "description": 
			return getDescription(advantageName);
		case "nums": 
			return getNums(advantageName);
		default:
			return "Invalid Entry: " + element;
		}
	}
	
	private String formatAdvantageName(String unformated) {
		String[] components = unformated.split("-");
		String advantageName = utilities.Utilities.capitalizeFully(components[0]);
		
		for(int i = 1; i < components.length; i++) {
			advantageName += "-" + utilities.Utilities.capitalizeFully(components[i]);
		}
		
		System.out.println(advantageName);
		return advantageName;
		
	}
	
	public Boolean isAdvantage(String advantageName) {
		
		return advantageMap.containsKey(advantageName);
	}
	
}
