using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    enum StatusEnum
    {
        [Description("AGUARDANDO_INICIO")]
        AGUARDANDO_INICIO = 0,
        [Description("AGUARDANDO_MATERIAL")]
        AGUARDANDO_MATERIAL = 1,
        [Description("EM_DESENVOLVIMENTO")]
        EM_DESENVOLVIMENTO = 2,
        [Description("AGUARDANDO_LIBERACAO")]
        AGUARDANDDO_LIBERACAO = 3,
        [Description("DISPONIVEL_RETIRADA")]
        DISPONIVEL_RETIRADA = 4


       
    }
}