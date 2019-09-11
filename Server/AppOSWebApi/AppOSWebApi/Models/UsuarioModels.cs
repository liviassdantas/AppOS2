using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class UsuarioModels
    {
        public EmpresaLoginModels Empresa { get; set; }
        public DateTime Ultimo_Login { get; set; }

    }
}