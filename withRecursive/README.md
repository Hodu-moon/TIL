# With recursive

상품을 데이터베이스로 구현하던 중 카테고리를 생각하게 되었다.

결론은 카테고리는 부모 카테고리를 가지고 세분화 시킬수 있다는 것이다.

## sql
```sql

create table category(
                         id int auto_increment primary key COMMENT 'id',
                         parent_id int COMMENT '부모 Id',
                         name varchar(30) COMMENT '카테고리 이름',

                         foreign key (parent_id) references category(id) ON DELETE CASCADE

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리';

insert into category(parent_id, name) values(null, 'menu');
insert into category(parent_id, name) values
                                          (1, '키보드'),
                                          (1, '마우스'),
                                          (1, '모니터'),
                                          (1, 'cpu'),
                                          (1, '쿨러'),
                                          (1, '램'),
                                          (1, 'SSD'),
                                          (1, 'HDD'),
                                          (1, '그래픽카드'),
                                          (1, '파워');

with recursive t3 id, t3.parent_id, t3.name  AS (
    select t1.id, t1.parent_id, t1.name
    from category t1
    where t1.parent_id = null

    union all
    
    select t2.id, t2.parent_id, t2.name
    from category t2
    inner join t3 on t2.parent_id = t3.id
    )

select * from t3;
    
```