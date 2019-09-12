using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Util
{
    public class CONSTANTES
    {
        public static String ConexaoBanco = ConfigurationManager.AppSettings["CaminhoBanco"];
        public static String VersaoAPI = ConfigurationManager.AppSettings["Versao_API"];
        public static String NomeAPI = ConfigurationManager.AppSettings["Nome_API"];

    }
}