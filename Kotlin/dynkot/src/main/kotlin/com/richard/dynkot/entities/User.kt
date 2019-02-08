package com.richard.dynkot.entities

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.springframework.data.annotation.Id

@DynamoDBTable(tableName = "dev-user")
data class User(
        @get:DynamoDBHashKey(attributeName = "email")
        val email: String,
        @get:DynamoDBAttribute(attributeName = "name")
        val name : String
) {
    @Id
    private var id: UserId? = null
        get() {
            return UserId(email)
        }
}


data class UserId(
        @DynamoDBHashKey
        var email: String? = null
)