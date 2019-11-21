using AppOSWebApi.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class Empresa: BaseModel
    {
        public String cpfcnpj { get; set; }
        public String Nome { get; set; }
        public EnderecoModels Endereco { get; set; }
        public String Telefone { get; set; }
        public String Data_Ultima_Atualizacao { get; set; }
        public String Email { get;set; }
        public EmpresaLoginModels Login { get; set; }

    }

    public class EmpresaLoginModels
    {
        public String Senha { get; set; }
        public DateTime Ultimo_Acesso { get; set; }
        public String Usuario { get; set; }
        public String Email { get; set; }
    }


    public class EmpresaEsqueciSenha
    {
        public String CPFCNPJ { get; set; }
        public String NomeRazaoSocial { get; set; }
        public String Email { get; set; }
    }


    public class EmpresaSenha
    {
        public String CPFCNPJ { get; set; }
        public String Senha_Antiga { get; set; }
        public String Senha_Nova { get; set; }
    }

}