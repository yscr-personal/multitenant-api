package com.yuriserka.multitenant.core.todo.exceptions

import com.yuriserka.multitenant.core.exceptions.BusinessException

class TodoNotFound(val id: String) :
    BusinessException.Error(BusinessException.ErrorType.TODO_NOT_FOUND, "Todo $id not found")
