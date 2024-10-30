# pagination

데이터를 보여줄때 데이터의 수가 많으면 페이징 처리를 해서 보여준다.

mysql로는 간단히 처리할 수 있지만 servlet 을 사용해 어플리케이션 쪽으로
해결하려고 하니 간단하진 않았다.

## sql
```sql
select * 
from category
LIMIT 0, 5;
```


> totalpage = (int)Math.ceil((double)pages /(double) pageSize) ;

LIMIT offset, size 라고 생각하면 된다.
0번부터 5개를 가져온다.

## Service, repository
service에 무슨 함수를 두고 repository에 무슨함수를 둬야하는 지 몰라서
고민을 해봤다.

repository에는 메소드를 findCategoryPaging(String categoryName, int page, int pageSize)


이렇게 두고

Service에서 offset과 페이지를 처리하여
findPage(page currentPage)로 처리하였다.