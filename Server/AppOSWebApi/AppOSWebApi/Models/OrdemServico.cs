using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class OrdemServico
    {
        public String numOS { get; set; }
        public Produto Produto { get; set; }
        public Empresa Empresa { get; set; }
        public DateTime DataAgendamento { get; set; }
        public String DescricaoProblema { get; set; }
        public float ValorServico { get; set; }
        public String RecnicoResp { get; set; }
        public StatusEnum statusOS { get; set; }
    }
}