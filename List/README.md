# List

List는 순서가 있는 자료구조이다. 
Integer index를 통해 접근할 수 있기 때문에 사용자는 정확하게 조정해야한다. 

Set과 다르게 list는 중복된 값을 허용한다.

javadoc에서 add, remove Optional operation이라고 나와있다.  -> 구현 안하고 throws 해도된다. 

## ADT

boolean add(E e)
void add(int index, E element)
void clear()
boolean contains(Object o)
E get(int index)
boolean isEmpty()
E remove(int index)
E set(int index, E element)
int size()

### ArrayList
java에서  ArrayList -> Vector로 구현

배열을 사용하기 때문에 인덱스를 활용한 검색과 수정에는 빠른 속도가 난다.

반면 삽입, 삭제 가 일어나면 배열 요소를 이동시켜야 하기 때문에 시간을 더 소모한다.

인덱스를 이용한 검색 O()
Worst : O(1)
Best : O(1)
Average : O(1)

삽입 O()
Worst : O(n) -> 첫번째 삽입
Best : O(1) -> 마지막 삽입
Average : O(n) -> 가운데 추가되고 나머지 것들 이동

삭제 O()
Worst : O(n) -> 첫번째 삭제
Best : O (1) -> 마지막 삭제
Average : O(n) -> 중간

### LinkedList
Singly로 구현하였다.

검색할 땐 순차적으로 검색해야 해서 속도가 느리지만 
삽입 삭제 할땐 빠른 속도를 보여주었다. 


검색 
Worst : O(N)
Best : O(1)
Average : O( N)

검색하는 로직은 빼고 계산했다.

삽입
Best : O(1)
Worst : O(1)
Average : O(1)

삭제
Best : O(1)
Worst : O(1)
Average : O(1)


## 장단점

데이터를 저장하고 검색하는을 자주하는 데이터구조에선 ArrayList를 쓰는게 유리하고
데이터의 삽입 삭제가 자주 일어나는 작업에선 LinkedList가 유리했다.


### 몰랐던 부분 
자바에서 ArrayList를 구현할 때 add(int index, E element)를 
arrayCopy로 복사해서 사용하는 것이었다.

Label 구문 -> 여기선 행동이 똑같은 가독성을 위해 Label구문을 사용한것같다. 

~~~java

public boolean remove(Object o) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found: {
            if (o == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }
~~~


