using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Util
{
    public class COSTANTES
    {
        public String NOME_APP = System.Configuration.ConfigurationManager.AppSettings["nome_api"];
        public String VERSAO_API = System.Configuration.ConfigurationManager.AppSettings["version_system"];
    }
}