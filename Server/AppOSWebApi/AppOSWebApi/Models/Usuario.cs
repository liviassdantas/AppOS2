using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class Usuario
    {
        private String CPFCNPJ  { get; set; }
        private String Senha { get; set; }
        private DateTime ultimoLogin { get; set; }
        private Int64 idUsuario { get; set; }
    }
}