# Thread 

Program 이 있으면 Thread가 하나 있을수도 있고 여러개 있을수도 있다.
회사 직원이라고 생각하면 될듯. 

### Thread 특징
독립된 Stack, PC를 가지고 Heap은 공유
Heap을 공유하기 때문에 그래서 한 프로세스 안의 쓰레드들은 주소공간, 자원을  공유할 수 있다.

## Main Thread
java programming을 해보셨다면 이미 Thread를 사용한 경험이 있는것이다.
java는 Main Thread로 시작된다. 
그리고 currentThread() 메소드를 통해 해당 쓰레드의 참조를 얻을 수 있다.


## Single Thread

thread를 따로 생성하지 않고 프로그래밍 했다면 single thread로 한거다.

작업이 순차적으로 진행되고 context switching, 동시성 제어 문제등을 고려하지 않아도 된다.

단순한 계산을 하는 프로그램이라면 single thread가 유리하다.

### single thread 단점 
요즘 컴퓨터들은 모두 multi cpu인데 그걸 활용하지 못한다.

연산량이 많은 경우 그 작업이 완료되어야 다음 작업 진행 가능 

싱글 쓰레드는 에러를 처리를 못하면 중지된다. -> 쓰레드를 자동차에 꼽는 부품정도라 생각하면
부품이 고장나도 바꿔 낄수 있는걸 생각하자.

## Multi Thread

프로그램 내에서 두 개 이상의 동작을 실행
-> cpu 활동을 극대화
하나의 프로세스가 다수의 실행단위로 구분하여 자원을 공유
-> 자원의 생성과 관리의 중복성 최소화

### Multi Thread 장점
한 쓰레드가 에러나면 다른걸로 바꿔낌

cpu 잘 사용함

### 단점
어려움
동시성제어 이게 정말 머리아프다.

그리고 context switching -> thread가 바뀌면 cpu안에 있는 정보들 다 바꿔야하느데
오래걸리겠지

개발 난이도 높음.. 

## Thread method 

interrupt() -> 발생하면 interrupted를 true로 만든다.
만약 쓰레드가 Timewait, blocked상태일 때 발생하면 InterruptedException이 발생한다.
catch해서 잡으면 Thread의 interrupted 를 false로 바꾸기 때문에 catch문에서 한번 더 써줘야한다.


nofity, notifyAll -> block상태에 있는 Thread 하나를 wait상태로 바꿈
깨우는거 누굴 깨울지모름. 깨우면 지들끼리 경쟁함. 

sleep -> Time wait 상태로 바꿈 . sleep(1000) 이면 1초다.

join -> 특정 쓰레드가 다른 쓰레드가 끝날 때 까지 기다리게할 수 있다.
t1 쓰레드에서 t2.join() 하면 t1은 t2가 끝날 때 까지 기다린다.

wait() -> wait상태로 바꿈 notify해서 깨워줘야함

yield() -> 실행 중에 우선순위가 동일하거나 높은 쓰레드를 산출하고 Wait상태로 변경
이거 참 애매함. while() 2초동안 yeild() 해봤더니 160만번이 돌아갔음
이거 써서 양보할빠엔 join 써라 

## Concurrency control

한 자원에 대해 여러 쓰레드가 접근함  -> Race condition 

Critical section -> 동시접근 허용 X 자원에 두개 이상의 쓰레드나 프로세스가 접근하는 블록코드

Mutual Exclusion 를 보장받아야함

그러기 위해 Mutex, semaphore가 있다.
semaphore는 화장실 Mutex는 똥칸이라고 생각하자.

semaphore 화장실에 여러명 들어가도 되고 한명 들어가도된다.
Mutex 똥칸 한명만 들어가라.

Fair lock , Unfair lock -> 
Fair lock -> FIFO
unFair lock -> 끝난놈이 또받음








## Process
porcess는 일반적으로 program이 실행시킨 인스턴스

보조기억장치 (HDD) 에 올라가있는 놈을 program
메모리(RAM)에 올라가서 도는놈을 process

같은 시각에 여러개의 프로그램을 실행하는 시분할 방식 -> 멀티태스킹



new -> Ready -> Running -> Terminate
       Ready <- Running
            Blocked


---
# 정리 

### **1. 멀티스레딩의 이해**

- 자바에서 멀티스레딩의 개념과 필요성을 이해한다.
  → 방법 : Thread를 하나 만들어서 start() ,
  개념 : 프로세스 내에서 실행되는 작은 단위. 프로세스는 여러 작업을 한다.
  필요성 : 카카오톡을 봐도 파일을 다운받으면서 다른 메세지를 보낼수 있어야 한다.
- 멀티스레딩의 장점과 단점을 설명할 수 있다.
  → 장점 : cpu 활동을 극대화 (경제성)
  두 개 동작을 동시에 실행 (응답성)
  → 단점 : 자원을 공유해야 하기 때문에  동기화 처리 해야하고, context switching비용 .
- 스레드의 생명 주기와 상태 변화를 이해한다.
  new → runnable → run → terminated
  blocked
  wait

blocked queue랑 wait queue랑 다르다.

### **2. 스레드 생성 및 관리**

- `Thread` 클래스와 `Runnable` 인터페이스를 사용하여 스레드를 생성하고 관리하는 방법을 학습한다.
  → Runnable interface 권장. 이유 : 재사용성이 높다.
- 스레드의 우선순위 설정과 스케줄링에 대해 이해한다.
  → 지알아서함

### **3. 동기화와 스레드 안전성**

- 동기화의 필요성과 개념을 이해한다.
- `synchronized` 키워드와 메서드, 블록을 사용하여 임계 구역을 보호하는 방법을 학습한다.
  → static method, method, block
- `ReentrantLock`과 같은 고급 동기화 도구를 사용하여 스레드 안전성을 확보하는 방법을 이해한다.
  → lock. mutex

### **4. 스레드 간 통신**

- `wait()`, `notify()`, `notifyAll()` 메서드를 사용하여 스레드 간 통신을 구현하는 방법을 학습한다.
  → wait 기다리게함 , notify 한놈 깨움, yield 양보. join 끝날때까지 기다림
- `BlockingQueue`와 같은 스레드 안전한 데이터 구조를 사용하여 스레드 간 데이터를 교환하는 방법을 이해한다.
  → 복잡하고 어려움 .


  ---
## Semaphore and Mutex

semaphore -> 여러개의 쓰레드가 접근 가능
Mutex -> 한개의 쓰레드만 접근가능 

---
## ThreadLocal
Thread 마다 독립적인 변수를 가질수 있게 해준다. 

