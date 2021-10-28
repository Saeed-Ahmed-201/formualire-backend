package tech.getarrays.formulairemanager.util;

import org.modelmapper.ModelMapper;

import tech.getarrays.formulairemanager.dto.response.DTOEntity;

public class Mapper {

      ModelMapper m1 = new ModelMapper();
      public DTOEntity convertToDTO(Object object, DTOEntity dto) {
    	  return m1.map(object, dto.getClass());
      }
      
}