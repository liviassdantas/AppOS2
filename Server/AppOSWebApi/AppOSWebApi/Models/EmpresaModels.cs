using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class EmpresaModels
    {
        public String CPFCNPJ { get; set; }
        public EnderecoModels Endereco { get; set; } 
        public String Telefone { get; set; }
        public DateTime Data_Ultima_Atualizacao { get; set; }
        
    }

    public class EmpresaLoginModels
    {
        public String CPFCNPJ { get; set; }
        public String Senha { get; set; }
    }
}