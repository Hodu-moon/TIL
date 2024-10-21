package filter;

import request.Request;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}