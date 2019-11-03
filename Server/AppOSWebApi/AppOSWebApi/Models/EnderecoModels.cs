using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{

      public class EnderecoModels
    {
        public String Logradouro { get; set; }
        public String num_residencia { get; set; }
        public String Complemento { get; set; }
        public String CEP { get; set; }
        public String Localidade { get; set; }
        public String UF { get; set; }
    }
}