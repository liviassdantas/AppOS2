using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class Endereco
    {
        public Int64 IdEndereco { get; set; }
        public String Rua { get; set; }
        public Int32 Numero { get; set; }
        public String Complemento { get; set; }
        public String Cep { get; set; }
        public String UF { get; set; }
    }
}