using AppOSWebApi.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class ClienteModels : BaseModel
    {
        public String cpf_cnpj { get; set; }
        public String nome { get; set; }
        public String data_nasc { get; set; }
        public EnderecoModels endereco { get; set; }
        public String telefone { get; set; }
        public String email { get; set; }


        
    }
}