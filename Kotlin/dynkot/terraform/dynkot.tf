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

//  global_secondary_index = [
//    {
//      name = "TagsIndex"
//      hash_key = "name"
//      range_key = "tags"
//      non_key_attributes = ["name"]
//      projection_type = "INCLUDE"
//    }
//  ]
}

