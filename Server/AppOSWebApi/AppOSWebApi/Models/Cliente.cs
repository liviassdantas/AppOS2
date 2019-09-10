using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class Cliente
    {
        public String CPFCNPJ { get; set; }
        public String Nome { get; set; }
        public Endereco endereco { get; set; }
        public String TelefonePrinc { get; set; }
    }
}