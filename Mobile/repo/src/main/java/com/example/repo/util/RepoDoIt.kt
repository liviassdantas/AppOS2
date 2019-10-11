package com.example.repo.util

interface RepoDoIt<T: List<*>>{
    fun servidor():T
    fun local():T
    fun refreshLocal(value:T)
}