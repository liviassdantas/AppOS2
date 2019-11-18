using AppOSWebApi;
using AppOSWebApi.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class OrdemServico :BaseModel
    {
        public Int64 Num_OS { get; set; }
        public ProdutosModels Produto { get; set; }
        public Empresa Empresa { get; set; }
        public ClienteModels ClienteResp { get; set; }
        public DateTime Data_Agendamento { get; set; }
        public String Descricao_Problema { get; set; }
        public String Observacao_Produto { get; set; }
        public float Valor_Servico { get; set; }
        public String Tecnico_Resp { get; set; }

        public int Status { get; set; }

      
    }

    public class OrdemServicoCadastro
    {
        public ProdutosModels Produto { get; set; }
        public String CPFCNPJ_Empresa { get; set; }
        public Int64 NumOS { get; set; }
        public String Descricao_problema { get; set; }
        public String Observacao_produto { get; set; }
        public float Valor_Servico { get; set; }
        public String Tecnico_Resp { get; set; }
        public int status { get; set; }
        public DateTime Data_Agendamento { get; set; }
        public ClienteModels ClienteResp { get; set; }
    }


    public class OrdemServicoParam
    {
       public Int64? Num_OS { get; set; }
       public String CPFCNPJ_Empresa { get; set; }
       public int status { get; set; }
    }

}