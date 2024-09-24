# Thread pool

thread pool은 여러개의 쓰레드를 만들어두고 요청이 들어올 때 미리 만들어둔 쓰레드에 작업을 할당하고 여분 쓰레드가 없다면
block상태에 있다가 작업이 들어오면 다시 실행시킨다. 작업이 없다면 쉰다. 

이미 실행중인 쓰레드에 start()를 하면 IllegalThreadStateException 이 생겨 
thread safety하게 실행되어야한다. 

stop도 마찬가지다. 