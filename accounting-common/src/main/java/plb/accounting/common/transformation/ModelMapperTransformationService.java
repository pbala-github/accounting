package plb.accounting.common.transformation;

import org.modelmapper.ModelMapper;
import plb.accounting.common.injection.Accounting;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: pbala
 */
@ApplicationScoped
public class ModelMapperTransformationService implements ITransformationService {

    private ModelMapper mapper;

    public ModelMapperTransformationService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T transform(Object srcObject, Class<T> targetClass) {
        return mapper.map(srcObject, targetClass);
    }

    @Override
    public <T> List<T> transform(Collection<?> srcCollection, Class<T> targetClass) {
        List<T> results = new ArrayList<T>();
        for (Object o : srcCollection) {
            results.add(transform(o, targetClass));
        }

        return results;
    }

}
