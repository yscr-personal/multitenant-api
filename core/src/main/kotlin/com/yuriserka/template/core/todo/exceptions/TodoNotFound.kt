package com.yuriserka.template.core.todo.exceptions

import com.yuriserka.template.core.exceptions.BusinessException

class TodoNotFound(val id: String) :
    BusinessException.Error(BusinessException.ErrorType.TODO_NOT_FOUND, "Todo $id not found")
