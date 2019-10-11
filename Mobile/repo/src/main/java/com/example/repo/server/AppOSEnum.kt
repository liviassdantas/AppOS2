package com.example.repo.server

import connectivity.util.Calls

enum class AppOSEnum constructor(private val theState:String):Calls {
    exemplo("");

    override fun toString(): String {
        return theState
    }
}