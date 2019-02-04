package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.CategoryWarna;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryWarnaMapperRequestNew extends ObjectMapper<CategoryWarna, CategoryWarnaDTO.CategoriWarnaRequestNewDTO> {

    CategoryWarnaMapperRequestNew converter = Mappers.getMapper(CategoryWarnaMapperRequestNew.class);

}
