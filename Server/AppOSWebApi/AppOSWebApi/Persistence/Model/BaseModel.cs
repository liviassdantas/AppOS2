using AppOSWebApi.Util;
using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Core
{
    public class BaseModel
    {
        internal object id;

        public ObjectId _id { get; set; }
        public String IdString {
            get {
                return _id.ToString();
            }
        }

        public DateTime Data_Modificacao { get; set; }

    }
}