using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class Empresa
    {
        public String CPFCNPJ { get; set; }
        public Usuario login { get; set; }
        public Endereco endereco { get; set; }
    }
}