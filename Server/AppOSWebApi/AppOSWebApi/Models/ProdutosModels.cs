using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class ProdutosModels
    {
        public String Descricao { get; set; }
        public String Marca { get; set; }
        public ClienteModels ClienteResp { get; set; }
    }
}