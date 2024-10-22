# Where과 Having의 차이를 잘 알아야 쉽게 풀 수 있다
# Where은 그룹화 이전에, 각 행(row) 단위로 조건을 필터링할 때 사용
# Having은 Group으로 묶은 다음에 결과에 대한 조건을 적용할 때 사용한다

select user_id, product_id
    from ONLINE_SALE 
    group by user_id, product_id
    having 2 <= count(*)
    order by user_id, product_id desc
