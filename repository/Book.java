
@CreationType(type = CreationTypeEnum.Prototype)
public class Book implements DataBean{

    Book(){}

    public DataBean clone()   {
        return new Book();
    }
}