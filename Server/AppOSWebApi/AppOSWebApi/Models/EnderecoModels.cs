using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{

      public class EnderecoModels
    {
        public String cep { get; set; }

        public String Logradouro { get; set; }
        public String uf { get; set; }
        public String Localidade { get; set; }
        public String Bairro { get; set; }
        public String num_residencia { get; set; }
        public String Complemento { get; set; }
    }
}