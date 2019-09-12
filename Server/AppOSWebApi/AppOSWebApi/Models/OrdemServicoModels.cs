using AppOSWebApi;
using AppOSWebApi.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class OrdemServicoModels :BaseModel
    {
        public String Num_OS { get; set; }
        public ProdutosModels Produto { get; set; }
        public EmpresaModels Empresa { get; set; }
       
        public DateTime Data_Agendamento { get; set; }
        public String Descricao_Problema { get; set; }
        public String Observacao_Produto { get; set; }
        public float Valor_Servico { get; set; }
        public String Tecnico_Resp { get; set; }

        private StatusEnum Status { get; set; }

      
    }
}