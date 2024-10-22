select user_id, product_id
    from ONLINE_SALE 
    group by user_id, product_id
    having 2 <= count(*)
    order by user_id, product_id desc
