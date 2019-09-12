using AppOSWebApi.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class EmpresaModels: BaseModel
    {
        public String CPFCNPJ { get; set; }
        public EnderecoModels Endereco { get; set; }
        public String Telefone { get; set; }
        public DateTime Data_Ultima_Atualizacao { get; set; }
        public String Email { get;set; }
        public EmpresaLoginModels Login { get; set; }

    }

    public class EmpresaLoginModels
    {
        public String Senha { get; set; }
        public DateTime Ultimo_Acesso { get; set; }
    }
}