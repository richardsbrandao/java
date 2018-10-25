docker run -d -p 80:8080 \
    -e cloud.aws.credentials.accessKey=XXX \
    -e cloud.aws.credentials.secretKey=XXX \
    -e AWS_ACCESS_KEY_ID=XXX \
    -e AWS_SECRET_ACCESS_KEY=XXX \
    richardbrandao/estudos:sns-sqs-example