public class MVG  implements Enumeratable<Person>{
    Person vip1;
    Person vip2;
    Person vip3;

    MVG(Person vip1, Person vip2 , Person vip3){
        this.vip1 =vip1;
        this.vip2 = vip2;
        this.vip3 = vip3;

    }


    
    @Override
    public Enumerator<Person> enumerator(){
        return new MVGEnumerator(vip1, vip2, vip3);
    } 


}
