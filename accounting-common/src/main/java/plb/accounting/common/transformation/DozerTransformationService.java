package plb.accounting.common.transformation;

import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 10:19 AM
 */
public class DozerTransformationService implements ITransformationService{

    /**
     *
     */
    private Mapper mapper;

    @Override
    public <T> T transform(Object srcObject, Class<T> targetClass) {
        return  mapper.map(srcObject,targetClass);
    }

    @Override
    public <T> List<T> transform(Collection<?> srcCollection, Class<T> targetClass) {

        List<T> results = new ArrayList<T>();

        for(Object o : srcCollection)
            results.add((T)transform(o,targetClass));

        return results;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
