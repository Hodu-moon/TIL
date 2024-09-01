public class MVGEnumerator implements Enumerator<Person> {
    
    Person vip1;
    Person vip2;
    Person vip3;

    int index = 0;

    MVGEnumerator(Person vip1, Person vip2 , Person vip3){
        this.vip1 =vip1;
        this.vip2 = vip2;
        this.vip3 = vip3;
    }
    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return index != 3;
    }

    @Override
    public Person next() {

        Person vip = null;;
        switch (index++) {
            case 0:
                vip = vip1;
                break;
            case 1:
                vip = vip2;
                break;
            case 2:
                vip = vip3;
                break;             
        }

        return vip;
    }
}
