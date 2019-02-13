variable "environment" {}
variable "table_name" {}
variable "ttl_enabled" {}
variable "billing_mode" {}
variable "hash_key" {}
variable "global_secondary_index" {
  type = "list"
  default = []
}
variable "attributes" {
  type = "list"
}