using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public enum StatusEnum
    {
        [Description("AGUARDANDO AVALIAÇÃO TECNICO")]
        AGUARDANDO_AVALIAÇÃO_TECNICO = 0,
        [Description("AGUARDANDO MATERIAL")]
        AGUARDANDO_MATERIAL = 1,
        [Description("EM DESENVOLVIMENTO")]
        EM_DESENVOLVIMENTO = 2,
        [Description("AGUARDANDO APROVAÇÃO")]
        AGUARDANDDO_APROVACAO = 3,
        [Description("DISPONIVEL RETIRADA")]
        DISPONIVEL_RETIRADA = 4
    }
}