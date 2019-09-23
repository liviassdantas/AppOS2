using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Models
{
    public class PostApiResponse<T>
    {
        public Boolean Result { get; set; }
        public T Objeto { get; set; }
        public String Mensagem { get; set; }
        public String Exception { get; set; }
    }
}