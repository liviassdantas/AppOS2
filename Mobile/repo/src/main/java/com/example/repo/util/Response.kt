package com.example.repo.util

import java.lang.Exception

class ResponseViewModel<T> {
    var id:Int? = null
    var result:Boolean? = null
    var mensagem:String?= null
    var exception:Throwable? = null
    var objeto : T? = null
}

class ResponseServidor<T> {
    var result:Boolean? = null
    var mensagem:String?= null
    var exception:String? = null
    var objeto : T? = null
}