package com.example.repo.util

import Util.Coalesce

internal class Repository<T : List<*>>(val repoDoIt: RepoDoIt<T>) {

    fun refresh(): T {
        repoDoIt.refreshLocal(repoDoIt.servidor())
        return repoDoIt.local()
    }


    fun get(): T {
        var dado = repoDoIt.local()
        if(Coalesce(true,(dado as List<*>?)?.isEmpty())){
            dado = this.refresh()
        }
        return dado
    }



}