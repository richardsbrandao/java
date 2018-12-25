#!/bin/bash

# $ sudo apt-get install apache2-utils

TYPE=$1 # SHOULD BE bank-sync|bank-async|credit-card|account
OUTPUT="$2.out"

ab_test_report() {
    URL=$1
    execute_test 1 ${URL}
    execute_test 5 ${URL}
    execute_test 10 ${URL}
    execute_test 20 ${URL}
    execute_test 50 ${URL}
    execute_test 100 ${URL}
    execute_test 500 ${URL}
    execute_test 1000 ${URL}
}

write_test_head_in_output() {
    MESSAGE="$1"

    echo "____________________________________________________________" >> ${OUTPUT}
    echo ${MESSAGE} >> ${OUTPUT}
    echo "____________________________________________________________" >> ${OUTPUT}
}

execute_test() {
    THREADS=$1
    URL=$2
    MESSAGE="TEST with ${THREADS} threads on ${URL}"

    echo ${MESSAGE}
    write_test_head_in_output "${MESSAGE}"
    ab -n 10000 -c ${THREADS} ${URL} >> ${OUTPUT}

    sleep 1
}

if [ "$TYPE" = "account" ]
then
    ab_test_report "http://localhost:8081/account/1"
elif [ "$TYPE" = "credit-card" ]
then
    ab_test_report "http://localhost:8082/credit-card/1"
elif [ "$TYPE" = "bank-sync" ]
then
    ab_test_report "http://localhost:8080/sync/bank-report/1"
elif [ "$TYPE" = "bank-async" ]
then
    ab_test_report "http://localhost:8080/async/bank-report/1"
fi
