package com.example.data.entity

enum class StatusEnum(val value: Int) {
    Aguardando_Inicio(0) {
        override fun statusToString(): String {
            return "Aguardando início do projeto ou serviço"
        }
    },
    Aguardando_Materiais(1) {
        override fun statusToString(): String {
            return "Aguardando a chegada de materiais necessários"
        }
    },
    Em_Desenvolvimento(2) {
        override fun statusToString(): String {
            return "Serviço em desenvolvimento"
        }
    },
    Aguardando_Liberacao(3) {
        override fun statusToString(): String {
        return "Aguardando liberação do serviço "
        }
    },
    Retirada_Disponivel(4) {
        override fun statusToString(): String {
            return "Serviço concluído. Aguardando a retirada do produto."
        }
    };

    abstract fun statusToString(): String
}
