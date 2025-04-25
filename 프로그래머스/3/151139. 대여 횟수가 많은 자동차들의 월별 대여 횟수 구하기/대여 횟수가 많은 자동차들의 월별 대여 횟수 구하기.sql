# 내가 놓친 부분
# 1) 전체 기간 중 5회 이상 체크하는 것 (제대로 했음)
# 2) 그리고 나서 각 월별로 횟수 세는 것... (안했음..)
SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
        AND CAR_ID IN (
            # 여기서 전체 기간 중 5회 넘어가는, 조건을 충족하는 car_id를 파악해둔다
            SELECT CAR_ID
                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
                GROUP BY CAR_ID
                HAVING COUNT(*) >= 5
        )
    # 그리고 바깥에서 해당 car_id들을 다시 그룹한다, 이때 묶는 기준은 Month(start_date) 해서 월별로 묶기
    GROUP BY MONTH(START_DATE), CAR_ID
    ORDER BY MONTH, CAR_ID DESC;