package filter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import request.Request;
import response.AdminPageResponse;
import response.MyPageResponse;
import response.NotFoundResponse;
import response.OrderResponse;

public class FilterChain {
    private List<Filter> filters = new LinkedList<>();
    private Iterator iterator;

    public void addFilter(Filter filter){
        this.filters.add(filter);
        iterator = filters.iterator();
    }

    public void doFilter(Request request){

        if(iterator.hasNext()){
            Filter nextFilter = (Filter) iterator.next();
            nextFilter.doFilter(request,this);
        }else{

            //요청 결과값 출력
            if(request.getPath().equals("/mypage")){
                new MyPageResponse().doResponse(request);
            }else if(request.getPath().equals("/admin")){
                new AdminPageResponse().doResponse(request);
            }else if(request.getPath().equals("/order")){
                new OrderResponse().doResponse(request);
            }else{
                new NotFoundResponse().doResponse(request);
            }

        }
    }

}