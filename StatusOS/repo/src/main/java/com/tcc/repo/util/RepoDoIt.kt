package com.example.repo.util

interface RepoDoIt<T: Any>{
    fun servidor():T
    fun local():T
    fun refreshLocal(value:T)
}