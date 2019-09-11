using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class ProdutosModels
    {
        public String Nome { get; set; }
        public String Marca { get; set; }
        public ClienteModels Cliente { get; set; }
    }
}