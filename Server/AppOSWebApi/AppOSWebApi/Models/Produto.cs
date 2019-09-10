using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class Produto
    {
        public String Nome { get; set; }
        public String Marca { get; set; }
        public Cliente ClientResp { get; set; }
    }
}