using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class ClienteModels
    {
        public String CPF_CNPJ { get; set; }
        public String Nome { get; set; }
        public EnderecoModels Endereco { get; set; }
        public String Telefone { get; set; }

        
    }
}