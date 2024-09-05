import java.lang.annotation.Annotation;
import java.util.*;

public class DataBeanMapFactory {
    static Map<String, DataBean> map = new HashMap<>();

    static void registerBean(DataBean bean){
        if(!map.containsKey(bean.getClass().getSimpleName())){
            map.put(bean.getClass().getSimpleName(), bean);
        }

    }

    static DataBean getBean(String name){
        DataBean bean = map.get(name);
        if(bean == null){
            return null;
        }

        Annotation[] annotations = bean.getClass().getAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof CreationType){
                if(((CreationType)annotation).type().equals(CreationTypeEnum.Singleton)){
                    return bean;
                }else if(((CreationType)annotation).type().equals(CreationTypeEnum.Prototype) ){
                    return bean.clone();
                }

            }
        }

        
        return null;

    }

}
