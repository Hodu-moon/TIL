public class RequestHandler implements Runnable{

    RequestChannel requestChannel;
    RequestHandler(RequestChannel requestChannel){
        this.requestChannel = requestChannel;
    }

    @Override
    public void run() {

        while(!Thread.interrupted()){
            Excutable excutable = requestChannel.getJob();
            excutable.excute();
        }
    }
}
