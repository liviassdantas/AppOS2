using AppOSWebApi;
using AppOSWebApi.Core;
using MongoDB.Bson;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class OrdemServico : BaseModel
    {
        public Int64 num_os { get; set; }
        public ObjectId cliente_responsavel { get; set; }
        public ProdutosModels Produto { get; set; }
        public ObjectId Empresa { get; set; }
        
        public String data_agendamento { get; set; }
        public String descricao_problema { get; set; }
        public String observacao_produto { get; set; }
        public float valor_servico { get; set; }
        public String tecnicoResp { get; set; }

        public int status_os { get; set; }


    }


    public class OrdemServicoModel
    {

        public Int64 num_os { get; set; }
        public ClienteModels cliente_responsavel { get; set; }
        public ProdutosModels Produto { get; set; }
        public String data_agendamento { get; set; }
        public String descricao_problema { get; set; }
        public String observacao_produto { get; set; }
        public Double valor_servico { get; set; }
        public String tecnicoResp { get; set; }

        public int status_os { get; set; }

    }

    public class OrdemServicoCadastro
    {

        public Int64 num_os { get; set; }

        public ClienteModels cliente_responsavel { get; set; }

        public ProdutosModels Produto { get; set; }
        public String cpfCnpj { get; set; }
        public String data_agendamento { get; set; }

        public String descricao_problema { get; set; }
        public String observacao_produto { get; set; }
        public float valor_servico { get; set; }
        public String tecnicoResp { get; set; }
        public int status_os { get; set; }
    }


    public class OrdemServicoParam
    {
        public Int64? Num_OS { get; set; }
        public String CPFCNPJ_Empresa { get; set; }
        public int status { get; set; }
    }

}