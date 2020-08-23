#!/bin/bash
no_sql_total_query_time_by_rating=0
no_sql_total_quert_time_by_scan=0
no_sql_total_query_time_by_category_and_created_date=0
no_sql_total_query_time_by_date=0
no_sql_total_query_time_by_price=0
no_sql_total_query_time_by_category_and_price=0
no_sql_total_query_time_by_category=0

times=20

for i in `seq 1 $times`; do 

	no_sql_response_time_by_category_and_created_date=`curl localhost:8080/database/measures | jq '.measures | .[0]."no_sql_query_time"'`
	no_sql_total_query_time_by_category_and_created_date=`expr $no_sql_total_query_time_by_category_and_created_date + $no_sql_response_time_by_category_and_created_date`
	
	no_sql_response_time_by_scan=`curl localhost:8080/database/measures | jq '.measures | .[1]."no_sql_query_time"'`
	no_sql_total_quert_time_by_scan=`expr $no_sql_response_time_by_scan + $no_sql_total_quert_time_by_scan`

	no_sql_response_time_by_date=`curl localhost:8080/database/measures | jq '.measures | .[2]."no_sql_query_time"'`
	no_sql_total_query_time_by_date=`expr $no_sql_total_query_time_by_date + $no_sql_response_time_by_date`

	no_sql_response_time_by_price=`curl localhost:8080/database/measures | jq '.measures | .[3]."no_sql_query_time"'`
	no_sql_total_query_time_by_price=`expr $no_sql_total_query_time_by_price + $no_sql_response_time_by_price`

	no_sql_response_time_by_category_and_price=`curl localhost:8080/database/measures | jq '.measures | .[4]."no_sql_query_time"'`
	no_sql_total_query_time_by_category_and_price=`expr $no_sql_total_query_time_by_category_and_price + $no_sql_response_time_by_category_and_price`

	no_sql_response_time_by_category=`curl localhost:8080/database/measures | jq '.measures | .[5]."no_sql_query_time"'`
	no_sql_total_query_time_by_category=`expr $no_sql_total_query_time_by_category + $no_sql_response_time_by_category`

	no_sql_response_time_by_rating=`curl localhost:8080/database/measures | jq '.measures | .[6]."no_sql_query_time"'`
	no_sql_total_query_time_by_rating=`expr $no_sql_response_time_by_rating + $no_sql_total_query_time_by_rating`

done


no_sql_avg_by_category_and_created_date=`echo "$no_sql_total_query_time_by_category_and_created_date / $times" | bc -l`
no_sql_avg_by_scan=`echo "$no_sql_total_quert_time_by_scan / $times" | bc -l` 
no_sql_avg_by_date=`echo "$no_sql_total_query_time_by_date / $times" | bc -l`
no_sql_avg_by_price=`echo "$no_sql_total_query_time_by_price / $times" | bc -l`
no_sql_avg_by_category_and_price=`echo "$no_sql_total_query_time_by_category_and_price / $times" | bc -l`
no_sql_avg_by_category=`echo "$no_sql_total_query_time_by_category / $times" | bc -l`
no_sql_avg_by_rating=`echo "$no_sql_total_query_time_by_rating / $times" | bc -l` 

echo "By category and created date:[no-sql: $no_sql_avg_by_category_and_created_date]"
echo "Scan:[no-sql: $no_sql_avg_by_scan]"
echo "By date:[no-sql: $no_sql_avg_by_date]"
echo "By price:[no-sql: $no_sql_avg_by_price]"
echo "By category and price:[no-sql: $no_sql_avg_by_category_and_price]"
echo "By category:[no-sql: $no_sql_avg_by_category]"
echo "By rating:[no-sql: $no_sql_avg_by_rating]"
