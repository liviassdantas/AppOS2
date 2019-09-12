using AppOSWebApi.Util;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;

namespace AppOSWebApi.Persistence
{
    public class DataSource
    {
        private static IMongoDatabase database;

        public static IMongoDatabase GetConnection()
        {
            if (database == null)
            {
                IMongoClient client = new MongoClient(CONSTANTES.ConexaoBanco);
                database = client.GetDatabase("AppOS");
            }

            return database;
        }

    }
}