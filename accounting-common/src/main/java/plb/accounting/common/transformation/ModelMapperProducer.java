package plb.accounting.common.transformation;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Iterator;

/**
 * @author: pbala
 */
@ApplicationScoped
public class ModelMapperProducer {

    @Inject
    private Instance<PropertyMap> propertyMaps;

    @Inject
    private Instance<Converter> converters;

    @Inject
    private Instance<AccountingObjectProvider> objectProviders;

    @Produces
    public ITransformationService createModelMapperTransformationService() {
        ModelMapper innerModelMapper = getInnerModelMapper();
        setMappings(innerModelMapper, propertyMaps.iterator());
        setConverters(innerModelMapper, converters.iterator());
        setProviders(innerModelMapper, objectProviders.iterator());

        return new ModelMapperTransformationService(innerModelMapper);
    }

    /**
     * @param modelMapper
     * @param mappings
     */
    private void setMappings(ModelMapper modelMapper, Iterator<PropertyMap> mappings) {
        while (mappings.hasNext()) {
            modelMapper.addMappings(mappings.next());
        }
    }

    /**
     * @param modelMapper
     * @param converters
     */
    private void setConverters(ModelMapper modelMapper, Iterator<Converter> converters) {
        while (converters.hasNext()) {
            modelMapper.addConverter(converters.next());
        }
    }

    /**
     * @param modelMapper
     * @param providers
     */
    private void setProviders(ModelMapper modelMapper, Iterator<AccountingObjectProvider> providers) {
        while (providers.hasNext()) {
            AccountingObjectProvider provider = providers.next();
            for (Class aClass : provider.getSourceTypes()) {
                for (Class bClass : provider.getDestinationTypes()) {
                    TypeMap typeMap = getTypeMap(modelMapper, aClass, bClass);
                    typeMap.setProvider(provider);
                }
            }
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
