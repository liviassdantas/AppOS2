using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class ClienteModels
    {
        public String cpf_cnpj { get; set; }
        public String Nome { get; set; }
        public DateTime Data_Nasc { get; set; }
        public EnderecoModels Endereco { get; set; }
        public String Telefone { get; set; }
        public String Email { get; set; }


        
    }
}