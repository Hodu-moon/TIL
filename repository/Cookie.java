@CreationType(type = CreationTypeEnum.Singleton)
public class Cookie implements DataBean {
    
    Cookie(){}

    public DataBean clone(){
        return new Cookie();
    }

}
