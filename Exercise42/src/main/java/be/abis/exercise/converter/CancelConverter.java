package be.abis.exercise.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CancelConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
       // System.out.println("converting boolean to DB");
        String valInDb = null;
        if (aBoolean){
            valInDb="c";
        }
        return valInDb;
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        //System.out.println("converting from DB to boolean");
        Boolean valInJava=true;
        if (s==null){
            valInJava=false;
        }
        return valInJava;
    }
}
