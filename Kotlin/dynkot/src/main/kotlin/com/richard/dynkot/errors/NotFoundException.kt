package com.richard.dynkot.errors

class NotFoundException : Throwable {
    constructor(email : String) : super("Entity with id $email not found")
}
