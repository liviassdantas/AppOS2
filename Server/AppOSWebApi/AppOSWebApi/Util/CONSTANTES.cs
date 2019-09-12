using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Util
{
    public abstract class CONSTANTES
    {
        public String ConexaoBanco = ConfigurationManager.AppSettings["CaminhoBanco"];
        public String VersaoAPI = ConfigurationManager.AppSettings["Versao_API"];
        public String NomeAPI = ConfigurationManager.AppSettings["Nome_API"];

    }
}