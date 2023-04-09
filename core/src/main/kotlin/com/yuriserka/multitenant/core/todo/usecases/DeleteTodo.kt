package com.yuriserka.multitenant.core.todo.usecases

interface DeleteTodo {
    fun execute(id: String)
}