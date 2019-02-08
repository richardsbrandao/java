module "user_dynamo_db_table" {
  source = "modules/dynamo"

  environment = "${terraform.workspace}"
  table_name = "user"
  billing_mode = "PAY_PER_REQUEST"
  hash_key = "email"
  ttl_enabled = true
  attributes = [
    {
      name = "email"
      type = "S"
    }
  ]
}

module "course_dynamo_db_table" {
  source = "modules/dynamo"

  environment = "${terraform.workspace}"
  table_name = "course"
  billing_mode = "PAY_PER_REQUEST"
  hash_key = "name"
  ttl_enabled = false
  attributes = [
    {
      name = "name"
      type = "S"
    }
  ]
}

module "subscription_dynamo_db_table" {
  source = "modules/dynamo"

  environment = "${terraform.workspace}"
  table_name = "subscription"
  billing_mode = "PAY_PER_REQUEST"
  hash_key = "id"
  ttl_enabled = true

  attributes = [
    {
      name = "id",
      type = "S"
    },
    {
      name = "course_name",
      type = "S"
    },
    {
      name = "user_email",
      type = "S"
    },
    {
      name = "course_tag",
      type = "S"
    }
  ]

  global_secondary_index = [
    {
      name = "UserEmailByCourseNameIndex",
      hash_key = "user_email",
      range_key = "course_name",
      projection_type = "ALL"
    },
    {
      name = "CourseNameByUserEmailIndex",
      hash_key = "course_name",
      range_key = "user_email",
      projection_type = "ALL"
    },
    {
      name = "CourseTagIndex",
      hash_key = "course_tag",
      projection_type = "ALL"
    },
    {
      name = "UserEmailByTagIndex",
      hash_key = "user_email",
      range_key = "course_tag",
      projection_type = "ALL"
    }
  ]
}

