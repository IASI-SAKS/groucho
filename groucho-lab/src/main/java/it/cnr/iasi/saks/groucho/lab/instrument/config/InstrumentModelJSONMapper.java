package it.cnr.iasi.saks.groucho.lab.instrument.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.cnr.iasi.saks.groucho.lab.instrument.model.InjectableMethodList;

public class InstrumentModelJSONMapper {
	
	public static InjectableMethodList loadFromJSON (InputStream stream) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();

        // Read JSON file and convert to java object
        InjectableMethodList lst = mapper.readValue(stream, InjectableMethodList.class);
        stream.close();

        return lst;
	}

	public static void saveToJSON (InjectableMethodList lst, OutputStream stream) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        // Save JSON string to file
        mapper.writeValue(stream, lst);
        stream.close();
     }

	public static String saveToJSON (InjectableMethodList lst) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();

     // Convert object to JSON string
        String lstJson = mapper.writeValueAsString(lst);
        return lstJson;
	}
}
