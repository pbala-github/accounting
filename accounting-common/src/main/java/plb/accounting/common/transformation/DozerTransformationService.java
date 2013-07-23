package plb.accounting.common.transformation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Collection;
import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 10:19 AM
 */
@ApplicationScoped
@Alternative
public class DozerTransformationService implements ITransformationService {

//    /**
//     *
//     */
//    private Mapper mapper;
//
//    @Override
//    public <T> T transform(Object srcObject, Class<T> targetClass) {
//        return mapper.map(srcObject, targetClass);
//    }
//
//    @Override
//    public <T> List<T> transform(Collection<?> srcCollection, Class<T> targetClass) {
//
//        List<T> results = new ArrayList<T>();
//
//        for (Object o : srcCollection)
//            results.add((T) transform(o, targetClass));
//
//        return results;
//    }
//
//    public void setMapper(Mapper mapper) {
//        this.mapper = mapper;
//    }
//
//    @PostConstruct
//    private synchronized void init() {
//        if (mapper == null) {
//            mapper = DozerBeanMapperSingletonWrapper.getInstance();
//            ((DozerBeanMapper) mapper).setMappingFiles(Arrays.asList("config/dozer/dozerBeanMapping.xml"));
//        }
//
//        new BeanMappingBuilder(){
//
//            @Override
//            protected void configure() {
//                mapping()
//            }
//        }  ;
//    }

    @Override
    public <T> T transform(Object srcObject, Class<T> targetClass) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> List<T> transform(Collection<?> srcCollection, Class<T> targetClass) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
