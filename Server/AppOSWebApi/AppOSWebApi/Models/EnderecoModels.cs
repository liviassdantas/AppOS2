using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class EnderecoModels
    {
        public String Rua { get; set; }
        public String Numero { get; set; }
        public String Complemento { get; set; }
        public String CEP { get; set; }
        public String Cidade { get; set; }
        public String UF { get; set; }
    }
}