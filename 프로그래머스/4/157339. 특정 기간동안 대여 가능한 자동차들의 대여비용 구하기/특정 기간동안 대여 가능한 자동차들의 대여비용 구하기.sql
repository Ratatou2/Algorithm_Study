SELECT *
    FROM (
        SELECT CAR.CAR_ID,
               CAR.CAR_TYPE, 
               ROUND(((DAILY_FEE * (100 - PLAN.DISCOUNT_RATE)) / 100), 0) * 30 AS FEE
            FROM CAR_RENTAL_COMPANY_CAR AS CAR
            INNER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS PLAN
                ON CAR.CAR_TYPE = PLAN.CAR_TYPE
            WHERE PLAN.CAR_TYPE IN ("SUV", "세단")
                AND PLAN.DURATION_TYPE = "30일 이상"
                AND NOT EXISTS (
                    SELECT 1
                        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS H
                        WHERE H.CAR_ID = CAR.CAR_ID
                            AND not (H.END_DATE < '2022-11-01' or '2022-11-30' < H.START_DATE)
                )
        ) AS RESULT 
    WHERE 500000 <= FEE AND FEE < 2000000
    ORDER BY FEE DESC, CAR_TYPE, CAR_ID DESC