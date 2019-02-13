resource "aws_dynamodb_table" "dynamo_table" {
  name = "${var.environment}-${var.table_name}"
  billing_mode = "${var.billing_mode}"
  hash_key = "${var.hash_key}"

  attribute = "${var.attributes}"

  ttl {
    attribute_name = "expiration"
    enabled = "${var.ttl_enabled}"
  }

  global_secondary_index = "${var.global_secondary_index}"

  tags {
    Name = "${var.environment}-${var.table_name}"
    Environment = "${var.environment}"
  }
}