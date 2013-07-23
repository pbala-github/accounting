package plb.accounting.common.transformation;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import javax.enterprise.inject.Produces;
import java.util.List;

/**
 * @author: pbala
 */
public class ModelMapperProducer {

    @Produces
    public ITransformationService createModelMapperTransformationService(List<PropertyMap> mappings,
                                                                         List<Converter> converters,
                                                                         List<AccountingObjectProvider> providers) {
        ModelMapper innerModelMapper = getInnerModelMapper();
        setMappings(innerModelMapper, mappings);
        setConverters(innerModelMapper, converters);
        setProviders(innerModelMapper, providers);

        return new ModelMapperTransformationService(innerModelMapper);
    }

    /**
     * @param modelMapper
     * @param mappings
     */
    private void setMappings(ModelMapper modelMapper, List<PropertyMap> mappings) {
        for (PropertyMap mapping : mappings) {
            modelMapper.addMappings(mapping);
        }
    }

    /**
     * @param modelMapper
     * @param converters
     */
    private void setConverters(ModelMapper modelMapper, List<Converter> converters) {
        for (Converter converter : converters) {
            modelMapper.addConverter(converter);
        }
    }

    /**
     * @param modelMapper
     * @param providers
     */
    private void setProviders(ModelMapper modelMapper, List<AccountingObjectProvider> providers) {
        for (AccountingObjectProvider provider : providers) {
            TypeMap typeMap = getTypeMap(modelMapper, provider.getSourceType(), provider.getDestinationType());
            typeMap.setProvider(provider);
        }
    }

    /**
     * @return
     */
    private ModelMapper getInnerModelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Assure settings consistency by initialising the mapper.
        org.modelmapper.config.Configuration config = mapper.getConfiguration();
        config.enableFieldMatching(false); // default
        config.ignoreAmbiguity(false); // ?
        config.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        config.setMatchingStrategy(MatchingStrategies.STANDARD); // default

        return mapper;
    }

    /**
     * @param modelMapper
     * @param sourceType
     * @param destinationType
     * @return
     */
    private TypeMap getTypeMap(ModelMapper modelMapper, Class<?> sourceType, Class<?> destinationType) {
        TypeMap typeMap = modelMapper.getTypeMap(sourceType, destinationType);
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(sourceType, destinationType);
        }
        return typeMap;
    }

}
