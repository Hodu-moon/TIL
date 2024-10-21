package response;

import request.Request;

public class NotFoundResponse implements Response{
    @Override
    public void doResponse(Request requset) {
        System.out.println("존재 하지 않음 404 ");

    }
}
