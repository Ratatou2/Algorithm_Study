select info.flavor
    from ICECREAM_INFO as info
    inner join FIRST_HALF as fir
        on info.flavor = fir.flavor
    where 3000 <= fir.total_order
        and info.INGREDIENT_TYPE = "fruit_based"
    
# select *
#     from FIRST_HALF 
#     where 2500 < total_order
        